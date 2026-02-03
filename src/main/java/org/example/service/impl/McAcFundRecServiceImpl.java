package org.example.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.example.mapper.*;
import org.example.pojo.*;
import org.example.pojo.dtos.CashVoucherWithRequestDTO;
import org.example.service.IMcAcFundRecService;
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
 * </p>
 *
 * @author JackChen
 * @since 2025-12-12
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class McAcFundRecServiceImpl extends ServiceImpl<McAcFundRecMapper, McAcFundRecPO> implements IMcAcFundRecService {
//public class McAcFundRecServiceImpl implements DatabaseManipulationService {

    private final CashVoucherMapper cashVoucherMapper;

    private final CashVoucherRequestMapper cashVoucherRequestMapper;

    private final TransactionTypesMapper transactionTypesMapper;

    private final McAcFundRecMapper mcAcFundRecMapper;

    private final ForexRateMapper forexRateMapper;

    private final McAcFundTxnRecMapper mcAcFundTxnRecMapper;

    @Override
    public void writeProcessedData() throws InterruptedException {
//        List<CashVoucherPO> cashVoucherPOS = cashVoucherMapper.selectTop5();
//        log.info("---->{}", cashVoucherPOS);
//        List<CashVoucherWithRequestDTO> cashVoucherWithRequestDTOS = cashVoucherMapper.selectCashVoucherWithRequest();
//        List<McAcFundRecPO> fundRecDataList = new ArrayList<>();
//        List<McAcFundTxnRecPO> fundTxnDataList = new ArrayList<>();
//        for (int i = 0; i < cashVoucherWithRequestDTOS.size(); i++) {
//            CashVoucherWithRequestDTO po = cashVoucherWithRequestDTOS.get(i);
//            McAcFundRecPO mcAcFundRecPO = new McAcFundRecPO();
//            McAcFundTxnRecPO mcAcFundTxnRecPO = new McAcFundTxnRecPO();
//            mcAcFundTxnRecPO.setAcFundTxnRid((long) (16000000+i));
//            mcAcFundTxnRecPO.setFundStatCde(fundStatCdeTrans(po.getStatus()));
//            mcAcFundTxnRecPO.setIsRev("N");
//            mcAcFundTxnRecPO.setRevAcFundTxnRid(null);
//            mcAcFundTxnRecPO.setRecVerNum(0L);
//            mcAcFundTxnRecPO.setInitTime(LocalDateTime.now());
//            mcAcFundTxnRecPO.setLastUpdTime(LocalDateTime.now());
//            mcAcFundTxnRecPO.setLastUpdBy("MIG");
//            mcAcFundTxnRecPO.setTagSeq(0L);
//
//            mcAcFundRecPO.setAcFundRid((long) (18000000 + i));
//            mcAcFundRecPO.setCmpnyIbusdate(po.getVoucherDate());
//            mcAcFundRecPO.setCmpnyBusdate(po.getVoucherDate());
////            mcAcFundRecPO.setMrktIbusdate(null);
////            mcAcFundRecPO.setMrktBusdate(null);
////            mcAcFundRecPO.setAcId(po.getClnt());
//            mcAcFundRecPO.setAcId("02-0000389-30");
//            mcAcFundRecPO.setCcyCde(currencyTrans(po.getCcy(), po));
//            mcAcFundRecPO.setSegrFundId(1L);
//            mcAcFundRecPO.setCmpnyCde("TFS");
////            mcAcFundRecPO.setTxnTypId(po.getTxnType()); //待确定
//            mcAcFundRecPO.setTxnTypId(txnTypIdConvert(po.getTxnType()));
//            mcAcFundRecPO.setValDate(po.getValueDate());
//            mcAcFundRecPO.setAmt(po.getAmount().abs());
//            mcAcFundRecPO.setIsNonAcHldr("N");
//            mcAcFundRecPO.setRemrk(po.getRemark());
//            mcAcFundRecPO.setBankCde(po.getSource());
//            mcAcFundRecPO.setPayeNam(po.getAccountName());
////            mcAcFundRecPO.setOthAcPayeNam(); //CashVoucherRequest.BenfName 待确定
//            mcAcFundRecPO.setClntBankCde(po.getSource());
//            mcAcFundRecPO.setClntBankAcNum(po.getAccountNumber());
//            mcAcFundRecPO.setIsPrtRcpt("N");
////            mcAcFundRecPO.setCmpnyBankAcId(po.getTxnType());
//            mcAcFundRecPO.setFundStatCde(fundStatCdeTrans(po.getStatus()));
//            mcAcFundRecPO.setFundChnlCde(Objects.equals(po.getManualInput(), "Yes") ? "MANUALINP" : "SYSTEM");
//            mcAcFundRecPO.setTxnTypActnCde(txnTypActnCdeTrans(po.getTxnType()));
//            mcAcFundRecPO.setIsMemo("N");
//            mcAcFundRecPO.setIsChq(isChqAndIsTrnfrTrans(po,"chq"));
//            mcAcFundRecPO.setIsChrg(po.getCharge().contains("YES") ? "Y" : "N");
//            mcAcFundRecPO.setIsTrnfr(isChqAndIsTrnfrTrans(po,"trnfr"));
//            mcAcFundRecPO.setIsRev(isRevTrans(po));
//            mcAcFundRecPO.setIsDhon("N");
//            mcAcFundRecPO.setIsRtun("N");
//            mcAcFundRecPO.setIsUnderRevrse("N");
//            mcAcFundRecPO.setIsUnderDhon("N");
//            mcAcFundRecPO.setIsUnderRtun("N");
//            mcAcFundRecPO.setIsTodayRev(isTodayRevTrans(po));
//            mcAcFundRecPO.setIsTrigCreat("N");
//            mcAcFundRecPO.setPrimyRemrkFrStmt(po.getRemark());
//            mcAcFundRecPO.setBaseCcyEquAmt(calculateAmountProcess(po));
//            mcAcFundRecPO.setIsAutoAprv("N"); //有条件约束  不能相同
//            mcAcFundRecPO.setIsIgnrDatSync("N");
//            mcAcFundRecPO.setRvisUnit("0031");
//            mcAcFundRecPO.setRvisBy("MIG");
//            mcAcFundRecPO.setLastRvisTime(LocalDateTime.now());
//            mcAcFundRecPO.setChkBy("MIG");
//            mcAcFundRecPO.setAprvRejCmpnyIbusdate(po.getApprovalTime());
//            mcAcFundRecPO.setAprvRejCmpnyBusdate(po.getApprovalTime());
//            mcAcFundRecPO.setAprvRejTime(po.getApprovalTime());
//            mcAcFundRecPO.setIsElectronic("N");
//            mcAcFundRecPO.setIsWork("N");
//            mcAcFundRecPO.setAcFundTxnRid((long) (16000000+i));
//            mcAcFundRecPO.setIsOperateInFund("N");
//            mcAcFundRecPO.setIsInstrRelt("N");
//            mcAcFundRecPO.setIsPost("N");
//            mcAcFundRecPO.setRecVerNum(0L);
//            mcAcFundRecPO.setInitTime(LocalDateTime.now());
//            mcAcFundRecPO.setLastUpdTime(LocalDateTime.now());
//            mcAcFundRecPO.setLastUpdBy("MIG");
//            mcAcFundRecPO.setTagSeq(0L);
//            mcAcFundRecPO.setExtrnlRefNum(po.getMarket()+po.getVoucherNo());
//            mcAcFundRecPO.setExtrnlSysCde("OctOBack");
//            mcAcFundRecPO.setInitUser("MIG");
////            mcAcFundRecPO.setInitUserUnit("0001");
//            mcAcFundRecPO.setInitUserUnit("0031");
//            mcAcFundRecPO.setIsReact("N");
//            mcAcFundRecPO.setIsUnderReact("N");
//            mcAcFundRecPO.setIsNonRegBankAc("N");
//            mcAcFundRecPO.setFundTpReltnCde(po.getRelationship());
//
//            fundRecDataList.add(mcAcFundRecPO);
//            fundTxnDataList.add(mcAcFundTxnRecPO);
//        }
//        saveToOracleMcAcFundTxn(fundTxnDataList);
////        Thread.sleep(5000);
//        saveToOracleMcAcFundRec(fundRecDataList);
    }

    private Long txnTypIdConvert(String code) {
        TransactionTypesPO transactionTypesPO = transactionTypesMapper.selectTxnTypeCode(code);
        return transactionTypesPO.getSignIndicator().equals("C") ? 1L : 6L;
    }

    private String isChqAndIsTrnfrTrans(CashVoucherWithRequestDTO po,String type) {
            if(type.equals("chq")){
                if(!StringUtils.isEmpty(po.getMode())){
                    return po.getMode().contains("CHQ") ? "Y" : "N";
                } else if (!StringUtils.isEmpty(po.getWithdrawMode())) {
                    return po.getWithdrawMode().contains("CHQ") ? "Y" : "N";
//                }else return " ";
                }else return "N";
            }else {
                if(!StringUtils.isEmpty(po.getMode())){
                    return po.getMode().equals("3. INT TRF") ? "Y" : "N";
                } else if (!StringUtils.isEmpty(po.getWithdrawMode())) {
                    return po.getWithdrawMode().equals("3. INT TRF") ? "Y" : "N";
//                }else return " ";
                }else return "N";
            }
    }

    @DS("oracle")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveToOracleMcAcFundTxn(List<McAcFundTxnRecPO> list){
        mcAcFundTxnRecMapper.insert(list);
    }

    @DS("oracle")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveToOracleMcAcFundRec(List<McAcFundRecPO> list){
        mcAcFundRecMapper.insert(list);
    }

    private BigDecimal calculateAmountProcess(CashVoucherWithRequestDTO po) {
        ForexRatePO forexRatePO = forexRateMapper.selectRateByDate(po.getCcy(), String.valueOf(po.getConfirmationDate()).split("T")[0]);
        return po.getAmount().multiply(forexRatePO.getXRate()).setScale(8, RoundingMode.HALF_UP);
    }

    private String isRevTrans(CashVoucherWithRequestDTO po) {
        //2019-06-05 00:00:00.000
        if(po.getCancelDate()!=null && po.getConfirmationDate() != null){
            if (!po.getCancelDate().equals(po.getConfirmationDate()) && po.getStatus().contains("CAN")) {
                return "Y";
            }
        }
        return "N";
    }
    private String isTodayRevTrans(CashVoucherWithRequestDTO po) {
        //2019-06-05 00:00:00.000
        if(po.getCancelDate()!=null && po.getConfirmationDate() != null) {
            if (po.getCancelDate().equals(po.getConfirmationDate()) && po.getStatus().contains("CAN")) {
                return "Y";
            }
        }
        return "N";
    }

    private String txnTypActnCdeTrans(String code) {
        TransactionTypesPO transactionTypesPO = transactionTypesMapper.selectTxnTypeCode(code);
        if(transactionTypesPO.getSignIndicator() != null){
           return transactionTypesPO.getSignIndicator().equals("C") ? "IN" : "OUT";
        }
        return "";
    }

    private String currencyTrans(String ccy, CashVoucherWithRequestDTO po) {
        if (ccy.contains("RMB")) {
            return "CNY";
        } else if (ccy.contains("YEN")) {
            return "JPY";
        } else return po.getCcy();
    }

    private String fundStatCdeTrans(String code) {
        switch (code) {
            case "NEW":
                return "DRAFT";
            case "DEL":
                return "DELETED";
            case "REJ":
                return "REJECTED";
            case "APP":
                return "APPROVED";
            case "CAN":
                return "REVERSED";
            default:
                return " ";
        }
    }

}
