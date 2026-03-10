package org.example.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.example.mapper.*;
import org.example.pojo.*;
import org.example.pojo.dtos.CashVoucherWithRequestDTO;
import org.example.service.CashVoucherToMsseService;
import org.example.service.IdGeneratorService;
import org.example.utils.PropertyConverUtils;
import org.springframework.stereotype.Service;
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
public class CashVoucherToMsseServiceImpl implements CashVoucherToMsseService {

    private final McAcFundRecMapper mcAcFundRecMapper;
    private final McAcFundTxnRecMapper mcAcFundTxnRecMapper;
    private final McFundTpReltnMapper mcFundTpReltnMapper;

    private final CashVoucherMapper cashVoucherMapper;
    private final ForexRateMapper forexRateMapper;
    private final TransactionTypesMapper transactionTypesMapper;
    private final CashVoucherMapMSSEMapper cashVoucherMapMSSEMapper;

    private final IdGeneratorService idGeneratorService;

    @Override
    public String writeProcessedData() {
        List<CashVoucherWithRequestDTO> cashVoucherWithRequestDTOS = cashVoucherMapper.selectCashVoucherWithRequest(1,10);
        List<McAcFundRecPO> mcAcFundRecPOList = new ArrayList<>();
        List<McAcFundTxnRecPO> mcAcFundTxnRecPOList = new ArrayList<>();
        List<McFundTpReltnPO> mcFundTpReltnPOList = new ArrayList<>();
        List<CashVoucherMapMSSEPO> cashVoucherMapMSSEPOList = new ArrayList<>();
        for (int i = 0; i < cashVoucherWithRequestDTOS.size(); i++) {
            CashVoucherWithRequestDTO items = cashVoucherWithRequestDTOS.get(i);
            Long mainId = idGeneratorService.generateMainId();
            Long acFundRid = idGeneratorService.generateDetailId();

            McAcFundRecPO mcAcFundRecPO = new McAcFundRecPO();
            McAcFundTxnRecPO mcAcFundTxnRecPO = new McAcFundTxnRecPO();
            McFundTpReltnPO mcFundTpReltnPO = new McFundTpReltnPO();
            CashVoucherMapMSSEPO cashVoucherMapMSSEPO = new CashVoucherMapMSSEPO();

            mcAcFundTxnRecPO.setAcFundTxnRid(mainId);
            mcAcFundTxnRecPO.setFundStatCde(PropertyConverUtils.fundStatCdeTrans(items.getStatus()));
            mcAcFundTxnRecPO.setIsRev("N");
            mcAcFundTxnRecPO.setRevAcFundTxnRid(null);
            mcAcFundTxnRecPO.setRecVerNum(0L);
            mcAcFundTxnRecPO.setInitTime(LocalDateTime.now());
            mcAcFundTxnRecPO.setLastUpdTime(LocalDateTime.now());
            mcAcFundTxnRecPO.setLastUpdBy("MIG");
            mcAcFundTxnRecPO.setTagSeq(0L);

            mcAcFundRecPO.setAcFundRid(acFundRid);
            mcAcFundRecPO.setCmpnyIbusdate(items.getVoucherDate());
            mcAcFundRecPO.setCmpnyBusdate(items.getVoucherDate());
//            mcAcFundRecPO.setAcId(items.getClnt());
            mcAcFundRecPO.setAcId("02-0000389-30");
            mcAcFundRecPO.setCcyCde(PropertyConverUtils.standardizeCurrencyCode(items.getCcy()));
            mcAcFundRecPO.setSegrFundId(1L);
            mcAcFundRecPO.setCmpnyCde("TFS");
//            mcAcFundRecPO.setTxnTypId(items.getTxnType()); //待确定
            mcAcFundRecPO.setTxnTypId(txnTypIdValueConvert(items.getTxnType()));
            mcAcFundRecPO.setValDate(items.getValueDate());
            mcAcFundRecPO.setAmt(items.getAmount().abs());
            mcAcFundRecPO.setIsNonAcHldr("N");
            mcAcFundRecPO.setRemrk(items.getRemark());
            mcAcFundRecPO.setBankCde(items.getSource());
            mcAcFundRecPO.setPayeNam(items.getAccountName());
            mcAcFundRecPO.setOthAcPayeNam(items.getBenfName()); //CashVoucherRequest.BenfName 待确定
            mcAcFundRecPO.setClntBankCde(items.getSource());
            mcAcFundRecPO.setClntBankAcNum(items.getAccountNumber());
            mcAcFundRecPO.setIsPrtRcpt("N");
//            mcAcFundRecPO.setCmpnyBankAcId(items.getTxnType());
            mcAcFundRecPO.setFundStatCde(PropertyConverUtils.fundStatCdeTrans(items.getStatus()));
            mcAcFundRecPO.setFundChnlCde(Objects.equals(items.getManualInput(), "Yes") ? "MANUALINP" : "SYSTEM");
            mcAcFundRecPO.setTxnTypActnCde(txnTypActnCdeValueCovert(items.getTxnType()));
            mcAcFundRecPO.setIsMemo("N");
            mcAcFundRecPO.setIsChq(isChqAndIsTrnfrTrans(items,"chq"));
            mcAcFundRecPO.setIsChrg(items.getCharge().contains("YES") ? "Y" : "N");
            mcAcFundRecPO.setIsTrnfr(isChqAndIsTrnfrTrans(items,"trnfr"));
            mcAcFundRecPO.setIsRev(isRevTrans(items));
            mcAcFundRecPO.setIsDhon("N");
            mcAcFundRecPO.setIsRtun("N");
            mcAcFundRecPO.setIsUnderRevrse("N");
            mcAcFundRecPO.setIsUnderDhon("N");
            mcAcFundRecPO.setIsUnderRtun("N");
            mcAcFundRecPO.setIsTodayRev(isTodayRevTrans(items));
            mcAcFundRecPO.setIsTrigCreat("N");
            mcAcFundRecPO.setPrimyRemrkFrStmt(items.getRemark());
            mcAcFundRecPO.setBaseCcyEquAmt(baseCcyEquAmtValueProcess(items));
            mcAcFundRecPO.setIsAutoAprv("N"); //有条件约束  不能相同
            mcAcFundRecPO.setIsIgnrDatSync("N");
            mcAcFundRecPO.setRvisUnit("0031");
            mcAcFundRecPO.setRvisBy("MIG");
            mcAcFundRecPO.setLastRvisTime(LocalDateTime.now());
            mcAcFundRecPO.setChkBy("MIG");
            mcAcFundRecPO.setAprvRejCmpnyIbusdate(items.getApprovalTime());
            mcAcFundRecPO.setAprvRejCmpnyBusdate(items.getApprovalTime());
            mcAcFundRecPO.setAprvRejTime(items.getApprovalTime());
            mcAcFundRecPO.setIsElectronic("N");
            mcAcFundRecPO.setIsWork("N");
            mcAcFundRecPO.setAcFundTxnRid(mainId);
            mcAcFundRecPO.setIsOperateInFund("N");
            mcAcFundRecPO.setIsInstrRelt("N");
            mcAcFundRecPO.setIsPost("N");
            mcAcFundRecPO.setRecVerNum(0L);
            mcAcFundRecPO.setInitTime(LocalDateTime.now());
            mcAcFundRecPO.setLastUpdTime(LocalDateTime.now());
            mcAcFundRecPO.setLastUpdBy("MIG");
            mcAcFundRecPO.setTagSeq(0L);
            mcAcFundRecPO.setExtrnlRefNum(items.getMarket()+items.getVoucherNo());
            mcAcFundRecPO.setExtrnlSysCde("OctOBack");
            mcAcFundRecPO.setInitUser("MIG");
//            mcAcFundRecPO.setInitUserUnit("0001");
            mcAcFundRecPO.setInitUserUnit("0031");
            mcAcFundRecPO.setIsReact("N");
            mcAcFundRecPO.setIsUnderReact("N");
            mcAcFundRecPO.setIsNonRegBankAc("N");
            mcAcFundRecPO.setFundTpReltnCde(items.getRelationship());

            mcFundTpReltnPO.setFundTpReltnCde(Objects.equals(items.getRelationship(), "") || items.getRelationship() == null  ? ""+(5+i) : items.getRelationship());
            mcFundTpReltnPO.setFundTpReltnDscr(Objects.equals(items.getRelationship(), "") || items.getRelationship() == null ? ""+(5+i) : items.getRelationship());
            mcFundTpReltnPO.setFundTpReltnPri((long) (18 + i));
            mcFundTpReltnPO.setIsInact("N");
            mcFundTpReltnPO.setRecVerNum(0L);
            mcFundTpReltnPO.setInitTime(LocalDateTime.now());
            mcFundTpReltnPO.setLastUpdTime(LocalDateTime.now());
            mcFundTpReltnPO.setLastUpdBy("MIG");
            mcFundTpReltnPO.setTagSeq(0L);

            cashVoucherMapMSSEPO.setVoucherNo(items.getVoucherNo());
            cashVoucherMapMSSEPO.setMarket(items.getMarket());
            cashVoucherMapMSSEPO.setAcFundRid(acFundRid);

            cashVoucherMapMSSEPOList.add(cashVoucherMapMSSEPO);
            mcAcFundRecPOList.add(mcAcFundRecPO);
            mcAcFundTxnRecPOList.add(mcAcFundTxnRecPO);
            mcFundTpReltnPOList.add(mcFundTpReltnPO);
        }
        cashVoucherMapMSSEMapper.insert(cashVoucherMapMSSEPOList);
        saveToOracleMcAcFundTxn(mcAcFundTxnRecPOList);
        saveToOracleMcAcFundRec(mcAcFundRecPOList);
        saveToOracleMcFundTpReltn(mcFundTpReltnPOList);
        return "ok";
    }

