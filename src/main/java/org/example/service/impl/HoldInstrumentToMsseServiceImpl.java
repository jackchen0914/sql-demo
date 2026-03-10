package org.example.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.example.mapper.*;
import org.example.pojo.*;
import org.example.service.HoldInstrumentToMsseService;
import org.example.service.IMcAcHoldStkTxnDtlService;
import org.example.service.IdGeneratorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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
 * @since 2025-12-24
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class HoldInstrumentToMsseServiceImpl implements HoldInstrumentToMsseService {

    private final McAcHoldStkTxnDtlMapper mcAcHoldStkTxnDtlMapper;
    private final McAcHoldStkTxnMapper mcAcHoldStkTxnMapper;
    private final McInstrMapper mcInstrMapper;
    private final McMrktMapper mcMrktMapper;

    private final InstrumentVoucherMapper instrumentVoucherMapper;
    private final TransactionTypesMapper transactionTypesMapper;
    private final HoldInstrumentMapMSSEMapper holdInstrumentMapMSSEMapper;
    
    private final IdGeneratorService idGeneratorService;
    
    @Override
    public String writeProcessedData() {
        List<InstrumentVoucherPO> instrumentVoucherPOS = instrumentVoucherMapper.selectStatusFlagEqualYQuantityByPage(1,10);
        List<McAcHoldStkTxnDtlPO> holdStkTxnDtlPOList = new ArrayList<>();
        List<McAcHoldStkTxnPO> holdStkTxnPOList = new ArrayList<>();
        List<HoldInstrumentMapMSSEPO> holdInstrumentMapMSSEPOList = new ArrayList<>();

        for (int i = 0; i < instrumentVoucherPOS.size(); i++) {
            InstrumentVoucherPO items = instrumentVoucherPOS.get(i);
            Long mainId = idGeneratorService.generateMainId();
            Long acHoldStkTxnDtlId = idGeneratorService.generateDetailId();
            McAcHoldStkTxnDtlPO mcAcHoldStkTxnDtlPO = new McAcHoldStkTxnDtlPO();
            McAcHoldStkTxnPO mcAcHoldStkTxnPO = new McAcHoldStkTxnPO();
            HoldInstrumentMapMSSEPO holdInstrumentMapMSSEPO = new HoldInstrumentMapMSSEPO();
            
            mcAcHoldStkTxnPO.setAcHoldStkTxnId(mainId);
            mcAcHoldStkTxnPO.setIsRelse(items.getStatusFlag().charAt(8) == 'Y' ? "Y" : "N");
            mcAcHoldStkTxnPO.setRecVerNum(0L);
            mcAcHoldStkTxnPO.setInitTime(LocalDateTime.now());
            mcAcHoldStkTxnPO.setLastUpdTime(LocalDateTime.now());
            mcAcHoldStkTxnPO.setLastUpdBy("MIG");
            mcAcHoldStkTxnPO.setTagSeq(0L);

            mcAcHoldStkTxnDtlPO.setAcHoldStkTxnDtlId(acHoldStkTxnDtlId);
            mcAcHoldStkTxnDtlPO.setAcHoldStkTxnId(mainId);
            mcAcHoldStkTxnDtlPO.setTxnRefNum("HE"+items.getVoucherNo());
            mcAcHoldStkTxnDtlPO.setTxnTypId(txnTypIdValueConvert(items.getTxnType()));
//            mcAcHoldStkTxnDtlPO.setAcId(items.getClnt());
            mcAcHoldStkTxnDtlPO.setAcId("02-0000457-30");
            mcAcHoldStkTxnDtlPO.setCmpnyCde("TFS");
            mcAcHoldStkTxnDtlPO.setInstrId(findInstrIdByCodeProcess(items.getInstrument(),"instrId"));
            mcAcHoldStkTxnDtlPO.setInstclId(findInstrIdByCodeProcess(items.getInstrument(),"tclId"));
            mcAcHoldStkTxnDtlPO.setMrktId(marketCodeConvert(items));
            mcAcHoldStkTxnDtlPO.setMrktBusdate(items.getVoucherDate());
            mcAcHoldStkTxnDtlPO.setExtrnlRefNum(items.getMarket()+items.getVoucherNo());
            mcAcHoldStkTxnDtlPO.setHoldStkQty(items.getQuantity().abs());
            mcAcHoldStkTxnDtlPO.setExprDate(items.getValueDate());
            mcAcHoldStkTxnDtlPO.setHoldStkStatCde(holdStkStatCodeConvert(items));
            mcAcHoldStkTxnDtlPO.setHoldStkChnlCde(Objects.equals(items.getManualInput(), "Yes") ? "MANUAL" : "SYSTEM");
            mcAcHoldStkTxnDtlPO.setRemrk(items.getRemark());
            mcAcHoldStkTxnDtlPO.setHoldDate(items.getValueDate());
            mcAcHoldStkTxnDtlPO.setHoldBy(items.getUserid());
            mcAcHoldStkTxnDtlPO.setRelseDate(Objects.equals(accordingStatusValueConvert(items.getStatusFlag()), "Y") ? items.getValueDate() : null);
            mcAcHoldStkTxnDtlPO.setRelseBy(Objects.equals(accordingStatusValueConvert(items.getStatusFlag()), "Y") ? items.getUserid() : null);
            mcAcHoldStkTxnDtlPO.setRelseStkQty(Objects.equals(accordingStatusValueConvert(items.getStatusFlag()), "Y") ? items.getQuantity() : BigDecimal.ZERO);
            mcAcHoldStkTxnDtlPO.setSrcSysCde("OctOBO");
            mcAcHoldStkTxnDtlPO.setRecVerNum(0L);
            mcAcHoldStkTxnDtlPO.setInitTime(LocalDateTime.now());
            mcAcHoldStkTxnDtlPO.setLastUpdTime(LocalDateTime.now());
            mcAcHoldStkTxnDtlPO.setLastUpdBy("MIG");
            mcAcHoldStkTxnDtlPO.setTagSeq(0L);

            holdInstrumentMapMSSEPO.setVoucherNo(items.getVoucherNo());
            holdInstrumentMapMSSEPO.setMarket(items.getMarket());
            holdInstrumentMapMSSEPO.setAcHoldStkTxnId(mainId);
            holdInstrumentMapMSSEPO.setAcHoldStkTxnDtlId(acHoldStkTxnDtlId);

            holdInstrumentMapMSSEPOList.add(holdInstrumentMapMSSEPO);
            holdStkTxnPOList.add(mcAcHoldStkTxnPO);
            holdStkTxnDtlPOList.add(mcAcHoldStkTxnDtlPO);
        }
        holdInstrumentMapMSSEMapper.insert(holdInstrumentMapMSSEPOList);
        saveToOracleMcAcHoldStkTxn(holdStkTxnPOList);
        saveToOracleMcAcHoldStkTxnDtl(holdStkTxnDtlPOList);
        return "ok";
    }

    private Long txnTypIdValueConvert(String type){
        TransactionTypesPO transactionTypesPO = transactionTypesMapper.selectTxnTypeActionCode(type);
        return transactionTypesPO.getSignIndicator().equals("D") ? 7506L : 2506L;
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

    private String holdStkStatCodeConvert(InstrumentVoucherPO po) {
        if(StringUtils.isBlank(po.getStatusFlag()) || po.getStatusFlag().length() < 7) return null;
        if(po.getStatusFlag().charAt(7) == 'Y' && po.getStatusFlag().charAt(8) == 'N' && po.getStatus().contains("APP")){
            return "ACTIVE";
        }else if(po.getStatusFlag().charAt(7) == 'Y' && po.getStatusFlag().charAt(8) == 'N' && (po.getStatus().contains("DEL") || po.getStatus().contains("CAN"))){
            return "DELETED";
        }else if (po.getStatusFlag().charAt(7) == 'Y' && po.getStatusFlag().charAt(8) == 'Y' && po.getStatus().contains("APP")){
            return "RELEASED";
        }
        return null;
    }

    private Long marketCodeConvert(InstrumentVoucherPO po) {
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
        return instrIdByCode.getMrktId();
    }

    private String accordingStatusValueConvert(String flag) {
        if(StringUtils.isBlank(flag) || flag.length() < 7) return null;
        if(flag.charAt(8) == 'Y'){
            return "Y";
        }
        return "N";
    }

    @DS("oracle")
    @Transactional(rollbackFor = Exception.class)
    public void saveToOracleMcAcHoldStkTxnDtl(List<McAcHoldStkTxnDtlPO> list){
        mcAcHoldStkTxnDtlMapper.batchInsert(list);
    }

    @DS("oracle")
    @Transactional(rollbackFor = Exception.class)
    public void saveToOracleMcAcHoldStkTxn(List<McAcHoldStkTxnPO> list){
        mcAcHoldStkTxnMapper.batchInsert(list);
    }

}
