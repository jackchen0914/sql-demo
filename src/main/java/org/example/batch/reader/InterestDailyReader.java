package org.example.batch.reader;

import com.baomidou.dynamic.datasource.annotation.DS;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.ClntPriceCapMapper;
import org.example.mapper.ForexRateMapper;
import org.example.mapper.InterestDailyMapper;
import org.example.pojo.ClntPriceCapPO;
import org.example.pojo.ForexRatePO;
import org.example.pojo.GlobalSettingsPO;
import org.example.pojo.InterestDailyPO;
import org.example.pojo.dtos.BrokerageWithRageDTO;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
public class InterestDailyReader implements ItemReader<InterestDailyPO> {

    //    private static final int PAGE_SIZE = 1000;
    private static final int PAGE_SIZE = 5;//dev

    @Autowired
    private InterestDailyMapper interestDailyMapper;

    @Autowired
    private ForexRateMapper forexRateMapper;

    private List<InterestDailyPO> currentBatch;
    private final AtomicInteger currentIndex = new AtomicInteger(0);
    private final AtomicInteger currentPage = new AtomicInteger(0);
    private volatile boolean finished = false;

    @Override
    @DS("master")
    public InterestDailyPO read() {
        if (currentBatch == null || currentIndex.get() >= currentBatch.size()) {
            if (finished) {
                return null;
            }
            loadNextBatch();
            if (currentBatch == null || currentBatch.isEmpty()) {
                finished = true;
                return null;
            }
        }

        int index = currentIndex.getAndIncrement();
        if (index < currentBatch.size()) {
            InterestDailyPO interestDailyPO = currentBatch.get(index);
                ForexRatePO ratePO = forexRateMapper.selectRateByDate(interestDailyPO.getCcy(), String.valueOf(interestDailyPO.getDate()).replaceAll("T"," "));
                if(ratePO!=null) {
                    interestDailyPO.setRateFromBaseCcy(ratePO.getXRate() != null ? BigDecimal.ONE.divide(ratePO.getXRate(), 4, RoundingMode.HALF_UP) : BigDecimal.ZERO);
                    interestDailyPO.setRateToBaseCcy(ratePO.getXRate() != null ? ratePO.getXRate() : BigDecimal.ZERO);
                }
            return interestDailyPO;
//            return currentBatch.get(index);
        }
        return null;
    }

    @DS("master")
    private void loadNextBatch() {
        if (currentPage.get()>0){
            finished = true;
            return;
        }//dev
        int offset = currentPage.getAndIncrement() * PAGE_SIZE;
        currentBatch = interestDailyMapper.selectByPage(offset,PAGE_SIZE);
        currentIndex.set(0);

        if(currentBatch == null || currentBatch.isEmpty()){
            finished = true;
        }
    }

    public void reset(){
        currentBatch = null;
        currentIndex.set(0);
        currentPage.set(0);
        finished = false;
    }

}

