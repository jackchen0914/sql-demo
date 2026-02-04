package org.example.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.*;
import org.example.pojo.*;
import org.example.service.IMcAcStkTxnDtlService;
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
public class McAcStkTxnDtlServiceImpl extends ServiceImpl<McAcStkTxnDtlMapper, McAcStkTxnDtlPO> implements IMcAcStkTxnDtlService {

    private final McAcStkTxnDtlMapper mcAcStkTxnDtlMapper;

    private final McAcStkTxnDtlStatMapper mcAcStkTxnDtlStatMapper;

    private final InstrumentVoucherMapper instrumentVoucherMapper;

    private final TransactionTypesMapper transactionTypesMapper;

    private final McInstrMapper mcInstrMapper;

    private final McStkTxnMapper mcStkTxnMapper;

    private final McMrktMapper mcMrktMapper;

    private final McInstclVerMapper mcInstclVerMapper;

    private final McStkMemoTxnMapper mcStkMemoTxnMapper;
    private final McStkMemoTxnReqMapper mcStkMemoTxnReqMapper;
    private final McLoctnStkTxnDtlMapper mcLoctnStkTxnDtlMapper;

    @Override
    public void writeProcessedData() throws InterruptedException {
        List<InstrumentVoucherPO> instrumentVoucherPOS = instrumentVoucherMapper.selectStatusFlagEqualYByPage(1,10);
        List<McAcStkTxnDtlStatPO> mcAcStkTxnDtlStatDataList = new ArrayList<>();
        List<McAcStkTxnDtlPO> mcAcStkTxnDtlDataList = new ArrayList<>();
        List<McStkTxnPO> mcStkTxnDataList = new ArrayList<>();
        List<McStkMemoTxnPO> mcStkMemoTxnDataList = new ArrayList<>();
        List<McStkMemoTxnReqPO> mcStkMemoTxnReqDataList = new ArrayList<>();
        List<McLoctnStkTxnDtlPO> mcLoctnStkTxnDtlDataList = new ArrayList<>();
        for (int i = 0; i < instrumentVoucherPOS.size(); i++) {
            InstrumentVoucherPO po = instrumentVoucherPOS.get(i);
            McAcStkTxnDtlPO mcAcStkTxnDtlPO = new McAcStkTxnDtlPO();
            McAcStkTxnDtlStatPO mcAcStkTxnDtlStatPO = new McAcStkTxnDtlStatPO();
            McStkTxnPO mcStkTxnPO = new McStkTxnPO();
            McStkMemoTxnPO mcStkMemoTxnPO = new McStkMemoTxnPO();
            McStkMemoTxnReqPO mcStkMemoTxnReqPO = new McStkMemoTxnReqPO();
            McLoctnStkTxnDtlPO mcLoctnStkTxnDtlPO = new McLoctnStkTxnDtlPO();

            mcAcStkTxnDtlStatPO.setAcStkTxnDtlStkStatId((long) (7321615 + i));
            mcAcStkTxnDtlStatPO.setAcStkTxnDtlId((long) (7434415 + i));
            mcAcStkTxnDtlStatPO.setStkStatCde(stkStatCdeTrans(po.getLocation(),po.getTxnType()));
            mcAcStkTxnDtlStatPO.setStkQty(po.getQuantity());
            mcAcStkTxnDtlStatPO.setRecVerNum(0L);
            mcAcStkTxnDtlStatPO.setInitTime(LocalDateTime.now());
            mcAcStkTxnDtlStatPO.setLastUpdTime(LocalDateTime.now());
            mcAcStkTxnDtlStatPO.setLastUpdBy("MIG");
            mcAcStkTxnDtlStatPO.setTagSeq(0L);

            mcStkTxnPO.setStkTxnId((long) (7400165 + i));
            mcStkTxnPO.setStkAprvStatCde("APPROVED");
            mcStkTxnPO.setIsRev("N");
            mcStkTxnPO.setIsAwaiting("N");
            mcStkTxnPO.setRecVerNum(0L);
            mcStkTxnPO.setInitTime(LocalDateTime.now());
            mcStkTxnPO.setLastUpdTime(LocalDateTime.now());
            mcStkTxnPO.setLastUpdBy("MIG");
            mcStkTxnPO.setTagSeq(0L);

            mcAcStkTxnDtlPO.setAcStkTxnDtlId((long) (7434415 + i));
            mcAcStkTxnDtlPO.setStkTxnId((long) (7400165 + i));
            mcAcStkTxnDtlPO.setTxnTypId(txnTypIdConvert(po.getTxnType()));
            mcAcStkTxnDtlPO.setTxnTypActnCde(txnTypActnCdeTrans(po.getTxnType()));
            mcAcStkTxnDtlPO.setTxnRefNum(String.valueOf(po.getVoucherNo()));
            mcAcStkTxnDtlPO.setCmpnyCde("TFS");
            mcAcStkTxnDtlPO.setInstrId(findInstrIdByCodeProcess(po.getInstrument(),"instrId"));
            mcAcStkTxnDtlPO.setInstclId(findInstrIdByCodeProcess(po.getInstrument(),"tclId"));
            mcAcStkTxnDtlPO.setInstclVid(instrumentFindInstclVid(po));
            mcAcStkTxnDtlPO.setMrktId(marketCodeConversion(po));
            mcAcStkTxnDtlPO.setValDate(po.getValueDate());
            mcAcStkTxnDtlPO.setInptDate(po.getVoucherDate());
            mcAcStkTxnDtlPO.setMrktBusdate(po.getConfirmationDate());
            mcAcStkTxnDtlPO.setStkChnlCde(po.getManualInput().contains("Yes") ? "MANUALINP" : "SYSTEM");
            mcAcStkTxnDtlPO.setRemrk(po.getRemark());
            mcAcStkTxnDtlPO.setIsAutoAprv("N");
            mcAcStkTxnDtlPO.setIsMemo("N");
            mcAcStkTxnDtlPO.setIsTrnfr("N");
            mcAcStkTxnDtlPO.setIsRev(isRevTrans(po));
            mcAcStkTxnDtlPO.setIsShowInStmt("Y");
            mcAcStkTxnDtlPO.setIsTodayRev(isTodayRevTrans(po));
            mcAcStkTxnDtlPO.setIsIgnrDatSync("N");
//            mcAcStkTxnDtlPO.setAcId(po.getClnt());
            mcAcStkTxnDtlPO.setAcId("02-0000389-30");
            mcAcStkTxnDtlPO.setPrimyRemrkFrStmt(po.getRemark());
            mcAcStkTxnDtlPO.setStmtModuleCde("STOCK");
            mcAcStkTxnDtlPO.setExtrnlTxnRefNum(po.getMarket()+po.getVoucherNo());
            mcAcStkTxnDtlPO.setRecVerNum(0L);
            mcAcStkTxnDtlPO.setInitTime(LocalDateTime.now());
            mcAcStkTxnDtlPO.setLastUpdTime(LocalDateTime.now());
            mcAcStkTxnDtlPO.setLastUpdBy("MIG");
            mcAcStkTxnDtlPO.setTagSeq(0L);
            mcAcStkTxnDtlPO.setIsTpTxn("N");
            mcAcStkTxnDtlPO.setIsOtcrTxn("N");

            //2961703
            mcStkMemoTxnPO.setStkMemoTxnId((long) (2962000+i));
            mcStkMemoTxnPO.setCmpnyCde("TFS");
            mcStkMemoTxnPO.setTxnTypId(txnTypIdConvert(po.getTxnType()));
            mcStkMemoTxnPO.setTxnTypActnCde(txnTypActnCdeTrans(po.getTxnType()));
            mcStkMemoTxnPO.setTxnRefNum(String.valueOf(po.getVoucherNo()));
            mcStkMemoTxnPO.setValDate(po.getValueDate());
            mcStkMemoTxnPO.setRemrk(po.getRemark());
            mcStkMemoTxnPO.setPrimyRemrkFrStmt(po.getRemark());
            mcStkMemoTxnPO.setStkReqStatCde(PropertyConverUtils.fundStatCdeTrans(po.getStatus()));
            mcStkMemoTxnPO.setStkChnlCde(Objects.equals(po.getManualInput(), "YES") ? "MANUALINP" : "SYSTEM");
            mcStkMemoTxnPO.setCreatBy("admin");
            mcStkMemoTxnPO.setCreatMrktBusdate(po.getConfirmationDate());
            mcStkMemoTxnPO.setCreatInptDate(po.getVoucherDate());
            mcStkMemoTxnPO.setLastSubmittedBy("admin");
            mcStkMemoTxnPO.setLastSubmittedMrktBusdate(po.getConfirmationDate());
            mcStkMemoTxnPO.setLastSubmittedInptDate(po.getVoucherDate());
            mcStkMemoTxnPO.setLastSubmittedTime(po.getUserIDTime());
            mcStkMemoTxnPO.setAprvRejectDelBy(po.getApprover());
            mcStkMemoTxnPO.setAprvRejectDelMrktBusdate(po.getConfirmationDate());
            mcStkMemoTxnPO.setAprvRejectDelInptDate(po.getVoucherDate());
            mcStkMemoTxnPO.setAprvRejectDelTime(po.getApprovalTime());
            mcStkMemoTxnPO.setAcId("02-0000389-30");

            mcStkMemoTxnPO.setInstrId(findInstrIdByCodeProcess(po.getInstrument(),"instrId"));
            mcStkMemoTxnPO.setInstclId(findInstrIdByCodeProcess(po.getInstrument(),"tclId"));
            mcStkMemoTxnPO.setInstclVid(instrumentFindInstclVid(po));
            mcStkMemoTxnPO.setMrktId(marketCodeConversion(po));

            mcStkMemoTxnPO.setStkStatCde(stkStatCdeTrans(po.getLocation(),po.getTxnType()));
            mcStkMemoTxnPO.setStkQty(po.getQuantity());
            mcStkMemoTxnPO.setRecVerNum(0L);
            mcStkMemoTxnPO.setInitTime(LocalDateTime.now());
            mcStkMemoTxnPO.setLastUpdTime(LocalDateTime.now());
            mcStkMemoTxnPO.setLastUpdBy("MIG");
            mcStkMemoTxnPO.setTagSeq(0L);
            mcStkMemoTxnPO.setExtrnlRefNum(po.getMarket()+po.getVoucherNo());
            mcStkMemoTxnPO.setIsTpTxn("N");
            mcStkMemoTxnPO.setIsOtcrTxn("N");
            mcStkMemoTxnPO.setIsRevTxn("N");
            mcStkMemoTxnPO.setStkLoctnId(1L);

            mcStkMemoTxnReqPO.setStkMemoTxnReqId((long)1+i);
            mcStkMemoTxnReqPO.setStkMemoTxnId((long) (2962000+i));
            mcStkMemoTxnReqPO.setStkTxnId((long) (7400165 + i));
            mcStkMemoTxnReqPO.setIsRev(isRevTrans(po));
            mcStkMemoTxnReqPO.setStkReqStatCde(PropertyConverUtils.fundStatCdeTrans(po.getStatus()));
            mcStkMemoTxnReqPO.setStkAprvStatCde("APPROVED");
            mcStkMemoTxnReqPO.setRecVerNum(0L);
            mcStkMemoTxnReqPO.setInitTime(LocalDateTime.now());
            mcStkMemoTxnReqPO.setLastUpdTime(LocalDateTime.now());
            mcStkMemoTxnReqPO.setLastUpdBy("MIG");
            mcStkMemoTxnReqPO.setTagSeq(0L);

            mcLoctnStkTxnDtlPO.setLoctnStkTxnDtlId((long) (7500000+i));
            mcLoctnStkTxnDtlPO.setCmpnyCde("TFS");
            mcLoctnStkTxnDtlPO.setStkTxnId((long) (7400165 + i));
            mcLoctnStkTxnDtlPO.setTxnTypId(txnTypIdConvert(po.getTxnType()));
            mcLoctnStkTxnDtlPO.setTxnTypActnCde(txnTypActnCdeTrans(po.getTxnType()));
            mcLoctnStkTxnDtlPO.setTxnRefNum(String.valueOf(po.getVoucherNo()));
            mcLoctnStkTxnDtlPO.setInstrId(findInstrIdByCodeProcess(po.getInstrument(),"instrId"));
            mcLoctnStkTxnDtlPO.setInstclId(findInstrIdByCodeProcess(po.getInstrument(),"tclId"));
            mcLoctnStkTxnDtlPO.setInstclVid(instrumentFindInstclVid(po));
            mcLoctnStkTxnDtlPO.setMrktId(marketCodeConversion(po));
            mcLoctnStkTxnDtlPO.setValDate(po.getValueDate());
            mcLoctnStkTxnDtlPO.setInptDate(po.getVoucherDate());
            mcLoctnStkTxnDtlPO.setMrktBusdate(po.getConfirmationDate());
            mcLoctnStkTxnDtlPO.setStkChnlCde(Objects.equals(po.getManualInput(), "YES") ? "MANUALINP" : "SYSTEM");
            mcLoctnStkTxnDtlPO.setStkQty(po.getQuantity());
            mcLoctnStkTxnDtlPO.setRemrk(po.getRemark());
            mcLoctnStkTxnDtlPO.setIsMemo("N");
            mcLoctnStkTxnDtlPO.setIsAutoAprv("N");
            mcLoctnStkTxnDtlPO.setIsTrnfr("N");
            mcLoctnStkTxnDtlPO.setIsRev(isRevTrans(po));
            mcLoctnStkTxnDtlPO.setIsShowInStmt("Y");
            mcLoctnStkTxnDtlPO.setIsTodayRev(isTodayRevTrans(po));
//            mcLoctnStkTxnDtlPO.setRevLoctnStkTxnDtlId("N");
            mcLoctnStkTxnDtlPO.setIsIgnrDatSync("N");
            mcLoctnStkTxnDtlPO.setStkStatCde(stkStatCdeTrans(po.getLocation(),po.getTxnType()));
            mcLoctnStkTxnDtlPO.setRecVerNum(0L);
            mcLoctnStkTxnDtlPO.setInitTime(LocalDateTime.now());
            mcLoctnStkTxnDtlPO.setLastUpdTime(LocalDateTime.now());
            mcLoctnStkTxnDtlPO.setLastUpdBy("MIG");
            mcLoctnStkTxnDtlPO.setTagSeq(0L);
            mcLoctnStkTxnDtlPO.setStkLoctnId(1L);

            mcStkTxnDataList.add(mcStkTxnPO);
            mcAcStkTxnDtlDataList.add(mcAcStkTxnDtlPO);
            mcAcStkTxnDtlStatDataList.add(mcAcStkTxnDtlStatPO);
            mcStkMemoTxnDataList.add(mcStkMemoTxnPO);
            mcStkMemoTxnReqDataList.add(mcStkMemoTxnReqPO);
            mcLoctnStkTxnDtlDataList.add(mcLoctnStkTxnDtlPO);
        }
        saveToOracleMcStkTxn(mcStkTxnDataList);
        Thread.sleep(2000);
        saveToOracleMcAcStkTxn(mcAcStkTxnDtlDataList);
        Thread.sleep(2000);
        saveToOracleMcAcStkTxnStat(mcAcStkTxnDtlStatDataList);
        saveToOracle4(mcStkMemoTxnDataList);
        saveToOracle5(mcStkMemoTxnReqDataList);
        saveToOracle6(mcLoctnStkTxnDtlDataList);
        //MC_STK_TXN
        //MC_AC_STK_txn
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

    private Long txnTypIdConvert(String code) {
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
        if(po.getMarket().equals("AUS")){
            code = "AUS";
        } else if (po.getMarket().equals("BOD") || po.getMarket().equals("BON")||
                po.getMarket().equals("DEO")|| po.getMarket().equals("DEV")|| po.getMarket().equals("FUN")||po.getMarket().equals("MMF")) {
            code = "OTC";
        }else if (po.getMarket().equals("CAN")) {
            code = "TSX";
        }else if (po.getMarket().equals("CHA")) {
            code = "SZSEA";
        }else if (po.getMarket().equals("CHE")) {
            code = "SIX";
        }else if (po.getMarket().equals("DEU")) {
            code = "FSE";
        }else if (po.getMarket().equals("FRA")) {
            code = "PARIS";
        }else if (po.getMarket().equals("HKG")) {
            code = "HKEX";
        }else if (po.getMarket().equals("JPN")) {
            code = "OSE";
        }else if (po.getMarket().equals("KOR")) {
            code = "KSE";
        }else if (po.getMarket().equals("MAL")) {
            code = "KLSE";
        }else if (po.getMarket().equals("SHA")) {
            code = "HKSSE";
        }else if (po.getMarket().equals("SHB")) {
            code = "SSE";
        }else if (po.getMarket().equals("SIN")) {
            code = "SGX";
        }else if (po.getMarket().equals("SZA")) {
            code = "HKSZSE";
        }else if (po.getMarket().equals("SZB")) {
            code = "SZSE";
        }else if (po.getMarket().equals("TWN")) {
            code = "TSEC";
        }else if (po.getMarket().equals("UKG")) {
            code = "LSE";
        }else if (po.getMarket().equals("USA")) {
            code = "NYSEMKT";
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
//                return "DIP(Deposit)/WIP(Withdraw)";
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
    public void saveToOracle4(List<McStkMemoTxnPO> list){
        mcStkMemoTxnMapper.insert(list);
    }

    @DS("oracle")
    @Transactional(rollbackFor = Exception.class)
    public void saveToOracle5(List<McStkMemoTxnReqPO> list){
        mcStkMemoTxnReqMapper.insert(list);
    }

    @DS("oracle")
    @Transactional(rollbackFor = Exception.class)
    public void saveToOracle6(List<McLoctnStkTxnDtlPO> list){
        mcLoctnStkTxnDtlMapper.insert(list);
    }

    @DS("oracle")
    @Transactional(rollbackFor = Exception.class)
    public void saveToOracleMcAcStkTxnStat(List<McAcStkTxnDtlStatPO> list){
        mcAcStkTxnDtlStatMapper.insert(list);
    }

    @DS("oracle")
    @Transactional(rollbackFor = Exception.class)
    public void saveToOracleMcAcStkTxn(List<McAcStkTxnDtlPO> list){
        mcAcStkTxnDtlMapper.insert(list);
    }

    @DS("oracle")
    @Transactional(rollbackFor = Exception.class)
    public void saveToOracleMcStkTxn(List<McStkTxnPO> list){
        mcStkTxnMapper.insert(list);
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

    private String txnTypActnCdeTrans(String code) {
        TransactionTypesPO transactionTypesPO = transactionTypesMapper.selectTxnTypeActionCode(code);
        if (transactionTypesPO.getSignIndicator() != null) {
            return transactionTypesPO.getSignIndicator().equals("D") ? "IN" : "OUT";
        }
        return " ";
    }


}
