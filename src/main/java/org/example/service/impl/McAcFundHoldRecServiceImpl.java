package org.example.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.example.mapper.CashVoucherMapper;
import org.example.mapper.McAcFundHoldRecMapper;
import org.example.mapper.McAcFundHoldTxnMapper;
import org.example.pojo.*;
import org.example.service.IMcAcFundHoldRecService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JackChen
 * @since 2025-12-23
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class McAcFundHoldRecServiceImpl extends ServiceImpl<McAcFundHoldRecMapper, McAcFundHoldRecPO> implements IMcAcFundHoldRecService {

    private final CashVoucherMapper cashVoucherMapper;

    private final McAcFundHoldRecMapper mcAcFundHoldRecMapper;

    private final McAcFundHoldTxnMapper mcAcFundHoldTxnMapper;

    @Override
    public void writeProcessedData() {
        List<CashVoucherPO> cashVoucherPOS = cashVoucherMapper.selectTop5();
        List<McAcFundHoldRecPO> fundHoldRecList = new ArrayList<>();
        List<McAcFundHoldTxnPO> fundHoldTxnList = new ArrayList<>();
        for (int i = 0; i < cashVoucherPOS.size(); i++) {
            CashVoucherPO po = cashVoucherPOS.get(i);
            McAcFundHoldRecPO mcAcFundHoldRecPO = new McAcFundHoldRecPO();
            McAcFundHoldTxnPO mcAcFundHoldTxnPO = new McAcFundHoldTxnPO();
            mcAcFundHoldRecPO.setAcFundHoldRid((long) (15900000 + i));
            mcAcFundHoldRecPO.setCmpnyIbusdate(po.getInputDate());
            mcAcFundHoldRecPO.setCmpnyBusdate(po.getVoucherDate());
//            mcAcFundHoldRecPO.setAcId(po.getClnt()); //02-0000389-30
            mcAcFundHoldRecPO.setAcId("02-0000389-30");
            mcAcFundHoldRecPO.setSegrFundId(1L);
            mcAcFundHoldRecPO.setCcyCde(currencyConversion(po));
            mcAcFundHoldRecPO.setCmpnyCde("TFS");
//            mcAcFundHoldRecPO.setTxnTypId();
            mcAcFundHoldRecPO.setRefNum("HF"+po.getVoucherNo());
            mcAcFundHoldRecPO.setHoldAmt(po.getAmount());
            mcAcFundHoldRecPO.setExprDate(po.getValueDate());
            mcAcFundHoldRecPO.setFundHoldTyp("ALL");
            mcAcFundHoldRecPO.setFundHoldStat(fundHoldStatConver(po.getStatusFlag()));
            mcAcFundHoldRecPO.setFundChnlCde(Objects.equals(po.getManualInput(), "Yes") ? "MANUALINP" : "SYSTEM");
            mcAcFundHoldRecPO.setRemrk(po.getRemark());
            mcAcFundHoldRecPO.setHoldDate(po.getVoucherDate());
            mcAcFundHoldRecPO.setHoldBy(po.getUserid());
            mcAcFundHoldRecPO.setRelseDate(Objects.equals(accordingStatusConverVal(po.getStatusFlag()), "Y") ? po.getValueDate() : null);
            mcAcFundHoldRecPO.setRelseBy(Objects.equals(accordingStatusConverVal(po.getStatusFlag()), "Y") ? po.getUserid() : null);
            mcAcFundHoldRecPO.setRelseAmt(Objects.equals(accordingStatusConverVal(po.getStatusFlag()), "Y") ? po.getAmount() : BigDecimal.ZERO);
            mcAcFundHoldRecPO.setLastRelseDate(po.getValueDate());
            mcAcFundHoldRecPO.setLastRelseBy(po.getUserid());
            mcAcFundHoldRecPO.setAcFundHoldTxnId((long) (16000000 + i));
            mcAcFundHoldRecPO.setIsInstrRelt("N");
            mcAcFundHoldRecPO.setIsPost(po.getStatusFlag().charAt(8) == 'Y' ? "Y" : "N");
            mcAcFundHoldRecPO.setRecVerNum(0L);
            mcAcFundHoldRecPO.setInitTime(LocalDateTime.now());
            mcAcFundHoldRecPO.setLastUpdTime(LocalDateTime.now());
            mcAcFundHoldRecPO.setLastUpdBy("MIG");
            mcAcFundHoldRecPO.setTagSeq(0L);

            mcAcFundHoldTxnPO.setAcFundHoldTxnId((long) (16000000 + i));
            mcAcFundHoldTxnPO.setFundHoldStat(fundHoldStatConver(po.getStatusFlag()));
            mcAcFundHoldTxnPO.setRecVerNum(0L);
            mcAcFundHoldTxnPO.setInitTime(LocalDateTime.now());
            mcAcFundHoldTxnPO.setLastUpdTime(LocalDateTime.now());
            mcAcFundHoldTxnPO.setLastUpdBy("MIG");
            mcAcFundHoldTxnPO.setTagSeq(0L);
            fundHoldRecList.add(mcAcFundHoldRecPO);
            fundHoldTxnList.add(mcAcFundHoldTxnPO);
        }
        saveToOracleMcAcFundHoldTxn(fundHoldTxnList);
        saveToOracleMcAcFundHoldRec(fundHoldRecList);
    }

    private String accordingStatusConverVal(String flag) {
        if(StringUtils.isBlank(flag) || flag.length() < 8) return null;
        if(flag.charAt(8) == 'Y'){
            return "Y";
        }
        return "N";
    }


    private String fundHoldStatConver(String statusFlag) {
        if(StringUtils.isBlank(statusFlag) || statusFlag.length() < 7) return null;
        if(statusFlag.charAt(7) == 'Y'){
            return "HOLD";
        }else if(statusFlag.charAt(8) == 'Y'){
            return "RELEASED";
        }
        return "N";
    }


    @DS("oracle")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveToOracleMcAcFundHoldRec(List<McAcFundHoldRecPO> list){
        mcAcFundHoldRecMapper.insert(list);
    }

    @DS("oracle")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveToOracleMcAcFundHoldTxn(List<McAcFundHoldTxnPO> list){
        mcAcFundHoldTxnMapper.insert(list);
    }

    private String currencyConversion(CashVoucherPO po) {
        if (po.getCcy().contains("RMB")) {
            return "CNY";
        } else if (po.getCcy().contains("YEN")) {
            return "JPY";
        } else return po.getCcy();
    }
}
