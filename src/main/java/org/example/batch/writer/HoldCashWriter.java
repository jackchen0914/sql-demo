package org.example.batch.writer;

import com.baomidou.dynamic.datasource.annotation.DS;
import lombok.RequiredArgsConstructor;
import org.example.mapper.McAcFundHoldRecMapper;
import org.example.mapper.McAcFundHoldTxnMapper;
import org.example.pojo.McAcFundHoldRecPO;
import org.example.pojo.McAcFundHoldTxnPO;
import org.example.pojo.dtos.HoldCashResultDTO;
import org.example.utils.DataBaseOperationUtils;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class HoldCashWriter implements ItemWriter<HoldCashResultDTO> {

    private static final int BATCH_SIZE = 1000;

    private final McAcFundHoldRecMapper mcAcFundHoldRecMapper;
    private final McAcFundHoldTxnMapper mcAcFundHoldTxnMapper;

    @Override
    @DS("oracle")
    @Transactional(rollbackFor = Exception.class)
    public void write(List<? extends HoldCashResultDTO> items) throws Exception {
        if(items.isEmpty()){
            return;
        }

        List<McAcFundHoldRecPO> mainRecord = new ArrayList<>();
        List<McAcFundHoldTxnPO> detailRecord = new ArrayList<>();

        for (HoldCashResultDTO dto : items){
            DataBaseOperationUtils.addIfNotNull(mainRecord,dto.getMainRecord());
            DataBaseOperationUtils.addIfNotNull(detailRecord,dto.getDetailRecord());
        }

        DataBaseOperationUtils.batchInsertFrom(mainRecord,mcAcFundHoldTxnMapper::batchInsert,BATCH_SIZE);
        DataBaseOperationUtils.batchInsertFrom(mainRecord,mcAcFundHoldRecMapper::batchInsert,BATCH_SIZE);
    }
}
