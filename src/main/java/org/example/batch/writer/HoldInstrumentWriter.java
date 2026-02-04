package org.example.batch.writer;

import com.baomidou.dynamic.datasource.annotation.DS;
import lombok.RequiredArgsConstructor;
import org.example.mapper.McAcFundHoldRecMapper;
import org.example.mapper.McAcFundHoldTxnMapper;
import org.example.mapper.McAcHoldStkTxnDtlMapper;
import org.example.mapper.McAcHoldStkTxnMapper;
import org.example.pojo.McAcFundHoldRecPO;
import org.example.pojo.McAcFundHoldTxnPO;
import org.example.pojo.McAcHoldStkTxnDtlPO;
import org.example.pojo.McAcHoldStkTxnPO;
import org.example.pojo.dtos.HoldCashResultDTO;
import org.example.pojo.dtos.HoldInstrumentResultDTO;
import org.example.utils.DataBaseOperationUtils;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class HoldInstrumentWriter implements ItemWriter<HoldInstrumentResultDTO> {

    private static final int BATCH_SIZE = 1000;

    private final McAcHoldStkTxnDtlMapper mcAcHoldStkTxnDtlMapper;
    private final McAcHoldStkTxnMapper mcAcHoldStkTxnMapper;

    @Override
    @DS("oracle")
    @Transactional(rollbackFor = Exception.class)
    public void write(List<? extends HoldInstrumentResultDTO> items) throws Exception {
        if(items.isEmpty()){
            return;
        }

        List<McAcHoldStkTxnPO> detailRecord = new ArrayList<>();
        List<McAcHoldStkTxnDtlPO> mainRecord = new ArrayList<>();

        for (HoldInstrumentResultDTO dto : items){
            DataBaseOperationUtils.addIfNotNull(mainRecord,dto.getMainRecord());
            DataBaseOperationUtils.addIfNotNull(detailRecord,dto.getDetailRecord());
        }

        DataBaseOperationUtils.batchInsertFrom(detailRecord,mcAcHoldStkTxnMapper::batchInsert,BATCH_SIZE);
        DataBaseOperationUtils.batchInsertFrom(mainRecord,mcAcHoldStkTxnDtlMapper::batchInsert,BATCH_SIZE);
    }
}
