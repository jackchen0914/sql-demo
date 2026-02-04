package org.example.batch.reader;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.example.mapper.DivAnnMapper;
import org.example.mapper.InstrumentVoucherMapper;
import org.example.mapper.TransactionTypesMapper;
import org.example.pojo.ForexRatePO;
import org.example.pojo.InstrumentVoucherPO;
import org.example.pojo.TransactionTypesPO;
import org.example.pojo.dtos.CaRSCDTO;
import org.example.pojo.dtos.CashVoucherWithRequestDTO;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.RoundingMode;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class InstrumentVoucherReader implements ItemReader<InstrumentVoucherPO> {

    //    private static final int PAGE_SIZE = 1000;
    private static final int PAGE_SIZE = 10;//dev

    @Autowired
    private InstrumentVoucherMapper instrumentVoucherMapper;
    @Autowired
    private TransactionTypesMapper transactionTypesMapper;

    private List<InstrumentVoucherPO> currentBatch;
    private final AtomicInteger currentIndex = new AtomicInteger(0);
    private final AtomicInteger currentPage = new AtomicInteger(0);
    private volatile boolean finished = false;

    @Override
    @DS("master")
    public InstrumentVoucherPO read() {
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
            InstrumentVoucherPO dto = currentBatch.get(index);
            TransactionTypesPO transactionTypesPO = transactionTypesMapper.selectTxnTypeActionCode(dto.getTxnType());
            dto.setTxnTypIdValue(transactionTypesPO.getSignIndicator().equals("D") ? 2501L : 2522L);
            dto.setTxnTypActnCdeValue(transactionTypesPO.getSignIndicator().equals("D") ? "IN" : "OUT");
            dto.setStkStatCdeValue(stkStatCdeTrans(dto.getLocation(),dto.getTxnType()));
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
        currentBatch = instrumentVoucherMapper.selectByPage(offset,PAGE_SIZE);
        currentIndex.set(0);

        if(currentBatch == null || currentBatch.isEmpty()){
            finished = true;
        }
    }

    private String stkStatCdeTrans(String location,String txnType) {
        switch (location){
            case "NOM":
                return "NOMINEE";
            case "OWM":
                return "OWNERNAME";
            case "BONUS":
                return "BONUS";
            case "ST":
                return findByStatCde(txnType);
            default: return " ";
        }
    }

    private String findByStatCde(String txnType) {
        TransactionTypesPO transactionTypesPO = transactionTypesMapper.selectTxnTypeActionCode(txnType);
        if (transactionTypesPO.getSignIndicator() != null) {
            return transactionTypesPO.getSignIndicator().equals("D") ? "DIP" : "WIP";
        }
        return null;
    }

    public void reset(){
        currentBatch = null;
        currentIndex.set(0);
        currentPage.set(0);
        finished = false;
    }


}

