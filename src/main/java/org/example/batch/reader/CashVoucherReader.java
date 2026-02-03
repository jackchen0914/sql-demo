package org.example.batch.reader;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.example.mapper.CashVoucherMapper;
import org.example.mapper.DivAnnMapper;
import org.example.mapper.ForexRateMapper;
import org.example.mapper.TransactionTypesMapper;
import org.example.pojo.ForexRatePO;
import org.example.pojo.InterestDailyPO;
import org.example.pojo.TransactionTypesPO;
import org.example.pojo.dtos.CaRSCDTO;
import org.example.pojo.dtos.CashVoucherWithRequestDTO;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class CashVoucherReader implements ItemReader<CashVoucherWithRequestDTO> {

    //    private static final int PAGE_SIZE = 1000;
    private static final int PAGE_SIZE = 5;

    @Autowired
    private CashVoucherMapper cashVoucherMapper;
    @Autowired
    private TransactionTypesMapper transactionTypesMapper;
    @Autowired
    private ForexRateMapper forexRateMapper;

    private List<CashVoucherWithRequestDTO> currentBatch;
    private final AtomicInteger currentIndex = new AtomicInteger(0);
    private final AtomicInteger currentPage = new AtomicInteger(0);
    private final AtomicInteger globalIndex = new AtomicInteger(1);
    private volatile boolean finished = false;

    @Override
    @DS("master")
    public CashVoucherWithRequestDTO read() {
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
            CashVoucherWithRequestDTO dto = currentBatch.get(index);
            dto.setIndex(globalIndex.getAndIncrement());
            TransactionTypesPO transactionTypesPO = transactionTypesMapper.selectTxnTypeCode(dto.getTxnType());
            dto.setTxnTypIdValue(transactionTypesPO.getSignIndicator().equals("C") ? 1L : 6L);
            dto.setTxnTypActnCdeValue(transactionTypesPO.getSignIndicator().equals("C") ? "IN" : "OUT");
            ForexRatePO forexRatePO = forexRateMapper.selectRateByDate(dto.getCcy(), String.valueOf(dto.getConfirmationDate()).split("T")[0]);
            dto.setBaseCcyEquAmtValue(dto.getAmount().multiply(forexRatePO.getXRate()).setScale(8, RoundingMode.HALF_UP));
            return dto;
        }
        return null;
    }

    @DS("master")
    private void loadNextBatch() {
        if (currentPage.get()>0){
            finished = true;
            return;
        }//test
        int offset = currentPage.getAndIncrement() * PAGE_SIZE;
        currentBatch = cashVoucherMapper.selectCashVoucherWithRequest(offset,PAGE_SIZE);
        currentIndex.set(0);

        if(currentBatch == null || currentBatch.isEmpty()){
            finished = true;
        }
    }

    public void reset(){
        currentBatch = null;
        currentIndex.set(0);
        currentPage.set(0);
        globalIndex.set(0);
        finished = false;
    }


}

