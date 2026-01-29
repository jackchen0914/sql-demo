package org.example.batch.writer;

import com.baomidou.dynamic.datasource.annotation.DS;
import lombok.RequiredArgsConstructor;
import org.example.mapper.McAcOnlineFxReqMapper;
import org.example.mapper.McRmAcInstrLndratMapper;
import org.example.pojo.McAcOnlineFxReqPO;
import org.example.pojo.McPffChrgLogPO;
import org.example.pojo.McRmAcInstrLndratPO;
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

    @Override
    @DS("oracle")
    @Transactional(rollbackFor = Exception.class)
    public void write(List<? extends CashTransferAllResultDTO> items) throws Exception {
        if(items.isEmpty()){
            return;
        }

        List<McAcOnlineFxReqPO> mainRecord = new ArrayList<>();

        for (CashTransferAllResultDTO dto : items){
            DataBaseOperationUtils.addIfNotNull(mainRecord,dto.getMainRecord());
        }

        DataBaseOperationUtils.batchInsertFrom(mainRecord,mcAcOnlineFxReqMapper::batchInsert,BATCH_SIZE);
    }

}
