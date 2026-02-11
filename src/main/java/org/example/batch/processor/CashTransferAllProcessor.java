package org.example.batch.processor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.McInstrMapper;
import org.example.mapper.McMrktMapper;
import org.example.pojo.*;
import org.example.pojo.dtos.CashTransferAllDTO;
import org.example.pojo.dtos.CashTransferAllResultDTO;
import org.example.pojo.dtos.ClntPriceCapResultDTO;
import org.example.service.IdGeneratorService;
import org.example.utils.PropertyConverUtils;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Component
@Slf4j
@RequiredArgsConstructor
public class CashTransferAllProcessor implements ItemProcessor<CashTransferAllDTO, CashTransferAllResultDTO> {

    private final McMrktMapper mcMrktMapper;
    private final McInstrMapper mcInstrMapper;
    private final IdGeneratorService idGeneratorService;

    @Override
    public CashTransferAllResultDTO process(CashTransferAllDTO items) throws Exception {
        if(items == null){
            return null;
        }

        CashTransferAllResultDTO result = new CashTransferAllResultDTO();

        Long mainId = idGeneratorService.generateMainId();

        McAcOnlineFxReqPO mcAcOnlineFxReqPO = new McAcOnlineFxReqPO();
        McAcFuntrfCcyexRecPO mcAcFuntrfCcyexRecPO = new McAcFuntrfCcyexRecPO();
        McAcFuntrfCcyexReqPO mcAcFuntrfCcyexReqPO = new McAcFuntrfCcyexReqPO();
        McAcFundGenStmtRemrkPO mcAcFundGenStmtRemrkPO = new McAcFundGenStmtRemrkPO();

        mcAcOnlineFxReqPO.setAcOnlineFxReqId(idGeneratorService.generateDetailId());
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
        mcAcFuntrfCcyexRecPO.setBaseCcyEquAmt(items.getBaseCcyEquAmtValue());
        mcAcFuntrfCcyexRecPO.setBaseCcyEquToAmt(items.getBaseCcyEquToAmtValue());
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

        mcAcFuntrfCcyexReqPO.setAcFuntrfCcyexReqId(idGeneratorService.generateDetailId());
        mcAcFuntrfCcyexReqPO.setAcFuntrfCcyexRid(mainId);
//        mcAcFuntrfCcyexReqPO.setAcFundTxnRid();// get from MC_AC_FUND_TXN_REC.AC_FUND_TXN_RID
        mcAcFuntrfCcyexReqPO.setRecVerNum(0L);
        mcAcFuntrfCcyexReqPO.setInitTime(LocalDateTime.now());
        mcAcFuntrfCcyexReqPO.setLastUpdTime(LocalDateTime.now());
        mcAcFuntrfCcyexReqPO.setLastUpdBy("MIG");
        mcAcFuntrfCcyexReqPO.setTagSeq(0L);

        mcAcFundGenStmtRemrkPO.setAcFundGenStmtRemrkRid(mainId);
//        mcAcFundGenStmtRemrkPO.setAcFundRid(); // get from MC_AC_FUND_REC.AC_FUND_RID
        mcAcFundGenStmtRemrkPO.setAcFundRid(1770190945943L);
        mcAcFundGenStmtRemrkPO.setIsPrimy("Y");
        mcAcFundGenStmtRemrkPO.setLangTyp("EN");
        mcAcFundGenStmtRemrkPO.setRemrkFrStmt(items.getRemarksFrom());
        mcAcFundGenStmtRemrkPO.setRecVerNum(0L);
        mcAcFundGenStmtRemrkPO.setInitTime(LocalDateTime.now());
        mcAcFundGenStmtRemrkPO.setLastUpdTime(LocalDateTime.now());
        mcAcFundGenStmtRemrkPO.setLastUpdBy("MIG");
        mcAcFundGenStmtRemrkPO.setTagSeq(0L);

        result.setMainRecord(mcAcOnlineFxReqPO);
        result.setMcAcFuntrfCcyexRecRecord(mcAcFuntrfCcyexRecPO);
        result.setMcAcFuntrfCcyexReqRecord(mcAcFuntrfCcyexReqPO);
        result.setMcAcFundGenStmtRemrkRecord(mcAcFundGenStmtRemrkPO);

        return result;
    }

    private String fxStatCodeConver(String status) {
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
