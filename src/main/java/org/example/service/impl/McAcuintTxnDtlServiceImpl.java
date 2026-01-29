package org.example.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.ForexRateMapper;
import org.example.mapper.InterestDailyMapper;
import org.example.mapper.McAcuintTxnDtlMapper;
import org.example.mapper.McAcuintTxnMapper;
import org.example.pojo.*;
import org.example.service.IMcAcuintTxnDtlService;
import org.example.utils.PropertyConverUtils;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
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
public class McAcuintTxnDtlServiceImpl extends ServiceImpl<McAcuintTxnDtlMapper, McAcuintTxnDtlPO> implements IMcAcuintTxnDtlService {
    
    private final InterestDailyMapper interestDailyMapper;

    private final ForexRateMapper forexRateMapper;

    private final McAcuintTxnDtlMapper mcAcuintTxnDtlMapper;

    private final McAcuintTxnMapper mcAcuintTxnMapper;

    @Override
    public void writeProcessedData() {
        List<InterestDailyPO> interestDailyPOS = interestDailyMapper.selectTop5();
        
        List<McAcuintTxnDtlPO> mcAcuintTxnDtlPOList = new ArrayList<>();
        List<McAcuintTxnPO> mcAcuintTxnPOList = new ArrayList<>();
        for (int i = 0; i < interestDailyPOS.size(); i++) {
            InterestDailyPO po = interestDailyPOS.get(i);

            McAcuintTxnDtlPO mcAcStkTxnDtlPO = new McAcuintTxnDtlPO();
            McAcuintTxnPO mcAcuintTxnPO = new McAcuintTxnPO();
            mcAcStkTxnDtlPO.setAcuintTxnDtlId((long) (340000000 + i));
            mcAcStkTxnDtlPO.setAcuintTxnId((long) (15200000 + i));
            mcAcStkTxnDtlPO.setTxnDate(po.getDate());
            mcAcStkTxnDtlPO.setCrAcuint(Objects.equals(po.getType(), "CR") ? po.getInterest() : BigDecimal.ZERO);
            mcAcStkTxnDtlPO.setDrAcuint(Objects.equals(po.getType(), "DR") ? po.getInterest() : BigDecimal.ZERO);
            mcAcStkTxnDtlPO.setLedgBal(po.getPrincipal());
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
            mcAcStkTxnDtlPO.setPenalAcuint(Objects.equals(po.getType(), "PI") ? po.getInterest() : BigDecimal.ZERO);
            mcAcStkTxnDtlPO.setTtlSegrFundDv(BigDecimal.ZERO);
            mcAcStkTxnDtlPO.setTtlSameCcyDv(BigDecimal.ZERO);
            mcAcStkTxnDtlPO.setAlocSameCcySeqNum(0L);
            mcAcStkTxnDtlPO.setAlocSameCcyDv(BigDecimal.ZERO);
            mcAcStkTxnDtlPO.setUncoOdBySameCcy(BigDecimal.ZERO);
            mcAcStkTxnDtlPO.setRemnDv(BigDecimal.ZERO);
            mcAcStkTxnDtlPO.setExchRateToBaseCcy(findRateProcess(po));
            mcAcStkTxnDtlPO.setBaseCcyRemnDv(BigDecimal.ZERO);
            mcAcStkTxnDtlPO.setBaseCcyTtlCrsCcyDv(BigDecimal.ZERO);
            mcAcStkTxnDtlPO.setAlocCrsCcySeqNum(0L);
            mcAcStkTxnDtlPO.setBaseCcyAlocCrsCcyDv(BigDecimal.ZERO);
            mcAcStkTxnDtlPO.setExchRateFromBaseCcy(findRateProcessBase(po));
            mcAcStkTxnDtlPO.setBaseCcyAlocCrsCcyDv(BigDecimal.ZERO);

            mcAcuintTxnPO.setAcuintTxnId((long) (15200000 + i));
            //2015-06-06 00:00:00.000
            mcAcuintTxnPO.setYearMth(LocalDateTime.parse(String.format("%d-%02d-01",po.getDate().getYear(),po.getDate().getMonthValue()) + "T00:00:00.000"));
//            mcAcuintTxnPO.setAcId(po.getClntCode());
            mcAcuintTxnPO.setAcId("02-0000389-30");
            mcAcuintTxnPO.setCmpnyCde("TFS");
            mcAcuintTxnPO.setCcyCde(PropertyConverUtils.standardizeCurrencyCode(po.getCcy()));
            mcAcuintTxnPO.setSegrFundId(1L);
            mcAcuintTxnPO.setFromDate(po.getDate().withDayOfMonth(i+1));//唯一约束 不能相同
//            mcAcuintTxnPO.setIntcyFromDate(po.getDate()); //??
            mcAcuintTxnPO.setToDate(po.getDate());
            mcAcuintTxnPO.setPostDate(po.getPostDate());
            mcAcuintTxnPO.setCrAcuint(Objects.equals(po.getType(), "CR") ?po.getInterest() : BigDecimal.ZERO);
            mcAcuintTxnPO.setDrAcuint(Objects.equals(po.getType(), "DR") ?po.getInterest() : BigDecimal.ZERO);
            mcAcuintTxnPO.setAdjCrAcuint(po.getType().contains("ADJ") && po.getInterest().signum() >= 0 ? po.getInterest() : BigDecimal.ZERO);
            mcAcuintTxnPO.setAdjDrAcuint(po.getType().contains("ADJ") && po.getInterest().signum() < 0 ? po.getInterest() : BigDecimal.ZERO);
            mcAcuintTxnPO.setIsWaiveNeedPost("N");
//            mcAcuintTxnPO.setFundTxnId(po.getStatus() == "POST" ? );
            mcAcuintTxnPO.setAcuintTxnStatCde("NEW".equals(po.getStatus()) ? "PENDING" : "POST".equals(po.getStatus()) ? "POSTED" : po.getStatus());
            mcAcuintTxnPO.setRecVerNum(0L);
            mcAcuintTxnPO.setInitTime(LocalDateTime.now());
            mcAcuintTxnPO.setLastUpdTime(LocalDateTime.now());
            mcAcuintTxnPO.setLastUpdBy("MIG");
            mcAcuintTxnPO.setTagSeq(0L);
            mcAcuintTxnPO.setIntcyTypCde("DEFAULT");
            mcAcuintTxnPO.setRemrk(po.getReferenceNo());
            mcAcuintTxnPO.setIsOnHoldPost("N");
            mcAcuintTxnPO.setPenalAcuint(Objects.equals(po.getType(), "PI") ?po.getInterest() : BigDecimal.ZERO);
            mcAcuintTxnDtlPOList.add(mcAcStkTxnDtlPO);
            mcAcuintTxnPOList.add(mcAcuintTxnPO);
        }
        saveToOracleMcAcuintTxn(mcAcuintTxnPOList);
        saveToOracleMcAcuintTxnDtl(mcAcuintTxnDtlPOList);
    }

