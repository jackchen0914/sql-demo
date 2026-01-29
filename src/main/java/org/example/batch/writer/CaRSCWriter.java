package org.example.batch.writer;

import com.baomidou.dynamic.datasource.annotation.DS;
import lombok.RequiredArgsConstructor;
import org.example.mapper.McCeventMapper;
import org.example.mapper.McConvEvntMapper;
import org.example.mapper.McPffChrgLogDtlMapper;
import org.example.mapper.McPffChrgLogMapper;
import org.example.pojo.McCeventPO;
import org.example.pojo.McConvEvntPO;
import org.example.pojo.McPffChrgLogDtlPO;
import org.example.pojo.McPffChrgLogPO;
import org.example.pojo.dtos.CaRSCResultDTO;
import org.example.pojo.dtos.FeeMigrationResultDTO;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CaRSCWriter implements ItemWriter<CaRSCResultDTO> {

    private static final int BATCH_SIZE = 1000;

    private final McCeventMapper mcCeventMapper;

    private final McConvEvntMapper mcConvEvntMapper;

    @Override
    @DS("oracle")
    @Transactional(rollbackFor = Exception.class)
    public void write(List<? extends CaRSCResultDTO> items) throws Exception {
        if(items.isEmpty()){
            return;
        }

        List<McConvEvntPO> mainRecord = new ArrayList<>();
        List<McCeventPO> detailsRecord = new ArrayList<>();

        for (CaRSCResultDTO dto : items){
            if(dto.getMainRecord()!=null){
                mainRecord.add(dto.getMainRecord());
            }
            if(!dto.getDetailRecord().isEmpty()){
                detailsRecord.addAll(dto.getDetailRecord());
            }
        }

        batchInsertDetailRecords(detailsRecord);
        batchInsertMainRecords(mainRecord);
    }

    private void batchInsertDetailRecords(List<McCeventPO> detailsRecord) {
        if(detailsRecord.isEmpty()){
            return;
        }

        for (int i = 0; i < detailsRecord.size(); i+=BATCH_SIZE) {
            int end = Math.min(i+BATCH_SIZE,detailsRecord.size());
            List<McCeventPO> mcCeventPOList = detailsRecord.subList(i, end);
            mcCeventMapper.batchInsert(mcCeventPOList);
        }
    }

    private void batchInsertMainRecords(List<McConvEvntPO> mainRecord) {

        if(mainRecord.isEmpty()){
            return;
        }

        for (int i = 0; i < mainRecord.size(); i+=BATCH_SIZE) {
            int end = Math.min(i+BATCH_SIZE,mainRecord.size());
            List<McConvEvntPO> mcConvEvntPOList = mainRecord.subList(i, end);
            mcConvEvntMapper.batchInsert(mcConvEvntPOList);
        }

    }
}
