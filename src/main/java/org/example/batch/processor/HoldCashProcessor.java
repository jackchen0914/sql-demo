package org.example.batch.processor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.example.pojo.*;
import org.example.pojo.dtos.HoldCashResultDTO;
import org.example.service.IdGeneratorService;
import org.example.utils.PropertyConverUtils;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Component
@Slf4j
@RequiredArgsConstructor
public class HoldCashProcessor implements ItemProcessor<CashVoucherPO, HoldCashResultDTO> {
    
    private final IdGeneratorService idGeneratorService;

    @Override
    public HoldCashResultDTO process(CashVoucherPO items) throws Exception {
        if(items == null){
            return null;
        }

        HoldCashResultDTO result = new HoldCashResultDTO();

        Long mainId = idGeneratorService.generateMainId();

        McAcFundHoldRecPO mcAcFundHoldRecPO = new McAcFundHoldRecPO();
        McAcFundHoldTxnPO mcAcFundHoldTxnPO = new McAcFundHoldTxnPO();

        mcAcFundHoldRecPO.setAcFundHoldRid(idGeneratorService.generateDetailId());
        mcAcFundHoldRecPO.setCmpnyIbusdate(items.getInputDate());
        mcAcFundHoldRecPO.setCmpnyBusdate(items.getVoucherDate());
//            mcAcFundHoldRecPO.setAcId(items.getClnt()); //02-0000389-30
        mcAcFundHoldRecPO.setAcId("02-0000389-30");
        mcAcFundHoldRecPO.setSegrFundId(1L);
        mcAcFundHoldRecPO.setCcyCde(PropertyConverUtils.standardizeCurrencyCode(items.getCcy()));
        mcAcFundHoldRecPO.setCmpnyCde("TFS");
//            mcAcFundHoldRecPO.setTxnTypId();
        mcAcFundHoldRecPO.setRefNum("HF"+items.getVoucherNo());
        mcAcFundHoldRecPO.setHoldAmt(items.getAmount());
        mcAcFundHoldRecPO.setExprDate(items.getValueDate());
        mcAcFundHoldRecPO.setFundHoldTyp("ALL");
        mcAcFundHoldRecPO.setFundHoldStat(fundHoldStatConver(items.getStatusFlag()));
        mcAcFundHoldRecPO.setFundChnlCde(Objects.equals(items.getManualInput(), "Yes") ? "MANUALINP" : "SYSTEM");
        mcAcFundHoldRecPO.setExtrnlRefNum(items.getMarket()+items.getVoucherNo());
        mcAcFundHoldRecPO.setRemrk(items.getRemark());
        mcAcFundHoldRecPO.setHoldDate(items.getVoucherDate());
        mcAcFundHoldRecPO.setHoldBy(items.getUserid());
        mcAcFundHoldRecPO.setRelseDate(Objects.equals(accordingStatusConverVal(items.getStatusFlag()), "Y") ? items.getValueDate() : null);
        mcAcFundHoldRecPO.setRelseBy(Objects.equals(accordingStatusConverVal(items.getStatusFlag()), "Y") ? items.getUserid() : null);
        mcAcFundHoldRecPO.setRelseAmt(Objects.equals(accordingStatusConverVal(items.getStatusFlag()), "Y") ? items.getAmount() : BigDecimal.ZERO);
        mcAcFundHoldRecPO.setLastRelseDate(items.getValueDate());
        mcAcFundHoldRecPO.setLastRelseBy(items.getUserid());
        mcAcFundHoldRecPO.setAcFundHoldTxnId(mainId);
        mcAcFundHoldRecPO.setIsInstrRelt("N");
        mcAcFundHoldRecPO.setIsPost(items.getStatusFlag().charAt(8) == 'Y' ? "Y" : "N");
        mcAcFundHoldRecPO.setRecVerNum(0L);
        mcAcFundHoldRecPO.setInitTime(LocalDateTime.now());
        mcAcFundHoldRecPO.setLastUpdTime(LocalDateTime.now());
        mcAcFundHoldRecPO.setLastUpdBy("MIG");
        mcAcFundHoldRecPO.setTagSeq(0L);

        mcAcFundHoldTxnPO.setAcFundHoldTxnId(mainId);
        mcAcFundHoldTxnPO.setFundHoldStat(fundHoldStatConver(items.getStatusFlag()));
        mcAcFundHoldTxnPO.setRecVerNum(0L);
        mcAcFundHoldTxnPO.setInitTime(LocalDateTime.now());
        mcAcFundHoldTxnPO.setLastUpdTime(LocalDateTime.now());
        mcAcFundHoldTxnPO.setLastUpdBy("MIG");
        mcAcFundHoldTxnPO.setTagSeq(0L);

        result.setMainRecord(mcAcFundHoldRecPO);
        result.setDetailRecord(mcAcFundHoldTxnPO);

        return result;
    }

    private String accordingStatusConverVal(String flag) {
        if(StringUtils.isBlank(flag) || flag.length() < 8) return null;
        if(flag.charAt(8) == 'Y'){
            return "Y";
        }
        return "N";
    }


    private String fundHoldStatConver(String statusFlag) {
        if(StringUtils.isBlank(statusFlag) || statusFlag.length() < 7) return null;
        if(statusFlag.charAt(7) == 'Y'){
            return "HOLD";
        }else if(statusFlag.charAt(8) == 'Y'){
            return "RELEASED";
        }
        return "N";
    }
}
