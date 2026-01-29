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

        result.setMainRecord(mcAcOnlineFxReqPO);

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
