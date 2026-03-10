package org.example.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.*;
import org.example.pojo.*;
import org.example.service.IdGeneratorService;
import org.example.service.InterestDailyToMsseService;
import org.example.utils.PropertyConverUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JackChen
 * @since 2025-12-25
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class InterestDailyToMsseServiceImpl implements InterestDailyToMsseService {
    
    private final InterestDailyMapper interestDailyMapper;
    private final ForexRateMapper forexRateMapper;
    private final InterestDailyMapMSSEMapper interestDailyMapMSSEMapper;

    private final McAcuintTxnDtlMapper mcAcuintTxnDtlMapper;
    private final McAcuintTxnMapper mcAcuintTxnMapper;

    private final IdGeneratorService idGeneratorService;

    @Override
    public String writeProcessedData() {
        List<InterestDailyPO> interestDailyPOS = interestDailyMapper.selectByPage(1,10);
        
        List<McAcuintTxnDtlPO> mcAcuintTxnDtlPOList = new ArrayList<>();
        List<McAcuintTxnPO> mcAcuintTxnPOList = new ArrayList<>();
        List<InterestDailyMapMSSEPO> interestDailyMapMSSEPOList = new ArrayList<>();
        for (int i = 0; i < interestDailyPOS.size(); i++) {
            InterestDailyPO items = interestDailyPOS.get(i);
            Long mainId = idGeneratorService.generateMainId();
            Long acuintTxnDtlId = idGeneratorService.generateDetailId();

            McAcuintTxnDtlPO mcAcStkTxnDtlPO = new McAcuintTxnDtlPO();
            McAcuintTxnPO mcAcuintTxnPO = new McAcuintTxnPO();
            InterestDailyMapMSSEPO interestDailyMapMSSEPO = new InterestDailyMapMSSEPO();

            mcAcStkTxnDtlPO.setAcuintTxnDtlId(acuintTxnDtlId);
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
            mcAcStkTxnDtlPO.setExchRateToBaseCcy(findRateToBaseCcyValue(items));
            mcAcStkTxnDtlPO.setBaseCcyRemnDv(BigDecimal.ZERO);
            mcAcStkTxnDtlPO.setBaseCcyTtlCrsCcyDv(BigDecimal.ZERO);
            mcAcStkTxnDtlPO.setAlocCrsCcySeqNum(0L);
            mcAcStkTxnDtlPO.setBaseCcyAlocCrsCcyDv(BigDecimal.ZERO);
            mcAcStkTxnDtlPO.setExchRateFromBaseCcy(findRateFromBaseCcyValue(items));
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
//        mcAcuintTxnPO.setFromDate(LocalDateTime.parse("2025-01-01T00:00:00.000"));
            mcAcuintTxnPO.setFromDate(items.getDate());
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

            interestDailyMapMSSEPO.setDate(items.getDate());
            interestDailyMapMSSEPO.setClntCode(items.getClntCode());
            interestDailyMapMSSEPO.setAcctType(items.getAcctType());
            interestDailyMapMSSEPO.setMarket(items.getMarket());
            interestDailyMapMSSEPO.setCcy(items.getCcy());
            interestDailyMapMSSEPO.setBankAccount(items.getBankAccount());
            interestDailyMapMSSEPO.setType(items.getType());
            interestDailyMapMSSEPO.setAcuintTxnDtlId(acuintTxnDtlId);
            interestDailyMapMSSEPO.setAcuintTxnId(mainId);
            interestDailyMapMSSEPO.setTxnDate(items.getDate());
            interestDailyMapMSSEPO.setAcId("02-0000389-30");
            interestDailyMapMSSEPO.setCcyCde(PropertyConverUtils.standardizeCurrencyCode(items.getCcy()));

            interestDailyMapMSSEPOList.add(interestDailyMapMSSEPO);
            mcAcuintTxnDtlPOList.add(mcAcStkTxnDtlPO);
            mcAcuintTxnPOList.add(mcAcuintTxnPO);
        }
        interestDailyMapMSSEMapper.insert(interestDailyMapMSSEPOList);
        saveToOracleMcAcuintTxn(mcAcuintTxnPOList);
        saveToOracleMcAcuintTxnDtl(mcAcuintTxnDtlPOList);
        return "ok";
    }

    @DS("oracle")
    @Transactional(rollbackFor = Exception.class)
    public void saveToOracleMcAcuintTxnDtl(List<McAcuintTxnDtlPO> list){
        mcAcuintTxnDtlMapper.batchInsert(list);
    }

    @DS("oracle")
    @Transactional(rollbackFor = Exception.class)
    public void saveToOracleMcAcuintTxn(List<McAcuintTxnPO> list){
        mcAcuintTxnMapper.batchInsert(list);
    }

    private BigDecimal findRateFromBaseCcyValue(InterestDailyPO po) {
        ForexRatePO forexRatePO = forexRateMapper.selectRateByDate(po.getCcy(), String.valueOf(po.getDate()).replaceAll("T"," "));
        return forexRatePO.getXRate() != null ? BigDecimal.ONE.divide(forexRatePO.getXRate(),4,RoundingMode.HALF_UP) : null;
    }

    private BigDecimal findRateToBaseCcyValue(InterestDailyPO po) {
        ForexRatePO forexRatePO = forexRateMapper.selectRateByDate(po.getCcy(), String.valueOf(po.getDate()).replaceAll("T"," "));
        return forexRatePO.getXRate() != null ? forexRatePO.getXRate() : null;
    }
}
