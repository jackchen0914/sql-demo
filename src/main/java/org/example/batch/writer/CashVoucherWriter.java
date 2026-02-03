package org.example.batch.writer;

import com.baomidou.dynamic.datasource.annotation.DS;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.*;
import org.example.pojo.*;
import org.example.pojo.dtos.CaRSCResultDTO;
import org.example.pojo.dtos.CashVoucherResultDTO;
import org.example.utils.DataBaseOperationUtils;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CashVoucherWriter implements ItemWriter<CashVoucherResultDTO> {

    private static final int BATCH_SIZE = 1000;

    private final McAcFundRecMapper mcAcFundRecMapper;
    private final McAcFundTxnRecMapper mcAcFundTxnRecMapper;
    private final McFundTpReltnMapper mcFundTpReltnMapper;

    @Override
    @DS("oracle")
    @Transactional(rollbackFor = Exception.class)
    public void write(List<? extends CashVoucherResultDTO> items) throws Exception {
        if(items.isEmpty()){
            return;
        }

        List<McAcFundRecPO> mcAcFundRecPORecord = new ArrayList<>();
        List<McAcFundTxnRecPO> mcAcFundTxnRecPORecord = new ArrayList<>();
        List<McFundTpReltnPO> mcFundTpReltnPORecord = new ArrayList<>();

        for (CashVoucherResultDTO dto : items){
            DataBaseOperationUtils.addIfNotNull(mcAcFundRecPORecord,dto.getMcAcFundRecRecord());
            DataBaseOperationUtils.addIfNotNull(mcAcFundTxnRecPORecord,dto.getMcAcFundTxnRecRecord());
            DataBaseOperationUtils.addIfNotNull(mcFundTpReltnPORecord,dto.getMcFundTpReltnPRecord());
        }
        log.info("{}条现金凭证记录待插入数据库",mcFundTpReltnPORecord);
        log.info("{}条现金凭证记录待插入数据库2",mcFundTpReltnPORecord.size());
        DataBaseOperationUtils.batchInsertFrom(mcAcFundTxnRecPORecord,mcAcFundTxnRecMapper::batchInsert,BATCH_SIZE);
        DataBaseOperationUtils.batchInsertFrom(mcAcFundRecPORecord,mcAcFundRecMapper::batchInsert,BATCH_SIZE);
        DataBaseOperationUtils.batchInsertFrom(mcFundTpReltnPORecord,mcFundTpReltnMapper::batchInsert,BATCH_SIZE);
    }

}
