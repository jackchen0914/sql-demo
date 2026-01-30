package org.example.batch.processor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.ForexRateMapper;
import org.example.pojo.*;
import org.example.pojo.dtos.InterestDailyResultDTO;
import org.example.service.IdGeneratorService;
import org.example.utils.PropertyConverUtils;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Objects;

@Component
@Slf4j
@RequiredArgsConstructor
public class InterestDailyProcessor implements ItemProcessor<InterestDailyPO, InterestDailyResultDTO> {

    private final ForexRateMapper forexRateMapper;
    private final IdGeneratorService idGeneratorService;

    @Override
    public InterestDailyResultDTO process(InterestDailyPO items) throws Exception {
        if(items == null){
            return null;
        }

        InterestDailyResultDTO result = new InterestDailyResultDTO();

        Long mainId = idGeneratorService.generateMainId();



        McAcuintTxnDtlPO mcAcStkTxnDtlPO = new McAcuintTxnDtlPO();
        McAcuintTxnPO mcAcuintTxnPO = new McAcuintTxnPO();
        mcAcStkTxnDtlPO.setAcuintTxnDtlId(idGeneratorService.generateDetailId());
        mcAcStkTxnDtlPO.setAcuintTxnId(mainId);
        mcAcStkTxnDtlPO.setTxnDate(items.getDate());
        mcAcStkTxnDtlPO.setCrAcuint(Objects.equals(items.getType(), "CR") ? items.getInterest() : BigDecimal.ZERO);
        mcAcStkTxnDtlPO.setDrAcuint(Objects.equals(items.getType(), "DR") ? items.getInterest() : BigDecimal.ZERO);
        mcAcStkTxnDtlPO.setLedgBal(items.getPrincipal());
        mcAcStkTxnDtlPO.setCat1DscntVal(BigDecimal.ZERO);
        mcAcStkTxnDtlPO.setCat2DscntVal(BigDecimal.ZERO);
        mcAcStkTxnDtlPO.setCat3DscntVal(BigDecimal.ZERO);
        mcAcStkTxnDtlPO.setAcuintTxnDtlStatCde("NORMAL");
        mcAcStkTxnDtlPO.setAcuintTxnTypCde("DC");
        mcAcStkTxnDtlPO.setRecVerNum(0L);
        mcAcStkTxnDtlPO.setInitTime(LocalDateTime.now());
        mcAcStkTxnDtlPO.setLastUpdTime(LocalDateTime.now());
        mcAcStkTxnDtlPO.setLastUpdBy("MIG");
        mcAcStkTxnDtlPO.setTagSeq(0L);
        mcAcStkTxnDtlPO.setNormalOdBal(BigDecimal.ZERO);
        mcAcStkTxnDtlPO.setPenalAcuint(Objects.equals(items.getType(), "PI") ? items.getInterest() : BigDecimal.ZERO);
        mcAcStkTxnDtlPO.setTtlSegrFundDv(BigDecimal.ZERO);
        mcAcStkTxnDtlPO.setTtlSameCcyDv(BigDecimal.ZERO);
        mcAcStkTxnDtlPO.setAlocSameCcySeqNum(0L);
        mcAcStkTxnDtlPO.setAlocSameCcyDv(BigDecimal.ZERO);
        mcAcStkTxnDtlPO.setUncoOdBySameCcy(BigDecimal.ZERO);
        mcAcStkTxnDtlPO.setRemnDv(BigDecimal.ZERO);
        mcAcStkTxnDtlPO.setExchRateToBaseCcy(items.getRateToBaseCcy());
        mcAcStkTxnDtlPO.setBaseCcyRemnDv(BigDecimal.ZERO);
        mcAcStkTxnDtlPO.setBaseCcyTtlCrsCcyDv(BigDecimal.ZERO);
        mcAcStkTxnDtlPO.setAlocCrsCcySeqNum(0L);
        mcAcStkTxnDtlPO.setBaseCcyAlocCrsCcyDv(BigDecimal.ZERO);
        mcAcStkTxnDtlPO.setExchRateFromBaseCcy(items.getRateFromBaseCcy());
        mcAcStkTxnDtlPO.setBaseCcyAlocCrsCcyDv(BigDecimal.ZERO);

        mcAcuintTxnPO.setAcuintTxnId(mainId);
        //2015-06-06 00:00:00.000
        mcAcuintTxnPO.setYearMth(LocalDateTime.parse(String.format("%d-%02d-01",items.getDate().getYear(),items.getDate().getMonthValue()) + "T00:00:00.000"));
//            mcAcuintTxnPO.setAcId(items.getClntCode());
        mcAcuintTxnPO.setAcId("02-0000389-30");
        mcAcuintTxnPO.setCmpnyCde("TFS");
        mcAcuintTxnPO.setCcyCde(PropertyConverUtils.standardizeCurrencyCode(items.getCcy()));
        mcAcuintTxnPO.setSegrFundId(1L);
//        mcAcuintTxnPO.setFromDate(items.getDate().withDayOfMonth(i+1));//唯一约束 不能相同
//        mcAcuintTxnPO.setFromDate(items.getDate().withDayOfMonth(ma+1));//唯一约束 不能相同
//            mcAcuintTxnPO.setIntcyFromDate(items.getDate()); //??
        mcAcuintTxnPO.setToDate(items.getDate());
        mcAcuintTxnPO.setPostDate(items.getPostDate());
        mcAcuintTxnPO.setCrAcuint(Objects.equals(items.getType(), "CR") ?items.getInterest() : BigDecimal.ZERO);
        mcAcuintTxnPO.setDrAcuint(Objects.equals(items.getType(), "DR") ?items.getInterest() : BigDecimal.ZERO);
        mcAcuintTxnPO.setAdjCrAcuint(items.getType().contains("ADJ") && items.getInterest().signum() >= 0 ? items.getInterest() : BigDecimal.ZERO);
        mcAcuintTxnPO.setAdjDrAcuint(items.getType().contains("ADJ") && items.getInterest().signum() < 0 ? items.getInterest() : BigDecimal.ZERO);
        mcAcuintTxnPO.setIsWaiveNeedPost("N");
//            mcAcuintTxnPO.setFundTxnId(items.getStatus() == "POST" ? );
        mcAcuintTxnPO.setAcuintTxnStatCde("NEW".equals(items.getStatus()) ? "PENDING" : "POST".equals(items.getStatus()) ? "POSTED" : items.getStatus());
        mcAcuintTxnPO.setRecVerNum(0L);
        mcAcuintTxnPO.setInitTime(LocalDateTime.now());
        mcAcuintTxnPO.setLastUpdTime(LocalDateTime.now());
        mcAcuintTxnPO.setLastUpdBy("MIG");
        mcAcuintTxnPO.setTagSeq(0L);
        mcAcuintTxnPO.setIntcyTypCde("DEFAULT");
        mcAcuintTxnPO.setRemrk(items.getReferenceNo());
        mcAcuintTxnPO.setIsOnHoldPost("N");
        mcAcuintTxnPO.setPenalAcuint(Objects.equals(items.getType(), "PI") ?items.getInterest() : BigDecimal.ZERO);

        result.setMainRecord(mcAcStkTxnDtlPO);
        result.setDetailRecord(mcAcuintTxnPO);

        return result;
    }
}
