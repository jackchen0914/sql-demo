package org.example.batch.writer;

import com.baomidou.dynamic.datasource.annotation.DS;
import lombok.RequiredArgsConstructor;
import org.example.mapper.McAcOnlineFxReqMapper;
import org.example.mapper.McCommCalcMtdMapper;
import org.example.mapper.McCommRuleMapper;
import org.example.pojo.McAcOnlineFxReqPO;
import org.example.pojo.McCommCalcMtdPO;
import org.example.pojo.McCommRulePO;
import org.example.pojo.dtos.BrokerageWithRageResultDTO;
import org.example.pojo.dtos.CashTransferAllResultDTO;
import org.example.utils.DataBaseOperationUtils;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BrokerageWriter implements ItemWriter<BrokerageWithRageResultDTO> {

    private static final int BATCH_SIZE = 1000;

    private final McCommRuleMapper mcCommRuleMapper;
    private final McCommCalcMtdMapper mcCommCalcMtdMapper;

    @Override
    @DS("oracle")
    @Transactional(rollbackFor = Exception.class)
    public void write(List<? extends BrokerageWithRageResultDTO> items) throws Exception {
        if(items.isEmpty()){
            return;
        }

        List<McCommRulePO> mainRecord = new ArrayList<>();
        List<McCommCalcMtdPO> detailRecord = new ArrayList<>();

        for (BrokerageWithRageResultDTO dto : items){
            DataBaseOperationUtils.addIfNotNull(mainRecord,dto.getMcCommRuleRecord());
            DataBaseOperationUtils.addIfNotNull(detailRecord,dto.getMcCommCalcMtdRecord());
        }

        DataBaseOperationUtils.batchInsertFrom(mainRecord,mcCommRuleMapper::batchInsert,BATCH_SIZE);
        DataBaseOperationUtils.batchInsertFrom(detailRecord,mcCommCalcMtdMapper::batchInsert,BATCH_SIZE);
    }

}
