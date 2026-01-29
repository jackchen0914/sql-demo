package org.example.batch.writer;

import com.baomidou.dynamic.datasource.annotation.DS;
import lombok.RequiredArgsConstructor;
import org.example.mapper.McPffChrgLogDtlMapper;
import org.example.mapper.McPffChrgLogMapper;
import org.example.pojo.McPffChrgLogDtlPO;
import org.example.pojo.McPffChrgLogPO;
import org.example.pojo.dtos.FeeMigrationResultDTO;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PortfolioFeeDailyWriter implements ItemWriter<FeeMigrationResultDTO> {

    private static final int BATCH_SIZE = 1000;

    private final McPffChrgLogMapper mcPffChrgLogMapper;

    private final McPffChrgLogDtlMapper mcPffChrgLogDtlMapper;

    @Override
    @DS("oracle")
    @Transactional(rollbackFor = Exception.class)
    public void write(List<? extends FeeMigrationResultDTO> items) throws Exception {
        if(items.isEmpty()){
            return;
        }

        List<McPffChrgLogPO> mainRecord = new ArrayList<>();
        List<McPffChrgLogDtlPO> detailsRecord = new ArrayList<>();

        for (FeeMigrationResultDTO dto : items){
            if(dto.getMainRecord()!=null){
                mainRecord.add(dto.getMainRecord());
            }
            if(!dto.getDetailRecord().isEmpty()){
                detailsRecord.addAll(dto.getDetailRecord());
            }
        }

        batchInsertMainRecords(mainRecord);
        batchInsertDetailRecords(detailsRecord);
    }

    private void batchInsertDetailRecords(List<McPffChrgLogDtlPO> detailsRecord) {
        if(detailsRecord.isEmpty()){
            return;
        }

        for (int i = 0; i < detailsRecord.size(); i+=BATCH_SIZE) {
            int end = Math.min(i+BATCH_SIZE,detailsRecord.size());
            List<McPffChrgLogDtlPO> mcPffChrgLogDtlPOList = detailsRecord.subList(i, end);
            mcPffChrgLogDtlMapper.batchInsert(mcPffChrgLogDtlPOList);
        }
    }

    private void batchInsertMainRecords(List<McPffChrgLogPO> mainRecord) {

        if(mainRecord.isEmpty()){
            return;
        }

        for (int i = 0; i < mainRecord.size(); i+=BATCH_SIZE) {
            int end = Math.min(i+BATCH_SIZE,mainRecord.size());
            List<McPffChrgLogPO> mcPffChrgLogPOList = mainRecord.subList(i, end);
            mcPffChrgLogMapper.batchInsert(mcPffChrgLogPOList);
        }

    }
}
