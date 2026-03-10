package org.example.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.*;
import org.example.pojo.*;
import org.example.service.IdGeneratorService;
import org.example.service.InstrumentVoucherToMsseService;
import org.example.utils.PropertyConverUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author JackChen
 * @since 2025-12-19
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class InstrumentVoucherToMsseServiceImpl implements InstrumentVoucherToMsseService {

    private final InstrumentVoucherMapper instrumentVoucherMapper;
    private final TransactionTypesMapper transactionTypesMapper;
    private final InstrumentVoucherMapMSSEMapper instrumentVoucherMapMSSEMapper;

    private final McAcStkTxnDtlMapper mcAcStkTxnDtlMapper;
    private final McAcStkTxnDtlStatMapper mcAcStkTxnDtlStatMapper;
    private final McInstrMapper mcInstrMapper;
    private final McStkTxnMapper mcStkTxnMapper;
    private final McMrktMapper mcMrktMapper;
    private final McInstclVerMapper mcInstclVerMapper;
    private final McStkMemoTxnMapper mcStkMemoTxnMapper;
    private final McStkMemoTxnReqMapper mcStkMemoTxnReqMapper;
    private final McLoctnStkTxnDtlMapper mcLoctnStkTxnDtlMapper;

    private final IdGeneratorService idGeneratorService;

    @Override
    public String writeProcessedData() {
        List<InstrumentVoucherPO> instrumentVoucherPOS = instrumentVoucherMapper.selectStatusFlagUnequalYByPage(1,10);
        List<McAcStkTxnDtlStatPO> mcAcStkTxnDtlStatPOList = new ArrayList<>();
        List<McAcStkTxnDtlPO> mcAcStkTxnDtlPOList = new ArrayList<>();
        List<McStkTxnPO> mcStkTxnPOList = new ArrayList<>();
        List<McStkMemoTxnPO> mcStkMemoTxnPOList = new ArrayList<>();
        List<McStkMemoTxnReqPO> mcStkMemoTxnReqPOList = new ArrayList<>();
        List<McLoctnStkTxnDtlPO> mcLoctnStkTxnDtlPOList = new ArrayList<>();
        List<InstrumentVoucherMapMSSEPO> instrumentVoucherMapMSSEPOList = new ArrayList<>();
        for (int i = 0; i < instrumentVoucherPOS.size(); i++) {
            InstrumentVoucherPO items = instrumentVoucherPOS.get(i);
            Long stkTxnRelevanceId = idGeneratorService.generateMainId();
            Long acStkTxnDtlRelevanceId = idGeneratorService.generateMainId();
            Long setStkMemoTxnRelevanceId = idGeneratorService.generateMainId();
            Long acStkTxnDtlStkStatId = idGeneratorService.generateDetailId();
            Long loctnStkTxnDtlId = idGeneratorService.generateDetailId();
            Long stkMemoTxnReqId = idGeneratorService.generateDetailId();
            McAcStkTxnDtlPO mcAcStkTxnDtlPO = new McAcStkTxnDtlPO();
            McAcStkTxnDtlStatPO mcAcStkTxnDtlStatPO = new McAcStkTxnDtlStatPO();
            McStkTxnPO mcStkTxnPO = new McStkTxnPO();
            McStkMemoTxnPO mcStkMemoTxnPO = new McStkMemoTxnPO();
            McStkMemoTxnReqPO mcStkMemoTxnReqPO = new McStkMemoTxnReqPO();
            McLoctnStkTxnDtlPO mcLoctnStkTxnDtlPO = new McLoctnStkTxnDtlPO();
            InstrumentVoucherMapMSSEPO instrumentVoucherMapMSSEPO = new InstrumentVoucherMapMSSEPO();

            mcAcStkTxnDtlStatPO.setAcStkTxnDtlStkStatId(acStkTxnDtlStkStatId);
            mcAcStkTxnDtlStatPO.setAcStkTxnDtlId((acStkTxnDtlRelevanceId));
            mcAcStkTxnDtlStatPO.setStkStatCde(stkStatCdeTrans(items.getLocation(),items.getTxnType()));
            mcAcStkTxnDtlStatPO.setStkQty(items.getQuantity());
            mcAcStkTxnDtlStatPO.setRecVerNum(0L);
            mcAcStkTxnDtlStatPO.setInitTime(LocalDateTime.now());
            mcAcStkTxnDtlStatPO.setLastUpdTime(LocalDateTime.now());
            mcAcStkTxnDtlStatPO.setLastUpdBy("MIG");
            mcAcStkTxnDtlStatPO.setTagSeq(0L);

            mcStkTxnPO.setStkTxnId(stkTxnRelevanceId);
            mcStkTxnPO.setStkAprvStatCde("APPROVED");
            mcStkTxnPO.setIsRev("N");
            mcStkTxnPO.setIsAwaiting("N");
            mcStkTxnPO.setRecVerNum(0L);
            mcStkTxnPO.setInitTime(LocalDateTime.now());
            mcStkTxnPO.setLastUpdTime(LocalDateTime.now());
            mcStkTxnPO.setLastUpdBy("MIG");
            mcStkTxnPO.setTagSeq(0L);

            mcAcStkTxnDtlPO.setAcStkTxnDtlId(acStkTxnDtlRelevanceId);
            mcAcStkTxnDtlPO.setStkTxnId(stkTxnRelevanceId);
            mcAcStkTxnDtlPO.setTxnTypId(txnTypIdValueConvert(items.getTxnType()));
            mcAcStkTxnDtlPO.setTxnTypActnCde(txnTypActnCdeValueConvert(items.getTxnType()));
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
            mcStkMemoTxnPO.setStkMemoTxnId(setStkMemoTxnRelevanceId);
            mcStkMemoTxnPO.setCmpnyCde("TFS");
            mcStkMemoTxnPO.setTxnTypId(txnTypIdValueConvert(items.getTxnType()));
            mcStkMemoTxnPO.setTxnTypActnCde(txnTypActnCdeValueConvert(items.getTxnType()));
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
            mcStkMemoTxnPO.setStkStatCde(stkStatCdeTrans(items.getLocation(),items.getTxnType()));
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

            mcStkMemoTxnReqPO.setStkMemoTxnReqId(stkMemoTxnReqId);
            mcStkMemoTxnReqPO.setStkMemoTxnId(setStkMemoTxnRelevanceId);
            mcStkMemoTxnReqPO.setStkTxnId(stkTxnRelevanceId);
            mcStkMemoTxnReqPO.setIsRev(isRevTrans(items));
            mcStkMemoTxnReqPO.setStkReqStatCde(PropertyConverUtils.fundStatCdeTrans(items.getStatus()));
            mcStkMemoTxnReqPO.setStkAprvStatCde("APPROVED");
            mcStkMemoTxnReqPO.setRecVerNum(0L);
            mcStkMemoTxnReqPO.setInitTime(LocalDateTime.now());
            mcStkMemoTxnReqPO.setLastUpdTime(LocalDateTime.now());
            mcStkMemoTxnReqPO.setLastUpdBy("MIG");
            mcStkMemoTxnReqPO.setTagSeq(0L);

            mcLoctnStkTxnDtlPO.setLoctnStkTxnDtlId(loctnStkTxnDtlId);
            mcLoctnStkTxnDtlPO.setCmpnyCde("TFS");
            mcLoctnStkTxnDtlPO.setStkTxnId(stkTxnRelevanceId);
            mcLoctnStkTxnDtlPO.setTxnTypId(txnTypIdValueConvert(items.getTxnType()));
            mcLoctnStkTxnDtlPO.setTxnTypActnCde(txnTypActnCdeValueConvert(items.getTxnType()));
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
            mcLoctnStkTxnDtlPO.setStkStatCde(stkStatCdeTrans(items.getLocation(),items.getTxnType()));
            mcLoctnStkTxnDtlPO.setRecVerNum(0L);
            mcLoctnStkTxnDtlPO.setInitTime(LocalDateTime.now());
            mcLoctnStkTxnDtlPO.setLastUpdTime(LocalDateTime.now());
            mcLoctnStkTxnDtlPO.setLastUpdBy("MIG");
            mcLoctnStkTxnDtlPO.setTagSeq(0L);

            instrumentVoucherMapMSSEPO.setVoucherNo(items.getVoucherNo());
            instrumentVoucherMapMSSEPO.setMarket(items.getMarket());
            instrumentVoucherMapMSSEPO.setAcStkTxnDtlStkStatId(acStkTxnDtlStkStatId);
            instrumentVoucherMapMSSEPO.setAcStkTxnDtlId(acStkTxnDtlRelevanceId);
            instrumentVoucherMapMSSEPO.setStkMemoTxnId(setStkMemoTxnRelevanceId);
            instrumentVoucherMapMSSEPO.setStkMemoTxnReqId(stkMemoTxnReqId);

            instrumentVoucherMapMSSEPOList.add(instrumentVoucherMapMSSEPO);
            mcStkTxnPOList.add(mcStkTxnPO);
            mcAcStkTxnDtlPOList.add(mcAcStkTxnDtlPO);
            mcAcStkTxnDtlStatPOList.add(mcAcStkTxnDtlStatPO);
            mcStkMemoTxnPOList.add(mcStkMemoTxnPO);
            mcStkMemoTxnReqPOList.add(mcStkMemoTxnReqPO);
            mcLoctnStkTxnDtlPOList.add(mcLoctnStkTxnDtlPO);
        }
        instrumentVoucherMapMSSEMapper.insert(instrumentVoucherMapMSSEPOList);
        saveToMcStkTxn(mcStkTxnPOList);
        saveToMcAcStkTxnDtl(mcAcStkTxnDtlPOList);
        saveToMcAcStkTxnDtlStat(mcAcStkTxnDtlStatPOList);
        saveToMcStkMemoTxn(mcStkMemoTxnPOList);
        saveToMcStkMemoTxnReq(mcStkMemoTxnReqPOList);
        saveToMcLoctnStkTxnDtl(mcLoctnStkTxnDtlPOList);
        return "ok";
    }


    private Long findInstrIdByCodeProcess(String instrument,String typeId) {
        List<McInstrPO> instrIdByCodeList = mcInstrMapper.findInstrIdByCodeList(instrument);
        if (CollectionUtils.isEmpty(instrIdByCodeList)) {
            return null;
        }
        if (typeId.equals("tclId")) {
            return instrIdByCodeList.get(0).getInstclId() == null ? null : instrIdByCodeList.get(0).getInstclId();
        }else {
            return instrIdByCodeList.get(0).getInstrId() == null ? null : instrIdByCodeList.get(0).getInstrId();
        }
    }

    private Long txnTypIdValueConvert(String code) {
        TransactionTypesPO transactionTypesPO = transactionTypesMapper.selectTxnTypeActionCode(code);
        return transactionTypesPO.getSignIndicator().equals("D") ? 2501L : 2522L;
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

    private String stkStatCdeTrans(String location,String txnType) {
        switch (location){
            case "NOM":
                return "NOMINEE";
            case "OWM":
                return "OWNERNAME";
            case "BONUS":
                return "BONUS";
            case "ST":
                return findByStatCde(txnType);
            default: return " ";
        }
    }

    private String findByStatCde(String txnType) {
        TransactionTypesPO transactionTypesPO = transactionTypesMapper.selectTxnTypeActionCode(txnType);
        if (transactionTypesPO.getSignIndicator() != null) {
            return transactionTypesPO.getSignIndicator().equals("D") ? "DIP" : "WIP";
        }
        return null;
    }

    @DS("oracle")
    @Transactional(rollbackFor = Exception.class)
    public void saveToMcAcStkTxnDtlStat(List<McAcStkTxnDtlStatPO> list){
        mcAcStkTxnDtlStatMapper.batchInsert(list);
    }

    @DS("oracle")
    @Transactional(rollbackFor = Exception.class)
    public void saveToMcAcStkTxnDtl(List<McAcStkTxnDtlPO> list){
        mcAcStkTxnDtlMapper.batchInsert(list);
    }

    @DS("oracle")
    @Transactional(rollbackFor = Exception.class)
    public void saveToMcStkTxn(List<McStkTxnPO> list){
        mcStkTxnMapper.batchInsert(list);
    }

    @DS("oracle")
    @Transactional(rollbackFor = Exception.class)
    public void saveToMcStkMemoTxn(List<McStkMemoTxnPO> list){
        mcStkMemoTxnMapper.batchInsert(list);
    }

    @DS("oracle")
    @Transactional(rollbackFor = Exception.class)
    public void saveToMcStkMemoTxnReq(List<McStkMemoTxnReqPO> list){
        mcStkMemoTxnReqMapper.batchInsert(list);
    }

    @DS("oracle")
    @Transactional(rollbackFor = Exception.class)
    public void saveToMcLoctnStkTxnDtl(List<McLoctnStkTxnDtlPO> list){
        mcLoctnStkTxnDtlMapper.batchInsert(list);
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

    private String txnTypActnCdeValueConvert(String code) {
        TransactionTypesPO transactionTypesPO = transactionTypesMapper.selectTxnTypeActionCode(code);
        if (transactionTypesPO.getSignIndicator() != null) {
            return transactionTypesPO.getSignIndicator().equals("D") ? "IN" : "OUT";
        }
        return " ";
    }


}
