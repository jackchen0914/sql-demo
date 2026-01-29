package org.example.batch.reader;

import com.baomidou.dynamic.datasource.annotation.DS;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.ClntPriceCapMapper;
import org.example.mapper.InterestDailyMapper;
import org.example.pojo.ClntPriceCapPO;
import org.example.pojo.InterestDailyPO;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
public class InterestDailyReader implements ItemReader<InterestDailyPO> {

    //    private static final int PAGE_SIZE = 1000;
    private static final int PAGE_SIZE = 5;//dev

    @Autowired
    private InterestDailyMapper interestDailyMapper;

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
            return currentBatch.get(index);
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

