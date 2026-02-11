package org.example.batch.reader;

import com.baomidou.dynamic.datasource.annotation.DS;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.CashTransferMapper;
import org.example.mapper.ForexRateMapper;
import org.example.pojo.ForexRatePO;
import org.example.pojo.InterestDailyPO;
import org.example.pojo.dtos.CashTransferAllDTO;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
public class CashTransferAllReader implements ItemReader<CashTransferAllDTO> {

    //    private static final int PAGE_SIZE = 1000;
    private static final int PAGE_SIZE = 10;//dev

    @Autowired
    private CashTransferMapper cashTransferMapper;
    @Autowired
    private ForexRateMapper forexRateMapper;

    private List<CashTransferAllDTO> currentBatch;
    private final AtomicInteger currentIndex = new AtomicInteger(0);
    private final AtomicInteger currentPage = new AtomicInteger(0);
    private volatile boolean finished = false;

    @Override
    @DS("master")
    public CashTransferAllDTO read() {
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
            CashTransferAllDTO dto = currentBatch.get(index);
            ForexRatePO ratePO = forexRateMapper.selectRateByDate(dto.getCCYFrom(), String.valueOf(dto.getTransferDate()).replaceAll("T"," "));
            if(ratePO!=null) {//BigDecimal.ONE.divide(ratePO.getXRate(), 4, RoundingMode.HALF_UP)
                dto.setBaseCcyEquAmtValue(ratePO.getXRate() != null ? dto.getAmountFrom().multiply(ratePO.getXRate()).setScale(2,RoundingMode.HALF_UP) : BigDecimal.ZERO);
                dto.setBaseCcyEquToAmtValue(ratePO.getXRate() != null ? ratePO.getXRate() : BigDecimal.ZERO);
            }
            return dto;
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
        currentBatch = cashTransferMapper.selectCashTransferAll(offset,PAGE_SIZE);
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

