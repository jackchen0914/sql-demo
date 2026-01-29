package org.example.batch.reader;

import com.baomidou.dynamic.datasource.annotation.DS;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.BrokerageMapper;
import org.example.mapper.CashTransferMapper;
import org.example.pojo.dtos.BrokerageWithRageDTO;
import org.example.pojo.dtos.CashTransferAllDTO;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
public class BrokerageReader implements ItemReader<BrokerageWithRageDTO> {

    //    private static final int PAGE_SIZE = 1000;
    private static final int PAGE_SIZE = 10;//dev

    @Autowired
    private BrokerageMapper brokerageMapper;

    private List<BrokerageWithRageDTO> currentBatch;
    private final AtomicInteger currentIndex = new AtomicInteger(0);
    private final AtomicInteger currentPage = new AtomicInteger(0);
    private volatile boolean finished = false;

    @Override
    @DS("master")
    public BrokerageWithRageDTO read() {
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
        currentBatch = brokerageMapper.selectBrokerageAll(offset,PAGE_SIZE);
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

