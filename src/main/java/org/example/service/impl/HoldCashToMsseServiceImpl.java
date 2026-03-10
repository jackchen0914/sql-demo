package org.example.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.example.mapper.*;
import org.example.pojo.*;
import org.example.service.HoldCashToMsseService;
import org.example.service.IdGeneratorService;
import org.example.utils.PropertyConverUtils;
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
public class HoldCashToMsseServiceImpl implements HoldCashToMsseService {

    private final McAcFundHoldRecMapper mcAcFundHoldRecMapper;
    private final McAcFundHoldTxnMapper mcAcFundHoldTxnMapper;

    private final CashVoucherMapper cashVoucherMapper;
    private final HoldCashMapMSSEMapper holdCashMapMSSEMapper;
    private final TransactionTypesMapper transactionTypesMapper;
    private final IdGeneratorService idGeneratorService;

    @Override
    public String writeProcessedData() {
        List<CashVoucherPO> cashVoucherPOS = cashVoucherMapper.selectStatusFlagIsYByPage(1,10);
        List<McAcFundHoldRecPO> mcAcFundHoldRecPOList = new ArrayList<>();
        List<McAcFundHoldTxnPO> mcAcFundHoldTxnPOList = new ArrayList<>();
        List<HoldCashMapMSSEPO> holdCashMapMSSEPOList = new ArrayList<>();
        for (int i = 0; i < cashVoucherPOS.size(); i++) {
            CashVoucherPO items = cashVoucherPOS.get(i);
            Long mainId = idGeneratorService.generateMainId();
            Long acFundHoldRid = idGeneratorService.generateDetailId();

            McAcFundHoldRecPO mcAcFundHoldRecPO = new McAcFundHoldRecPO();
            McAcFundHoldTxnPO mcAcFundHoldTxnPO = new McAcFundHoldTxnPO();
            HoldCashMapMSSEPO holdCashMapMSSEPO = new HoldCashMapMSSEPO();

            mcAcFundHoldRecPO.setAcFundHoldRid(acFundHoldRid);
            mcAcFundHoldRecPO.setCmpnyIbusdate(items.getInputDate());
            mcAcFundHoldRecPO.setCmpnyBusdate(items.getVoucherDate());
//            mcAcFundHoldRecPO.setAcId(items.getClnt()); //02-0000389-30
            mcAcFundHoldRecPO.setAcId("02-0000389-30");
            mcAcFundHoldRecPO.setSegrFundId(1L);
            mcAcFundHoldRecPO.setCcyCde(PropertyConverUtils.standardizeCurrencyCode(items.getCcy()));
            mcAcFundHoldRecPO.setCmpnyCde("TFS");
            mcAcFundHoldRecPO.setTxnTypId(txnTypIdValueConvert(items.getTxnType()));
            mcAcFundHoldRecPO.setRefNum("HF"+items.getVoucherNo());
            mcAcFundHoldRecPO.setHoldAmt(items.getAmount().abs());
            mcAcFundHoldRecPO.setExprDate(items.getValueDate());
            mcAcFundHoldRecPO.setFundHoldTyp("ALL");
            mcAcFundHoldRecPO.setFundHoldStat(fundHoldStatConvert(items.getStatusFlag()));
            mcAcFundHoldRecPO.setFundChnlCde(Objects.equals(items.getManualInput(), "Yes") ? "MANUALINP" : "SYSTEM");
            mcAcFundHoldRecPO.setExtrnlRefNum(items.getMarket()+items.getVoucherNo());
            mcAcFundHoldRecPO.setRemrk(items.getRemark());
            mcAcFundHoldRecPO.setHoldDate(items.getVoucherDate());
            mcAcFundHoldRecPO.setHoldBy(items.getUserid());
            mcAcFundHoldRecPO.setRelseDate(Objects.equals(accordingStatusValueConvert(items.getStatusFlag()), "Y") ? items.getValueDate() : null);
            mcAcFundHoldRecPO.setRelseBy(Objects.equals(accordingStatusValueConvert(items.getStatusFlag()), "Y") ? items.getUserid() : null);
            mcAcFundHoldRecPO.setRelseAmt(Objects.equals(accordingStatusValueConvert(items.getStatusFlag()), "Y") ? items.getAmount() : BigDecimal.ZERO);
            mcAcFundHoldRecPO.setLastRelseDate(items.getValueDate());
            mcAcFundHoldRecPO.setLastRelseBy(items.getUserid());
            mcAcFundHoldRecPO.setAcFundHoldTxnId(mainId);
            mcAcFundHoldRecPO.setIsInstrRelt("N");
            mcAcFundHoldRecPO.setIsPost(items.getStatusFlag().charAt(8) == 'Y' ? "Y" : "N");
            mcAcFundHoldRecPO.setRecVerNum(0L);
            mcAcFundHoldRecPO.setInitTime(LocalDateTime.now());
            mcAcFundHoldRecPO.setLastUpdTime(LocalDateTime.now());
            mcAcFundHoldRecPO.setLastUpdBy("MIG");
            mcAcFundHoldRecPO.setTagSeq(0L);

            mcAcFundHoldTxnPO.setAcFundHoldTxnId(mainId);
            mcAcFundHoldTxnPO.setFundHoldStat(fundHoldStatConvert(items.getStatusFlag()));
            mcAcFundHoldTxnPO.setRecVerNum(0L);
            mcAcFundHoldTxnPO.setInitTime(LocalDateTime.now());
            mcAcFundHoldTxnPO.setLastUpdTime(LocalDateTime.now());
            mcAcFundHoldTxnPO.setLastUpdBy("MIG");
            mcAcFundHoldTxnPO.setTagSeq(0L);

            holdCashMapMSSEPO.setVoucherNo(items.getVoucherNo());
            holdCashMapMSSEPO.setMarket(items.getMarket());
            holdCashMapMSSEPO.setAcFundHoldRid(acFundHoldRid);
            holdCashMapMSSEPO.setAcFundHoldTxnId(mainId);

            holdCashMapMSSEPOList.add(holdCashMapMSSEPO);
            mcAcFundHoldRecPOList.add(mcAcFundHoldRecPO);
            mcAcFundHoldTxnPOList.add(mcAcFundHoldTxnPO);
        }
        holdCashMapMSSEMapper.insert(holdCashMapMSSEPOList);
        saveToOracleMcAcFundHoldTxn(mcAcFundHoldTxnPOList);
        saveToOracleMcAcFundHoldRec(mcAcFundHoldRecPOList);
        return "ok";
    }