    @DS("oracle")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveToOracleMcAcuintTxnDtl(List<McAcuintTxnDtlPO> list){
        mcAcuintTxnDtlMapper.insert(list);
    }

    @DS("oracle")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveToOracleMcAcuintTxn(List<McAcuintTxnPO> list){
        mcAcuintTxnMapper.insert(list);
    }

    private BigDecimal findRateProcessBase(InterestDailyPO po) {
//        ForexRatePO forexRatePO = forexRateMapper.selectRateByDate(po.getCcy(), String.valueOf(po.getDate()).split("T")[0]);
        ForexRatePO forexRatePO = forexRateMapper.selectRateByDate(po.getCcy(), String.valueOf(po.getDate()).replaceAll("T"," "));
        return forexRatePO.getXRate() != null ? BigDecimal.ONE.divide(forexRatePO.getXRate(),4,RoundingMode.HALF_UP) : null;
    }

    private BigDecimal findRateProcess(InterestDailyPO po) {
//        ForexRatePO forexRatePO = forexRateMapper.selectRateByDate(po.getCcy(), String.valueOf(po.getDate()));
        ForexRatePO forexRatePO = forexRateMapper.selectRateByDate(po.getCcy(), String.valueOf(po.getDate()).replaceAll("T"," "));
        return forexRatePO.getXRate() != null ? forexRatePO.getXRate() : null;
    }
}
