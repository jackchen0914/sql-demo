package org.example.batch.writer;

import com.baomidou.dynamic.datasource.annotation.DS;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.McAcuintTxnDtlMapper;
import org.example.mapper.McAcuintTxnMapper;
import org.example.mapper.McRmAcInstrLndratMapper;
import org.example.pojo.*;
import org.example.pojo.dtos.ClntPriceCapResultDTO;
import org.example.pojo.dtos.InterestDailyResultDTO;
import org.example.utils.DataBaseOperationUtils;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class InterestDailyWriter implements ItemWriter<InterestDailyResultDTO> {

    private static final int BATCH_SIZE = 1000;

    private final McAcuintTxnDtlMapper mcAcuintTxnDtlMapper;
    private final McAcuintTxnMapper mcAcuintTxnMapper;

    @Override
    @DS("oracle")
    @Transactional(rollbackFor = Exception.class)
    public void write(List<? extends InterestDailyResultDTO> items) throws Exception {
        if(items.isEmpty()){
            return;
        }

        List<McAcuintTxnDtlPO> mainRecord = new ArrayList<>();
        List<McAcuintTxnPO> detailRecord = new ArrayList<>();

        for (InterestDailyResultDTO dto : items){
            DataBaseOperationUtils.addIfNotNull(mainRecord,dto.getMainRecord());
            DataBaseOperationUtils.addIfNotNull(detailRecord,dto.getDetailRecord());
        }
        DataBaseOperationUtils.batchInsertFrom(detailRecord,mcAcuintTxnMapper::batchInsert,BATCH_SIZE);
        DataBaseOperationUtils.batchInsertFrom(mainRecord,mcAcuintTxnDtlMapper::batchInsert,BATCH_SIZE);
    }
}
