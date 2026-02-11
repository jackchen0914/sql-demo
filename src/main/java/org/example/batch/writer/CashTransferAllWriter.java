package org.example.batch.writer;

import com.baomidou.dynamic.datasource.annotation.DS;
import lombok.RequiredArgsConstructor;
import org.example.mapper.*;
import org.example.pojo.*;
import org.example.pojo.dtos.CashTransferAllResultDTO;
import org.example.pojo.dtos.ClntPriceCapResultDTO;
import org.example.utils.DataBaseOperationUtils;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CashTransferAllWriter implements ItemWriter<CashTransferAllResultDTO> {

    private static final int BATCH_SIZE = 1000;

    private final McAcOnlineFxReqMapper mcAcOnlineFxReqMapper;
    private final McAcFuntrfCcyexRecMapper mcAcFuntrfCcyexRecMapper;
    private final McAcFuntrfCcyexReqMapper mcAcFuntrfCcyexReqMapper;
    private final McAcFundGenStmtRemrkMapper mcAcFundGenStmtRemrkMapper;

    @Override
    @DS("oracle")
    @Transactional(rollbackFor = Exception.class)
    public void write(List<? extends CashTransferAllResultDTO> items) throws Exception {
        if(items.isEmpty()){
            return;
        }

        List<McAcOnlineFxReqPO> mcAcOnlineFxReqPOList = new ArrayList<>();
        List<McAcFuntrfCcyexRecPO> mcAcFuntrfCcyexRecPOList = new ArrayList<>();
        List<McAcFuntrfCcyexReqPO> mcAcFuntrfCcyexReqPOList = new ArrayList<>();
        List<McAcFundGenStmtRemrkPO> mcAcFundGenStmtRemrkPOList = new ArrayList<>();

        for (CashTransferAllResultDTO dto : items){
            DataBaseOperationUtils.addIfNotNull(mcAcOnlineFxReqPOList,dto.getMainRecord());
            DataBaseOperationUtils.addIfNotNull(mcAcFuntrfCcyexRecPOList,dto.getMcAcFuntrfCcyexRecRecord());
            DataBaseOperationUtils.addIfNotNull(mcAcFuntrfCcyexReqPOList,dto.getMcAcFuntrfCcyexReqRecord());
            DataBaseOperationUtils.addIfNotNull(mcAcFundGenStmtRemrkPOList,dto.getMcAcFundGenStmtRemrkRecord());
        }

        DataBaseOperationUtils.batchInsertFrom(mcAcOnlineFxReqPOList,mcAcOnlineFxReqMapper::batchInsert,BATCH_SIZE);
        DataBaseOperationUtils.batchInsertFrom(mcAcFuntrfCcyexRecPOList,mcAcFuntrfCcyexRecMapper::batchInsert,BATCH_SIZE);
        DataBaseOperationUtils.batchInsertFrom(mcAcFuntrfCcyexReqPOList,mcAcFuntrfCcyexReqMapper::batchInsert,BATCH_SIZE);
        DataBaseOperationUtils.batchInsertFrom(mcAcFundGenStmtRemrkPOList,mcAcFundGenStmtRemrkMapper::batchInsert,BATCH_SIZE);
    }

}
