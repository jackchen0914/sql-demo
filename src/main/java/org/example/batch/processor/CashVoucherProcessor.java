package org.example.batch.processor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.example.mapper.McInstrMapper;
import org.example.mapper.McMrktMapper;
import org.example.pojo.*;
import org.example.pojo.dtos.CaRSCDTO;
import org.example.pojo.dtos.CaRSCResultDTO;
import org.example.pojo.dtos.CashVoucherResultDTO;
import org.example.pojo.dtos.CashVoucherWithRequestDTO;
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
public class CashVoucherProcessor implements ItemProcessor<CashVoucherWithRequestDTO, CashVoucherResultDTO> {

    private final McMrktMapper mcMrktMapper;
    private final IdGeneratorService idGeneratorService;
    private final McInstrMapper mcInstrMapper;

    @Override
    public CashVoucherResultDTO process(CashVoucherWithRequestDTO items) throws Exception {
        if(items == null){
            return null;
        }

        CashVoucherResultDTO result = new CashVoucherResultDTO();

        Long mainId = idGeneratorService.generateMainId();

        McAcFundRecPO mcAcFundRecPO = new McAcFundRecPO();
        McAcFundTxnRecPO mcAcFundTxnRecPO = new McAcFundTxnRecPO();
        McFundTpReltnPO mcFundTpReltnPO = new McFundTpReltnPO();

        mcAcFundTxnRecPO.setAcFundTxnRid(mainId);
        mcAcFundTxnRecPO.setFundStatCde(PropertyConverUtils.fundStatCdeTrans(items.getStatus()));
        mcAcFundTxnRecPO.setIsRev("N");
        mcAcFundTxnRecPO.setRevAcFundTxnRid(null);
        mcAcFundTxnRecPO.setRecVerNum(0L);
        mcAcFundTxnRecPO.setInitTime(LocalDateTime.now());
        mcAcFundTxnRecPO.setLastUpdTime(LocalDateTime.now());
        mcAcFundTxnRecPO.setLastUpdBy("MIG");
        mcAcFundTxnRecPO.setTagSeq(0L);

        mcAcFundRecPO.setAcFundRid(idGeneratorService.generateDetailId());
        mcAcFundRecPO.setCmpnyIbusdate(items.getVoucherDate());
        mcAcFundRecPO.setCmpnyBusdate(items.getVoucherDate());
//            mcAcFundRecPO.setAcId(items.getClnt());
        mcAcFundRecPO.setAcId("02-0000389-30");
        mcAcFundRecPO.setCcyCde(PropertyConverUtils.standardizeCurrencyCode(items.getCcy()));
        mcAcFundRecPO.setSegrFundId(1L);
        mcAcFundRecPO.setCmpnyCde("TFS");
//            mcAcFundRecPO.setTxnTypId(items.getTxnType()); //待确定
        mcAcFundRecPO.setTxnTypId(items.getTxnTypIdValue());
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
        mcAcFundRecPO.setTxnTypActnCde(items.getTxnTypActnCdeValue());
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
        mcAcFundRecPO.setBaseCcyEquAmt(items.getBaseCcyEquAmtValue());
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

        log.info("index value:{} " , items.getIndex());
        mcFundTpReltnPO.setFundTpReltnCde(Objects.equals(items.getRelationship(), " ") || items.getRelationship() == null  ? ""+items.getIndex() : items.getRelationship());
        mcFundTpReltnPO.setFundTpReltnDscr(Objects.equals(items.getRelationship(), " ") || items.getRelationship() == null ? ""+items.getIndex() : items.getRelationship());
        mcFundTpReltnPO.setFundTpReltnPri((long) (17 + items.getIndex()));
        mcFundTpReltnPO.setIsInact("N");
        mcFundTpReltnPO.setRecVerNum(0L);
        mcFundTpReltnPO.setInitTime(LocalDateTime.now());
        mcFundTpReltnPO.setLastUpdTime(LocalDateTime.now());
        mcFundTpReltnPO.setLastUpdBy("MIG");
        mcFundTpReltnPO.setTagSeq(0L);

        result.setMcAcFundRecRecord(mcAcFundRecPO);
        result.setMcFundTpReltnPRecord(mcFundTpReltnPO);
        result.setMcAcFundTxnRecRecord(mcAcFundTxnRecPO);

        return result;
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

}

