package org.example.batch.processor;

import com.baomidou.dynamic.datasource.annotation.DS;
import lombok.RequiredArgsConstructor;
import org.example.mapper.*;
import org.example.pojo.*;
import org.example.pojo.dtos.CaRSCDTO;
import org.example.pojo.dtos.InstrumentVoucherResultDTO;
import org.example.pojo.dtos.InstrumentVoucherResultDTO;
import org.example.service.IdGeneratorService;
import org.example.utils.PropertyConverUtils;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class InstrumentVoucherProcessor implements ItemProcessor<InstrumentVoucherPO, InstrumentVoucherResultDTO> {


    private final McInstrMapper mcInstrMapper;
    private final McMrktMapper mcMrktMapper;
    private final McInstclVerMapper mcInstclVerMapper;
    private final IdGeneratorService idGeneratorService;

    @Override
    public InstrumentVoucherResultDTO process(InstrumentVoucherPO items) throws Exception {
        if(items == null){
            return null;
        }

        InstrumentVoucherResultDTO result = new InstrumentVoucherResultDTO();

        Long mainId = idGeneratorService.generateMainId();

        McAcStkTxnDtlPO mcAcStkTxnDtlPO = new McAcStkTxnDtlPO();
        McAcStkTxnDtlStatPO mcAcStkTxnDtlStatPO = new McAcStkTxnDtlStatPO();
        McStkTxnPO mcStkTxnPO = new McStkTxnPO();
        McStkMemoTxnPO mcStkMemoTxnPO = new McStkMemoTxnPO();
        McStkMemoTxnReqPO mcStkMemoTxnReqPO = new McStkMemoTxnReqPO();
        McLoctnStkTxnDtlPO mcLoctnStkTxnDtlPO = new McLoctnStkTxnDtlPO();

        mcAcStkTxnDtlStatPO.setAcStkTxnDtlStkStatId(idGeneratorService.generateDetailId());
        mcAcStkTxnDtlStatPO.setAcStkTxnDtlId((mainId));
        mcAcStkTxnDtlStatPO.setStkStatCde(items.getStkStatCdeValue());
        mcAcStkTxnDtlStatPO.setStkQty(items.getQuantity());
        mcAcStkTxnDtlStatPO.setRecVerNum(0L);
        mcAcStkTxnDtlStatPO.setInitTime(LocalDateTime.now());
        mcAcStkTxnDtlStatPO.setLastUpdTime(LocalDateTime.now());
        mcAcStkTxnDtlStatPO.setLastUpdBy("MIG");
        mcAcStkTxnDtlStatPO.setTagSeq(0L);

        mcStkTxnPO.setStkTxnId(mainId);
        mcStkTxnPO.setStkAprvStatCde("APPROVED");
        mcStkTxnPO.setIsRev("N");
        mcStkTxnPO.setIsAwaiting("N");
        mcStkTxnPO.setRecVerNum(0L);
        mcStkTxnPO.setInitTime(LocalDateTime.now());
        mcStkTxnPO.setLastUpdTime(LocalDateTime.now());
        mcStkTxnPO.setLastUpdBy("MIG");
        mcStkTxnPO.setTagSeq(0L);

        mcAcStkTxnDtlPO.setAcStkTxnDtlId(mainId);
        mcAcStkTxnDtlPO.setStkTxnId(mainId);
        mcAcStkTxnDtlPO.setTxnTypId(items.getTxnTypIdValue());
        mcAcStkTxnDtlPO.setTxnTypActnCde(items.getTxnTypActnCdeValue());
        mcAcStkTxnDtlPO.setTxnRefNum(String.valueOf(items.getVoucherNo()));
        mcAcStkTxnDtlPO.setCmpnyCde("TFS");
        mcAcStkTxnDtlPO.setInstrId(findInstrIdByCodeProcess(items.getInstrument(),"instrId"));
        mcAcStkTxnDtlPO.setInstclId(findInstrIdByCodeProcess(items.getInstrument(),"tclId"));
        mcAcStkTxnDtlPO.setInstclVid(instrumentFindInstclVid(items));
        mcAcStkTxnDtlPO.setMrktId(marketCodeConversion(items));
        mcAcStkTxnDtlPO.setValDate(items.getValueDate());
        mcAcStkTxnDtlPO.setInptDate(items.getVoucherDate());
        mcAcStkTxnDtlPO.setMrktBusdate(items.getConfirmationDate());
        mcAcStkTxnDtlPO.setStkChnlCde(items.getManualInput().contains("Yes") ? "MANUALINP" : "SYSTEM");
        mcAcStkTxnDtlPO.setRemrk(items.getRemark());
        mcAcStkTxnDtlPO.setIsAutoAprv("N");
        mcAcStkTxnDtlPO.setIsMemo("N");
        mcAcStkTxnDtlPO.setIsTrnfr("N");
        mcAcStkTxnDtlPO.setIsRev(isRevTrans(items));
        mcAcStkTxnDtlPO.setIsShowInStmt("Y");
        mcAcStkTxnDtlPO.setIsTodayRev(isTodayRevTrans(items));
        mcAcStkTxnDtlPO.setIsIgnrDatSync("N");
//            mcAcStkTxnDtlPO.setAcId(items.getClnt());
        mcAcStkTxnDtlPO.setAcId("02-0000389-30");
        mcAcStkTxnDtlPO.setPrimyRemrkFrStmt(items.getRemark());
        mcAcStkTxnDtlPO.setStmtModuleCde("STOCK");
        mcAcStkTxnDtlPO.setExtrnlTxnRefNum(items.getMarket()+items.getVoucherNo());
        mcAcStkTxnDtlPO.setRecVerNum(0L);
        mcAcStkTxnDtlPO.setInitTime(LocalDateTime.now());
        mcAcStkTxnDtlPO.setLastUpdTime(LocalDateTime.now());
        mcAcStkTxnDtlPO.setLastUpdBy("MIG");
        mcAcStkTxnDtlPO.setTagSeq(0L);
        mcAcStkTxnDtlPO.setIsTpTxn("N");
        mcAcStkTxnDtlPO.setIsOtcrTxn("N");

        //2961703
        mcStkMemoTxnPO.setStkMemoTxnId(mainId);
        mcStkMemoTxnPO.setCmpnyCde("TFS");
        mcStkMemoTxnPO.setTxnTypId(items.getTxnTypIdValue());
        mcStkMemoTxnPO.setTxnTypActnCde(items.getTxnTypActnCdeValue());
        mcStkMemoTxnPO.setTxnRefNum(String.valueOf(items.getVoucherNo()));
        mcStkMemoTxnPO.setValDate(items.getValueDate());
        mcStkMemoTxnPO.setRemrk(items.getRemark());
        mcStkMemoTxnPO.setPrimyRemrkFrStmt(items.getRemark());
        mcStkMemoTxnPO.setStkReqStatCde(PropertyConverUtils.fundStatCdeTrans(items.getStatus()));
        mcStkMemoTxnPO.setStkChnlCde(Objects.equals(items.getManualInput(), "YES") ? "MANUALINP" : "SYSTEM");
        mcStkMemoTxnPO.setCreatBy("admin");
        mcStkMemoTxnPO.setCreatMrktBusdate(items.getConfirmationDate());
        mcStkMemoTxnPO.setCreatInptDate(items.getVoucherDate());
        mcStkMemoTxnPO.setLastSubmittedBy("admin");
        mcStkMemoTxnPO.setLastSubmittedMrktBusdate(items.getConfirmationDate());
        mcStkMemoTxnPO.setLastSubmittedInptDate(items.getVoucherDate());
        mcStkMemoTxnPO.setLastSubmittedTime(items.getUserIDTime());
        mcStkMemoTxnPO.setAprvRejectDelBy(items.getApprover());
        mcStkMemoTxnPO.setAprvRejectDelMrktBusdate(items.getConfirmationDate());
        mcStkMemoTxnPO.setAprvRejectDelInptDate(items.getVoucherDate());
        mcStkMemoTxnPO.setAprvRejectDelTime(items.getApprovalTime());
        mcStkMemoTxnPO.setAcId("02-0000389-30");
        mcStkMemoTxnPO.setInstrId(findInstrIdByCodeProcess(items.getInstrument(),"instrId"));
        mcStkMemoTxnPO.setInstclId(findInstrIdByCodeProcess(items.getInstrument(),"tclId"));
        mcStkMemoTxnPO.setInstclVid(instrumentFindInstclVid(items));
        mcStkMemoTxnPO.setMrktId(marketCodeConversion(items));
        mcStkMemoTxnPO.setStkStatCde(items.getStkStatCdeValue());
        mcStkMemoTxnPO.setStkQty(items.getQuantity());
        mcStkMemoTxnPO.setRecVerNum(0L);
        mcStkMemoTxnPO.setInitTime(LocalDateTime.now());
        mcStkMemoTxnPO.setLastUpdTime(LocalDateTime.now());
        mcStkMemoTxnPO.setLastUpdBy("MIG");
        mcStkMemoTxnPO.setTagSeq(0L);
        mcStkMemoTxnPO.setExtrnlRefNum(items.getMarket()+items.getVoucherNo());
        mcStkMemoTxnPO.setIsTpTxn("N");
        mcStkMemoTxnPO.setIsOtcrTxn("N");
        mcStkMemoTxnPO.setIsRevTxn("N");

        mcStkMemoTxnReqPO.setStkMemoTxnReqId(idGeneratorService.generateDetailId());
        mcStkMemoTxnReqPO.setStkMemoTxnId(mainId);
        mcStkMemoTxnReqPO.setStkTxnId(mainId);
        mcStkMemoTxnReqPO.setIsRev(isRevTrans(items));
        mcStkMemoTxnReqPO.setStkReqStatCde(PropertyConverUtils.fundStatCdeTrans(items.getStatus()));
        mcStkMemoTxnReqPO.setStkAprvStatCde("APPROVED");
        mcStkMemoTxnReqPO.setRecVerNum(0L);
        mcStkMemoTxnReqPO.setInitTime(LocalDateTime.now());
        mcStkMemoTxnReqPO.setLastUpdTime(LocalDateTime.now());
        mcStkMemoTxnReqPO.setLastUpdBy("MIG");
        mcStkMemoTxnReqPO.setTagSeq(0L);

        mcLoctnStkTxnDtlPO.setLoctnStkTxnDtlId(idGeneratorService.generateDetailId());
        mcLoctnStkTxnDtlPO.setCmpnyCde("TFS");
        mcLoctnStkTxnDtlPO.setStkTxnId(mainId);
        mcLoctnStkTxnDtlPO.setTxnTypId(items.getTxnTypIdValue());
        mcLoctnStkTxnDtlPO.setTxnTypActnCde(items.getTxnTypActnCdeValue());
        mcLoctnStkTxnDtlPO.setTxnRefNum(String.valueOf(items.getVoucherNo()));
        mcLoctnStkTxnDtlPO.setInstrId(findInstrIdByCodeProcess(items.getInstrument(),"instrId"));
        mcLoctnStkTxnDtlPO.setInstclId(findInstrIdByCodeProcess(items.getInstrument(),"tclId"));
        mcLoctnStkTxnDtlPO.setInstclVid(instrumentFindInstclVid(items));
        mcLoctnStkTxnDtlPO.setMrktId(marketCodeConversion(items));
        mcLoctnStkTxnDtlPO.setValDate(items.getValueDate());
        mcLoctnStkTxnDtlPO.setInptDate(items.getVoucherDate());
        mcLoctnStkTxnDtlPO.setMrktBusdate(items.getConfirmationDate());
        mcLoctnStkTxnDtlPO.setStkChnlCde(Objects.equals(items.getManualInput(), "YES") ? "MANUALINP" : "SYSTEM");
        mcLoctnStkTxnDtlPO.setStkQty(items.getQuantity());
        mcLoctnStkTxnDtlPO.setRemrk(items.getRemark());
        mcLoctnStkTxnDtlPO.setIsMemo("N");
        mcLoctnStkTxnDtlPO.setIsAutoAprv("N");
        mcLoctnStkTxnDtlPO.setIsTrnfr("N");
        mcLoctnStkTxnDtlPO.setIsRev(isRevTrans(items));
        mcLoctnStkTxnDtlPO.setIsShowInStmt("Y");
        mcLoctnStkTxnDtlPO.setIsTodayRev(isTodayRevTrans(items));
//            mcLoctnStkTxnDtlPO.setRevLoctnStkTxnDtlId("N");
        mcLoctnStkTxnDtlPO.setIsIgnrDatSync("N");
        mcLoctnStkTxnDtlPO.setStkStatCde(items.getStkStatCdeValue());
        mcLoctnStkTxnDtlPO.setRecVerNum(0L);
        mcLoctnStkTxnDtlPO.setInitTime(LocalDateTime.now());
        mcLoctnStkTxnDtlPO.setLastUpdTime(LocalDateTime.now());
        mcLoctnStkTxnDtlPO.setLastUpdBy("MIG");
        mcLoctnStkTxnDtlPO.setTagSeq(0L);

        result.setMcStkTxnRecord(mcStkTxnPO);
        result.setMcAcStkTxnDtlRecord(mcAcStkTxnDtlPO);
        result.setMcAcStkTxnDtlStatRecord(mcAcStkTxnDtlStatPO);
        result.setMcStkMemoTxnRecord(mcStkMemoTxnPO);
        result.setMcStkMemoTxnReqRecord(mcStkMemoTxnReqPO);
        result.setMcLoctnStkTxnDtlRecord(mcLoctnStkTxnDtlPO);

        return result;
    }

    private Long findInstrIdByCodeProcess(String instrument,String typeId) {
        List<McInstrPO> instrIdByCodeList = mcInstrMapper.findInstrIdByCodeList(instrument);
        if (CollectionUtils.isEmpty(instrIdByCodeList)) {
            return null;
        }
        if (typeId.equals("tclId")) {
            return instrIdByCodeList.get(1).getInstclId() == null ? null : instrIdByCodeList.get(1).getInstclId();
        }else {
            return instrIdByCodeList.get(0).getInstrId() == null ? null : instrIdByCodeList.get(0).getInstrId();
        }
    }

    private Long instrumentFindInstclVid(InstrumentVoucherPO po) {
        List<McInstrPO> instclVidByInstrument = mcInstrMapper.findInstrIdByCodeList(po.getInstrument());
        McInstclVerPO instclVidByDate = mcInstclVerMapper.findInstclVidByDate(instclVidByInstrument.get(0).getInstclId(), String.valueOf(po.getConfirmationDate()));
        //为null不设值
        return instclVidByDate.getInstclVid() == null ? null : instclVidByDate.getInstclVid();
    }

    private Long marketCodeConversion(InstrumentVoucherPO po) {
        String code = "";
        switch (po.getMarket()) {
            case "AUS":
                code = "AUS";
                break;
            case "BOD":
            case "BON":
            case "DEO":
            case "DEV":
            case "FUN":
            case "MMF":
                code = "OTC";
                break;
            case "CAN":
                code = "TSX";
                break;
            case "CHA":
                code = "SZSEA";
                break;
            case "CHE":
                code = "SIX";
                break;
            case "DEU":
                code = "FSE";
                break;
            case "FRA":
                code = "PARIS";
                break;
            case "HKG":
                code = "HKEX";
                break;
            case "JPN":
                code = "OSE";
                break;
            case "KOR":
                code = "KSE";
                break;
            case "MAL":
                code = "KLSE";
                break;
            case "SHA":
                code = "HKSSE";
                break;
            case "SHB":
                code = "SSE";
                break;
            case "SIN":
                code = "SGX";
                break;
            case "SZA":
                code = "HKSZSE";
                break;
            case "SZB":
                code = "SZSE";
                break;
            case "TWN":
                code = "TSEC";
                break;
            case "UKG":
                code = "LSE";
                break;
            case "USA":
                code = "NYSEMKT";
                break;
        }
        McMrktPO instrIdByCode = mcMrktMapper.findInstrIdByCode(code);
        return instrIdByCode.getMrktId() == null ? null : instrIdByCode.getMrktId();
    }

    private String isRevTrans(InstrumentVoucherPO po) {
        //2019-06-05 00:00:00.000
        if(po.getCancelDate()!=null && po.getConfirmationDate() != null){
            if (!po.getCancelDate().equals(po.getConfirmationDate()) && po.getStatus().contains("CAN")) {
                return "Y";
            }
        }
        return "N";
    }
    private String isTodayRevTrans(InstrumentVoucherPO po) {
        //2019-06-05 00:00:00.000
        if(po.getCancelDate()!=null && po.getConfirmationDate() != null) {
            if (po.getCancelDate().equals(po.getConfirmationDate()) && po.getStatus().contains("CAN")) {
                return "Y";
            }
        }
        return "N";
    }
}

