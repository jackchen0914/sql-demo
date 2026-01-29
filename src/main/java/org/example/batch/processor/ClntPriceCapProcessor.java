package org.example.batch.processor;

import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.McInstrMapper;
import org.example.mapper.McMrktMapper;
import org.example.pojo.*;
import org.example.pojo.dtos.ClntPriceCapResultDTO;
import org.example.service.IdGeneratorService;
import org.example.utils.PropertyConverUtils;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@Slf4j
@RequiredArgsConstructor
public class ClntPriceCapProcessor implements ItemProcessor<ClntPriceCapPO, ClntPriceCapResultDTO> {

    private final McMrktMapper mcMrktMapper;
    private final McInstrMapper mcInstrMapper;
    private final IdGeneratorService idGeneratorService;

    @Override
    public ClntPriceCapResultDTO process(ClntPriceCapPO clntPriceCapPO) throws Exception {
        if(clntPriceCapPO == null){
            return null;
        }

        ClntPriceCapResultDTO result = new ClntPriceCapResultDTO();

        Long mainId = idGeneratorService.generateMainId();

        McRmAcInstrLndratPO mcPffChrgLogDtlPO = new McRmAcInstrLndratPO();

        mcPffChrgLogDtlPO.setRmAcInstrLndratId(idGeneratorService.generateDetailId());
//        mcPffChrgLogDtlPO.setAcId(clntPriceCapPO.getClntCode());
        mcPffChrgLogDtlPO.setAcId("02-0000389-30");
        mcPffChrgLogDtlPO.setInstrId(findInstrIdByCodeProcess(clntPriceCapPO.getInstrument()));
        mcPffChrgLogDtlPO.setIsActv("N");
        mcPffChrgLogDtlPO.setAcLndrat(clntPriceCapPO.getMarginPercent() == null ? BigDecimal.ZERO : clntPriceCapPO.getMarginPercent().divide(new BigDecimal(100),8, RoundingMode.HALF_UP));
        mcPffChrgLogDtlPO.setRecVerNum(0L);
        mcPffChrgLogDtlPO.setInitTime(LocalDateTime.now());
        mcPffChrgLogDtlPO.setLastUpdTime(LocalDateTime.now());
        mcPffChrgLogDtlPO.setLastUpdBy("MIG");
        mcPffChrgLogDtlPO.setTagSeq(0L);

        result.setMainRecord(mcPffChrgLogDtlPO);

        return result;
    }

    private Long findInstrIdByCodeProcess(String instrument) {
        List<McInstrPO> instrIdByCodeList = mcInstrMapper.findInstrIdByCodeList(instrument);
        if (CollectionUtils.isEmpty(instrIdByCodeList)) {
            return null;
        }
        return instrIdByCodeList.get(0).getInstrId() == null ? null : instrIdByCodeList.get(0).getInstrId();
    }
}
