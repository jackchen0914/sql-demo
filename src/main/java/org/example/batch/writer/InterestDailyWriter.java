package org.example.batch.writer;

import com.baomidou.dynamic.datasource.annotation.DS;
import lombok.RequiredArgsConstructor;
import org.example.mapper.McRmAcInstrLndratMapper;
import org.example.pojo.McRmAcInstrLndratPO;
import org.example.pojo.dtos.ClntPriceCapResultDTO;
import org.example.utils.DataBaseOperationUtils;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InterestDailyWriter implements ItemWriter<ClntPriceCapResultDTO> {

    private static final int BATCH_SIZE = 1000;

    private final McRmAcInstrLndratMapper mcRmAcInstrLndratMapper;

    @Override
    @DS("oracle")
    @Transactional(rollbackFor = Exception.class)
    public void write(List<? extends ClntPriceCapResultDTO> items) throws Exception {
        if(items.isEmpty()){
            return;
        }

        List<McRmAcInstrLndratPO> mainRecord = new ArrayList<>();

        for (ClntPriceCapResultDTO dto : items){
            DataBaseOperationUtils.addIfNotNull(mainRecord,dto.getMainRecord());
        }

        DataBaseOperationUtils.batchInsertFrom(mainRecord,mcRmAcInstrLndratMapper::batchInsert,BATCH_SIZE);
    }
}