    private String isChqAndIsTrnfrTrans(CashVoucherWithRequestDTO items,String type) {
        if(type.equals("chq")){
            if(!StringUtils.isEmpty(items.getMode())){
                return items.getMode().contains("CHQ") ? "Y" : "N";
            } else if (!StringUtils.isEmpty(items.getWithdrawMode())) {
                return items.getWithdrawMode().contains("CHQ") ? "Y" : "N";
//                }else return " ";
            }else return "N";
        }else {
            if(!StringUtils.isEmpty(items.getMode())){
                return items.getMode().equals("3. INT TRF") ? "Y" : "N";
            } else if (!StringUtils.isEmpty(items.getWithdrawMode())) {
                return items.getWithdrawMode().equals("3. INT TRF") ? "Y" : "N";
//                }else return " ";
            }else return "N";
        }
    }

    private String isRevTrans(CashVoucherWithRequestDTO items) {
        //2019-06-05 00:00:00.000
        if(items.getCancelDate()!=null && items.getConfirmationDate() != null){
            if (!items.getCancelDate().equals(items.getConfirmationDate()) && items.getStatus().contains("CAN")) {
                return "Y";
            }
        }
        return "N";
    }
    private String isTodayRevTrans(CashVoucherWithRequestDTO items) {
        //2019-06-05 00:00:00.000
        if(items.getCancelDate()!=null && items.getConfirmationDate() != null) {
            if (items.getCancelDate().equals(items.getConfirmationDate()) && items.getStatus().contains("CAN")) {
                return "Y";
            }
        }
        return "N";
    }

