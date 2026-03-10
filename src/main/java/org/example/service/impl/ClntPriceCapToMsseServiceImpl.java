package org.example.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.ClntPriceCapMapper;
import org.example.mapper.McInstrMapper;
import org.example.mapper.McMrktMapper;
import org.example.mapper.McRmAcInstrLndratMapper;
import org.example.pojo.ClntPriceCapPO;
import org.example.pojo.McInstrPO;
import org.example.pojo.McRmAcInstrLndratPO;
import org.example.service.ClntPriceCapToMsseService;
import org.example.service.IdGeneratorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClntPriceCapToMsseServiceImpl implements ClntPriceCapToMsseService {

    private final ClntPriceCapMapper clntPriceCapMapper;
    private final McMrktMapper mcMrktMapper;
    private final McInstrMapper mcInstrMapper;
    private final IdGeneratorService idGeneratorService;
    private final McRmAcInstrLndratMapper mcRmAcInstrLndratMapper;

    @Override
    public String writeProcessedData() {
        List<ClntPriceCapPO> clntPriceCapPOS = clntPriceCapMapper.selectByPage(1, 10);
        List<McRmAcInstrLndratPO> mcRmAcInstrLndratPOList = new ArrayList<>();
        for (int i = 0; i < clntPriceCapPOS.size(); i++) {
            ClntPriceCapPO items = clntPriceCapPOS.get(i);

            Long mainId = idGeneratorService.generateMainId();

            McRmAcInstrLndratPO mcPffChrgLogDtlPO = new McRmAcInstrLndratPO();

            mcPffChrgLogDtlPO.setRmAcInstrLndratId(idGeneratorService.generateDetailId());
//        mcPffChrgLogDtlPO.setAcId(items.getClntCode());
            mcPffChrgLogDtlPO.setAcId("02-0000389-30");
            mcPffChrgLogDtlPO.setInstrId(findInstrIdByCodeProcess(items.getInstrument()));
            mcPffChrgLogDtlPO.setIsActv("N");
            mcPffChrgLogDtlPO.setAcLndrat(items.getMarginPercent() == null ? BigDecimal.ZERO : items.getMarginPercent().divide(new BigDecimal(100),8, RoundingMode.HALF_UP));
            mcPffChrgLogDtlPO.setRecVerNum(0L);
            mcPffChrgLogDtlPO.setInitTime(LocalDateTime.now());
            mcPffChrgLogDtlPO.setLastUpdTime(LocalDateTime.now());
            mcPffChrgLogDtlPO.setLastUpdBy("MIG");
            mcPffChrgLogDtlPO.setTagSeq(0L);

            mcRmAcInstrLndratPOList.add(mcPffChrgLogDtlPO);
        }
        saveToOracleMcRmAcInstrLndrat(mcRmAcInstrLndratPOList);
        return "ok";
    }

    @DS("oracle")
    @Transactional(rollbackFor = Exception.class)
    private void saveToOracleMcRmAcInstrLndrat(List<McRmAcInstrLndratPO> mcRmAcInstrLndratPOList) {
        mcRmAcInstrLndratMapper.batchInsert(mcRmAcInstrLndratPOList);
    }

    private Long findInstrIdByCodeProcess(String instrument) {
        List<McInstrPO> instrIdByCodeList = mcInstrMapper.findInstrIdByCodeList(instrument);
        if (CollectionUtils.isEmpty(instrIdByCodeList)) {
            return null;
        }
        return instrIdByCodeList.get(0).getInstrId() == null ? null : instrIdByCodeList.get(0).getInstrId();
    }
}
