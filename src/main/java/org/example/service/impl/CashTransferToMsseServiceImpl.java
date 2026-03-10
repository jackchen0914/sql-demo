package org.example.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.*;
import org.example.pojo.*;
import org.example.pojo.dtos.CashTransferAllDTO;
import org.example.service.CashTransferToMsseService;
import org.example.service.IdGeneratorService;
import org.example.utils.PropertyConverUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
 * @since 2025-12-25
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CashTransferToMsseServiceImpl implements CashTransferToMsseService {

    private final McAcOnlineFxReqMapper mcAcOnlineFxReqMapper;
    private final McAcFuntrfCcyexRecMapper mcAcFuntrfCcyexRecMapper;
    private final McAcFuntrfCcyexReqMapper mcAcFuntrfCcyexReqMapper;
    private final McAcFundGenStmtRemrkMapper mcAcFundGenStmtRemrkMapper;

    private final CashTransferMapper cashTransferMapper;
    private final ForexRateMapper forexRateMapper;
    private final CashTransferMapMSSEMapper cashTransferMapMSSEMapper;

    private final IdGeneratorService idGeneratorService;

    @Override
    public String writeProcessedData() {
        List<CashTransferAllDTO> instrumentVoucherPOS = cashTransferMapper.selectCashTransferAll(1,10);
        List<McAcOnlineFxReqPO> mcAcOnlineFxReqPOList = new ArrayList<>();
        List<McAcFuntrfCcyexRecPO> mcAcFuntrfCcyexRecPOList = new ArrayList<>();
        List<McAcFuntrfCcyexReqPO> mcAcFuntrfCcyexReqPOList = new ArrayList<>();
        List<McAcFundGenStmtRemrkPO> mcAcFundGenStmtRemrkPOList = new ArrayList<>();
        List<CashTransferMapMSSEPO> cashTransferMapMSSEPOList = new ArrayList<>();
        for (int i = 0; i < instrumentVoucherPOS.size(); i++) {
            CashTransferAllDTO items = instrumentVoucherPOS.get(i);
            Long mainId = idGeneratorService.generateMainId();
            Long acOnlineFxReqId = idGeneratorService.generateDetailId();
            Long acFuntrfCcyexReqId = idGeneratorService.generateDetailId();

            McAcOnlineFxReqPO mcAcOnlineFxReqPO = new McAcOnlineFxReqPO();
            McAcFuntrfCcyexRecPO mcAcFuntrfCcyexRecPO = new McAcFuntrfCcyexRecPO();
            McAcFuntrfCcyexReqPO mcAcFuntrfCcyexReqPO = new McAcFuntrfCcyexReqPO();
            McAcFundGenStmtRemrkPO mcAcFundGenStmtRemrkPO = new McAcFundGenStmtRemrkPO();
            CashTransferMapMSSEPO cashTransferMapMSSEPO = new CashTransferMapMSSEPO();

            mcAcOnlineFxReqPO.setAcOnlineFxReqId(acOnlineFxReqId);
            mcAcOnlineFxReqPO.setCmpnyCde("TFS");
            mcAcOnlineFxReqPO.setCmpnyIbusdate(items.getTransferDate());
            mcAcOnlineFxReqPO.setCmpnyBusdate(items.getTransferDate());
//        mcAcOnlineFxReqPO.setFromAcId(items.getClntCodeFrom());
            mcAcOnlineFxReqPO.setFromAcId("02-0000389-30");
            mcAcOnlineFxReqPO.setFromSegrFundId(1L);
            mcAcOnlineFxReqPO.setFromCcyCde(PropertyConverUtils.standardizeCurrencyCode(items.getCCYFrom()));
            mcAcOnlineFxReqPO.setFromAmt(items.getAmountFrom());
//        mcAcOnlineFxReqPO.setToAcId(items.getClntCodeTo());
            mcAcOnlineFxReqPO.setToAcId("02-0000389-30");
            mcAcOnlineFxReqPO.setToSegrFundId(1L);
            mcAcOnlineFxReqPO.setToCcyCde(PropertyConverUtils.standardizeCurrencyCode(items.getCCYTo()));
            mcAcOnlineFxReqPO.setToAmt(items.getAmountTo());
            mcAcOnlineFxReqPO.setValDate(items.getTransferDate());
            mcAcOnlineFxReqPO.setExchRate(items.getFXRate());

            mcAcOnlineFxReqPO.setAprvCmpnyIbusdate(Objects.equals(items.getStatus(), "APP") ? items.getApprovalTimeFrom() : null);
            mcAcOnlineFxReqPO.setAprvCmpnyBusdate(Objects.equals(items.getStatus(), "APP") ? items.getApprovalTimeFrom() : null);
            mcAcOnlineFxReqPO.setAprvTime(Objects.equals(items.getStatus(), "APP") ? items.getApprovalTimeFrom() : null);
            mcAcOnlineFxReqPO.setCanclCmpnyIbusdate(Objects.equals(items.getStatus(), "CAN") ? items.getCancelDateFrom() : null);
            mcAcOnlineFxReqPO.setCanclCmpnyBusdate(Objects.equals(items.getStatus(), "CAN") ? items.getCancelDateFrom() : null);
            mcAcOnlineFxReqPO.setCanclTime(Objects.equals(items.getStatus(), "CAN") ? items.getCancelDateFrom() : null);
            mcAcOnlineFxReqPO.setFxStatCde(fxStatCodeConver(items.getStatus()));
            mcAcOnlineFxReqPO.setRecVerNum(0L);
            mcAcOnlineFxReqPO.setInitTime(LocalDateTime.now());
            mcAcOnlineFxReqPO.setLastUpdTime(LocalDateTime.now());
            mcAcOnlineFxReqPO.setLastUpdBy("MIG");
            mcAcOnlineFxReqPO.setTagSeq(0L);

            mcAcFuntrfCcyexRecPO.setAcFuntrfCcyexRid(mainId);
            mcAcFuntrfCcyexRecPO.setCmpnyIbusdate(items.getTransferDate());
            mcAcFuntrfCcyexRecPO.setCmpnyBusdate(items.getTransferDate());
//        mcAcFuntrfCcyexRecPO.setAcId(items.getClntCodeFrom());
            mcAcFuntrfCcyexRecPO.setAcId("02-0000389-30");
            mcAcFuntrfCcyexRecPO.setSegrFundId(1L);
            mcAcFuntrfCcyexRecPO.setCcyCde(PropertyConverUtils.standardizeCurrencyCode(items.getCCYFrom()));
            mcAcFuntrfCcyexRecPO.setCmpnyCde("TFS");
            mcAcFuntrfCcyexRecPO.setFuntrfCcyexTypCde("CCYEXCH");
            mcAcFuntrfCcyexRecPO.setValDate(items.getTransferDate());
            mcAcFuntrfCcyexRecPO.setAmt(items.getAmountFrom());
            mcAcFuntrfCcyexRecPO.setIsPrtRcpt("N");
            mcAcFuntrfCcyexRecPO.setExchRateTypCde("INDIRECT");
            mcAcFuntrfCcyexRecPO.setExchRate(items.getFXRate());
            mcAcFuntrfCcyexRecPO.setRndMtd("ROUNDING");
//        mcAcFuntrfCcyexRecPO.setToAcId(items.getClntCodeTo());
            mcAcFuntrfCcyexRecPO.setToAcId("02-0000389-30");
            mcAcFuntrfCcyexRecPO.setToSegrFundId(1L);
            mcAcFuntrfCcyexRecPO.setToCmpnyCde("TFS");
            mcAcFuntrfCcyexRecPO.setToCcyCde(PropertyConverUtils.standardizeCurrencyCode(items.getCCYTo()));
            mcAcFuntrfCcyexRecPO.setFuntrfCcyexStatCde("APPROVED");
            mcAcFuntrfCcyexRecPO.setFundChnlCde("MANUALINP");
            mcAcFuntrfCcyexRecPO.setIsRev("N");
            mcAcFuntrfCcyexRecPO.setIsUnderRevrse("N");
            mcAcFuntrfCcyexRecPO.setIsTodayRev("N");
            mcAcFuntrfCcyexRecPO.setBaseCcyEquAmt(findBaseCcyEquAmtValue(items));
            mcAcFuntrfCcyexRecPO.setBaseCcyEquToAmt(findBaseCcyEquToAmtValue(items));
            mcAcFuntrfCcyexRecPO.setIsIgnrDatSync("N");
            mcAcFuntrfCcyexRecPO.setIsAutoAprv("N");
//        mcAcFuntrfCcyexRecPO.setRvisUnit("0001");
            mcAcFuntrfCcyexRecPO.setRvisUnit("0031");
            mcAcFuntrfCcyexRecPO.setRvisBy("MIG");
            mcAcFuntrfCcyexRecPO.setChkBy("MIG");
            mcAcFuntrfCcyexRecPO.setAprvRejCmpnyIbusdate(items.getApprovalTimeFrom());
            mcAcFuntrfCcyexRecPO.setAprvRejCmpnyBusdate(items.getApprovalTimeFrom());
            mcAcFuntrfCcyexRecPO.setAprvRejTime(items.getApprovalTimeFrom());
            mcAcFuntrfCcyexRecPO.setIsTrnfr("N");
            mcAcFuntrfCcyexRecPO.setIsCcyex("Y");
            mcAcFuntrfCcyexRecPO.setRecVerNum(0L);
            mcAcFuntrfCcyexRecPO.setInitTime(LocalDateTime.now());
            mcAcFuntrfCcyexRecPO.setLastUpdTime(LocalDateTime.now());
            mcAcFuntrfCcyexRecPO.setLastUpdBy("MIG");
            mcAcFuntrfCcyexRecPO.setTagSeq(0L);
            mcAcFuntrfCcyexRecPO.setInitUser("MIG");
//        mcAcFuntrfCcyexRecPO.setInitUserUnit("0001");
            mcAcFuntrfCcyexRecPO.setInitUserUnit("0031");
            mcAcFuntrfCcyexRecPO.setExtrnlRefNum(String.valueOf(items.getSeqNo()));
            mcAcFuntrfCcyexRecPO.setExtrnlSysCde("OctOBack");

            mcAcFuntrfCcyexReqPO.setAcFuntrfCcyexReqId(acFuntrfCcyexReqId);
            mcAcFuntrfCcyexReqPO.setAcFuntrfCcyexRid(mainId);
//        mcAcFuntrfCcyexReqPO.setAcFundTxnRid();// get from MC_AC_FUND_TXN_REC.AC_FUND_TXN_RID
            mcAcFuntrfCcyexReqPO.setRecVerNum(0L);
            mcAcFuntrfCcyexReqPO.setInitTime(LocalDateTime.now());
            mcAcFuntrfCcyexReqPO.setLastUpdTime(LocalDateTime.now());
            mcAcFuntrfCcyexReqPO.setLastUpdBy("MIG");
            mcAcFuntrfCcyexReqPO.setTagSeq(0L);

            mcAcFundGenStmtRemrkPO.setAcFundGenStmtRemrkRid(mainId);
//        mcAcFundGenStmtRemrkPO.setAcFundRid(); // get from MC_AC_FUND_REC.AC_FUND_RID
            mcAcFundGenStmtRemrkPO.setAcFundRid(1770190945944L + i);
            mcAcFundGenStmtRemrkPO.setIsPrimy("Y");
            mcAcFundGenStmtRemrkPO.setLangTyp("EN");
            mcAcFundGenStmtRemrkPO.setRemrkFrStmt(items.getRemarksFrom());
            mcAcFundGenStmtRemrkPO.setRecVerNum(0L);
            mcAcFundGenStmtRemrkPO.setInitTime(LocalDateTime.now());
            mcAcFundGenStmtRemrkPO.setLastUpdTime(LocalDateTime.now());
            mcAcFundGenStmtRemrkPO.setLastUpdBy("MIG");
            mcAcFundGenStmtRemrkPO.setTagSeq(0L);

            cashTransferMapMSSEPO.setSeqNo(items.getSeqNo());
            cashTransferMapMSSEPO.setAcOnlineFxReqId(acOnlineFxReqId);
            cashTransferMapMSSEPO.setAcFuntrfCcyexRid(mainId);
            cashTransferMapMSSEPO.setAcFuntrfCcyexReqId(acFuntrfCcyexReqId);
            cashTransferMapMSSEPO.setAcFundGenStmtRemrkRid(mainId);

            cashTransferMapMSSEPOList.add(cashTransferMapMSSEPO);
            mcAcOnlineFxReqPOList.add(mcAcOnlineFxReqPO);
            mcAcFuntrfCcyexRecPOList.add(mcAcFuntrfCcyexRecPO);
            mcAcFuntrfCcyexReqPOList.add(mcAcFuntrfCcyexReqPO);
            mcAcFundGenStmtRemrkPOList.add(mcAcFundGenStmtRemrkPO);
        }
        cashTransferMapMSSEMapper.insert(cashTransferMapMSSEPOList);
        saveToMcAcOnlineFxReq(mcAcOnlineFxReqPOList);
        saveToMcAcFuntrfCcyexRec(mcAcFuntrfCcyexRecPOList);
        saveToMcAcFuntrfCcyexReq(mcAcFuntrfCcyexReqPOList);
        saveToMcAcFundGenStmtRemrk(mcAcFundGenStmtRemrkPOList);
        return "ok";
    }

    private BigDecimal findBaseCcyEquAmtValue(CashTransferAllDTO dto) {
        ForexRatePO ratePO = forexRateMapper.selectRateByDate(dto.getCCYFrom(), String.valueOf(dto.getTransferDate()).replaceAll("T"," "));
        if(ratePO!=null) {//BigDecimal.ONE.divide(ratePO.getXRate(), 4, RoundingMode.HALF_UP)
            return ratePO.getXRate() != null ? dto.getAmountFrom().multiply(ratePO.getXRate()).setScale(2, RoundingMode.HALF_UP) : BigDecimal.ZERO;
        }
        return BigDecimal.ZERO;
    }

    private BigDecimal findBaseCcyEquToAmtValue(CashTransferAllDTO dto) {
        ForexRatePO ratePO = forexRateMapper.selectRateByDate(dto.getCCYFrom(), String.valueOf(dto.getTransferDate()).replaceAll("T"," "));
        if(ratePO!=null) {
            return ratePO.getXRate() != null ? ratePO.getXRate() : BigDecimal.ZERO;
        }
        return BigDecimal.ZERO;
    }

    @DS("oracle")
    @Transactional(rollbackFor = Exception.class)
    public void saveToMcAcOnlineFxReq(List<McAcOnlineFxReqPO> list){
        mcAcOnlineFxReqMapper.batchInsert(list);
    }

    @DS("oracle")
    @Transactional(rollbackFor = Exception.class)
    public void saveToMcAcFuntrfCcyexRec(List<McAcFuntrfCcyexRecPO> list){
        mcAcFuntrfCcyexRecMapper.batchInsert(list);
    }

    @DS("oracle")
    @Transactional(rollbackFor = Exception.class)
    public void saveToMcAcFuntrfCcyexReq(List<McAcFuntrfCcyexReqPO> list){
        mcAcFuntrfCcyexReqMapper.batchInsert(list);
    }

    @DS("oracle")
    @Transactional(rollbackFor = Exception.class)
    public void saveToMcAcFundGenStmtRemrk(List<McAcFundGenStmtRemrkPO> list){
        mcAcFundGenStmtRemrkMapper.batchInsert(list);
    }

    private static String fxStatCodeConver(String status) {
        switch (status) {
            case "NEW":
                return "SUBMITTED";
            case "APP":
                return "APPROVED";
            case "CAN":
            case "REJ":
            case "DEL":
                return "CANCELLED";
        }
        return status;
    }
}
