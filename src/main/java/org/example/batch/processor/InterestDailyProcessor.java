//package org.example.batch.processor;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.example.mapper.McInstrMapper;
//import org.example.mapper.McMrktMapper;
//import org.example.pojo.ClntPriceCapPO;
//import org.example.pojo.InterestDailyPO;
//import org.example.pojo.McInstrPO;
//import org.example.pojo.McRmAcInstrLndratPO;
//import org.example.pojo.dtos.ClntPriceCapResultDTO;
//import org.example.pojo.dtos.InterestDailyResultDTO;
//import org.example.service.IdGeneratorService;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.stereotype.Component;
//import org.springframework.util.CollectionUtils;
//
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Component
//@Slf4j
//@RequiredArgsConstructor
//public class InterestDailyProcessor implements ItemProcessor<InterestDailyPO, InterestDailyResultDTO> {
//
//    private final McMrktMapper mcMrktMapper;
//    private final McInstrMapper mcInstrMapper;
//    private final IdGeneratorService idGeneratorService;
//
//    @Override
//    public InterestDailyResultDTO process(InterestDailyPO items) throws Exception {
//        if(items == null){
//            return null;
//        }
//
//        ClntPriceCapResultDTO result = new ClntPriceCapResultDTO();
//
//        Long mainId = idGeneratorService.generateMainId();
//
//        McRmAcInstrLndratPO mcPffChrgLogDtlPO = new McRmAcInstrLndratPO();
//
//        mcPffChrgLogDtlPO.setRmAcInstrLndratId(idGeneratorService.generateDetailId());
////        mcPffChrgLogDtlPO.setAcId(clntPriceCapPO.getClntCode());
//        mcPffChrgLogDtlPO.setAcId("02-0000389-30");
//        mcPffChrgLogDtlPO.setInstrId(findInstrIdByCodeProcess(items.getInstrument()));
//        mcPffChrgLogDtlPO.setIsActv("N");
//        mcPffChrgLogDtlPO.setAcLndrat(items.getMarginPercent() == null ? BigDecimal.ZERO : items.getMarginPercent().divide(new BigDecimal(100),8, RoundingMode.HALF_UP));
//        mcPffChrgLogDtlPO.setRecVerNum(0L);
//        mcPffChrgLogDtlPO.setInitTime(LocalDateTime.now());
//        mcPffChrgLogDtlPO.setLastUpdTime(LocalDateTime.now());
//        mcPffChrgLogDtlPO.setLastUpdBy("MIG");
//        mcPffChrgLogDtlPO.setTagSeq(0L);
//
//        result.setMainRecord(mcPffChrgLogDtlPO);
//
//        return result;
//    }
//
//    private Long findInstrIdByCodeProcess(String instrument) {
//        List<McInstrPO> instrIdByCodeList = mcInstrMapper.findInstrIdByCodeList(instrument);
//        if (CollectionUtils.isEmpty(instrIdByCodeList)) {
//            return null;
//        }
//        return instrIdByCodeList.get(0).getInstrId() == null ? null : instrIdByCodeList.get(0).getInstrId();
//    }
//}