    private Long txnTypIdValueConvert(String type){
        TransactionTypesPO transactionTypesPO = transactionTypesMapper.selectTxnTypeCode(type);
        return transactionTypesPO.getSignIndicator().equals("C") ? 8513L : 3513L;
    }

    private String accordingStatusValueConvert(String flag) {
        if(StringUtils.isBlank(flag) || flag.length() < 8) return null;
        if(flag.charAt(8) == 'Y'){
            return "Y";
        }
        return "N";
    }


    private String fundHoldStatConvert(String statusFlag) {
        if(StringUtils.isBlank(statusFlag) || statusFlag.length() < 7) return null;
        if(statusFlag.charAt(7) == 'Y'){
            return "HOLD";
        }else if(statusFlag.charAt(8) == 'Y'){
            return "RELEASED";
        }
        return "N";
    }


    @DS("oracle")
    @Transactional(rollbackFor = Exception.class)
    public void saveToOracleMcAcFundHoldRec(List<McAcFundHoldRecPO> list){
        mcAcFundHoldRecMapper.batchInsert(list);
    }

    @DS("oracle")
    @Transactional(rollbackFor = Exception.class)
    public void saveToOracleMcAcFundHoldTxn(List<McAcFundHoldTxnPO> list){
        mcAcFundHoldTxnMapper.batchInsert(list);
    }

}
