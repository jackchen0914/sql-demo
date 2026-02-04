package org.example.batch.processor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.example.mapper.McInstrMapper;
import org.example.mapper.McMrktMapper;
import org.example.pojo.*;
import org.example.pojo.dtos.HoldCashResultDTO;
import org.example.pojo.dtos.HoldInstrumentResultDTO;
import org.example.service.IdGeneratorService;
import org.example.utils.PropertyConverUtils;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Component
@Slf4j
@RequiredArgsConstructor
public class HoldInstrumentProcessor implements ItemProcessor<InstrumentVoucherPO, HoldInstrumentResultDTO> {
    
    private final IdGeneratorService idGeneratorService;
    private final McInstrMapper mcInstrMapper;
    private final McMrktMapper mcMrktMapper;

    @Override
    public HoldInstrumentResultDTO process(InstrumentVoucherPO items) throws Exception {
        if(items == null){
            return null;
        }

        HoldInstrumentResultDTO result = new HoldInstrumentResultDTO();

        Long mainId = idGeneratorService.generateMainId();

        McAcHoldStkTxnDtlPO mcAcHoldStkTxnDtlPO = new McAcHoldStkTxnDtlPO();
        McAcHoldStkTxnPO mcAcHoldStkTxnPO = new McAcHoldStkTxnPO();
        mcAcHoldStkTxnPO.setAcHoldStkTxnId(mainId);
        mcAcHoldStkTxnPO.setIsRelse(items.getStatusFlag().charAt(8) == 'Y' ? "Y" : "N");
        mcAcHoldStkTxnPO.setRecVerNum(0L);
        mcAcHoldStkTxnPO.setInitTime(LocalDateTime.now());
        mcAcHoldStkTxnPO.setLastUpdTime(LocalDateTime.now());
        mcAcHoldStkTxnPO.setLastUpdBy("MIG");
        mcAcHoldStkTxnPO.setTagSeq(0L);

        mcAcHoldStkTxnDtlPO.setAcHoldStkTxnDtlId(idGeneratorService.generateDetailId());
        mcAcHoldStkTxnDtlPO.setAcHoldStkTxnId(mainId);
        mcAcHoldStkTxnDtlPO.setTxnRefNum("HE"+items.getVoucherNo());
//            mcAcHoldStkTxnDtlPO.setTxnTypId();
//            mcAcHoldStkTxnDtlPO.setAcId(items.getClnt());
        mcAcHoldStkTxnDtlPO.setAcId("02-0000457-30");
        mcAcHoldStkTxnDtlPO.setCmpnyCde("TFS");
        mcAcHoldStkTxnDtlPO.setInstrId(findInstrIdByCodeProcess(items.getInstrument(),"instrId"));
        mcAcHoldStkTxnDtlPO.setInstclId(findInstrIdByCodeProcess(items.getInstrument(),"tclId"));
        mcAcHoldStkTxnDtlPO.setMrktId(marketCodeConversion(items));
        mcAcHoldStkTxnDtlPO.setMrktBusdate(items.getVoucherDate());
        mcAcHoldStkTxnDtlPO.setExtrnlRefNum(items.getMarket()+items.getVoucherNo());
        mcAcHoldStkTxnDtlPO.setHoldStkQty(items.getQuantity());
        mcAcHoldStkTxnDtlPO.setExprDate(items.getValueDate());
        mcAcHoldStkTxnDtlPO.setHoldStkStatCde(holdStkStatCodeConver(items));
        mcAcHoldStkTxnDtlPO.setHoldStkChnlCde(Objects.equals(items.getManualInput(), "Yes") ? "MANUAL" : "SYSTEM");
        mcAcHoldStkTxnDtlPO.setRemrk(items.getRemark());
        mcAcHoldStkTxnDtlPO.setHoldDate(items.getValueDate());
        mcAcHoldStkTxnDtlPO.setHoldBy(items.getUserid());
        mcAcHoldStkTxnDtlPO.setRelseDate(Objects.equals(accordingStatusConverVal(items.getStatusFlag()), "Y") ? items.getValueDate() : null);
        mcAcHoldStkTxnDtlPO.setRelseBy(Objects.equals(accordingStatusConverVal(items.getStatusFlag()), "Y") ? items.getUserid() : null);
        mcAcHoldStkTxnDtlPO.setRelseStkQty(Objects.equals(accordingStatusConverVal(items.getStatusFlag()), "Y") ? items.getQuantity() : BigDecimal.ZERO);
        mcAcHoldStkTxnDtlPO.setSrcSysCde("OctOBO");
        mcAcHoldStkTxnDtlPO.setRecVerNum(0L);
        mcAcHoldStkTxnDtlPO.setInitTime(LocalDateTime.now());
        mcAcHoldStkTxnDtlPO.setLastUpdTime(LocalDateTime.now());
        mcAcHoldStkTxnDtlPO.setLastUpdBy("MIG");
        mcAcHoldStkTxnDtlPO.setTagSeq(0L);

        result.setMainRecord(mcAcHoldStkTxnDtlPO);
        result.setDetailRecord(mcAcHoldStkTxnPO);

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

    private String holdStkStatCodeConver(InstrumentVoucherPO po) {
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
        return instrIdByCode.getMrktId();
    }

    private String accordingStatusConverVal(String flag) {
        if(StringUtils.isBlank(flag) || flag.length() < 7) return null;
        if(flag.charAt(8) == 'Y'){
            return "Y";
        }
        return "N";
    }
    
}