    private Long txnTypIdValueConvert(String code) {
        TransactionTypesPO transactionTypesPO = transactionTypesMapper.selectTxnTypeCode(code);
        return transactionTypesPO.getSignIndicator().equals("C") ? 1L : 6L;
    }

    private BigDecimal baseCcyEquAmtValueProcess(CashVoucherWithRequestDTO po) {
        ForexRatePO forexRatePO = forexRateMapper.selectRateByDate(po.getCcy(), String.valueOf(po.getConfirmationDate()).split("T")[0]);
        return po.getAmount().multiply(forexRatePO.getXRate()).setScale(8, RoundingMode.HALF_UP);
    }

    private String txnTypActnCdeValueCovert(String code) {
        TransactionTypesPO transactionTypesPO = transactionTypesMapper.selectTxnTypeCode(code);
        if(transactionTypesPO.getSignIndicator() != null){
           return transactionTypesPO.getSignIndicator().equals("C") ? "IN" : "OUT";
        }
        return "";
    }

    @DS("oracle")
    @Transactional(rollbackFor = Exception.class)
    public void saveToOracleMcAcFundTxn(List<McAcFundTxnRecPO> list){
        mcAcFundTxnRecMapper.batchInsert(list);
    }

    @DS("oracle")
    @Transactional(rollbackFor = Exception.class)
    public void saveToOracleMcAcFundRec(List<McAcFundRecPO> list){
        mcAcFundRecMapper.batchInsert(list);
    }

    @DS("oracle")
    @Transactional(rollbackFor = Exception.class)
    public void saveToOracleMcFundTpReltn(List<McFundTpReltnPO> list){
        mcFundTpReltnMapper.batchInsert(list);
    }
}
