package org.example.batch.processor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.pojo.*;
import org.example.pojo.dtos.BrokerageWithRageDTO;
import org.example.pojo.dtos.BrokerageWithRageResultDTO;
import org.example.service.IdGeneratorService;
import org.example.service.OracleQueryService;
import org.example.utils.PropertyConverUtils;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
@Slf4j
@RequiredArgsConstructor
public class BrokerageWithRageProcessor implements ItemProcessor<BrokerageWithRageDTO, BrokerageWithRageResultDTO> {

    private final IdGeneratorService idGeneratorService;
    private final OracleQueryService oracleQueryService;

    @Override
    public BrokerageWithRageResultDTO process(BrokerageWithRageDTO items) throws Exception {
        if (items == null) {
            return null;
        }

        BrokerageWithRageResultDTO result = new BrokerageWithRageResultDTO();

        Long mainId = idGeneratorService.generateMainId();

        Long marketId = oracleQueryService.findMarketId(PropertyConverUtils.marketCodeConver(items.getMarket()));
        String settingsValue = items.getSettingsValue() != null ? items.getSettingsValue() : " ";


        McCommRulePO mcCommRulePO = new McCommRulePO();
        McCommCalcMtdPO mcCommCalcMtdPO = new McCommCalcMtdPO();

        mcCommRulePO.setCommRuleId(idGeneratorService.generateDetailId());
        mcCommRulePO.setCommRuleDescr(items.getMarket() + "-" + "Stock" + settingsValue +
                "-" + items.getPercentRate() + "-" + PropertyConverUtils.standardizeCurrencyCode(items.getCcy()) + items.getMinBrokerage());
        mcCommRulePO.setCommFrCde("CLIENT");
        mcCommRulePO.setCommTypCde("COMP_BASE_SCHEME");
        mcCommRulePO.setMrktId(marketId);
        mcCommRulePO.setCommInstrTypGrpCde("STOCK");
        mcCommRulePO.setTradCcyCde(PropertyConverUtils.standardizeCurrencyCode(items.getCcy()));
        mcCommRulePO.setIsObsol("N");
        mcCommRulePO.setCmpnyCde("TFS");
        mcCommRulePO.setRecVerNum(0L);
        mcCommRulePO.setInitTime(LocalDateTime.now());
        mcCommRulePO.setLastUpdTime(LocalDateTime.now());
        mcCommRulePO.setLastUpdBy("MIG");
        mcCommRulePO.setTagSeq(0L);

        mcCommCalcMtdPO.setCommCalcMtdId(mainId);
        mcCommCalcMtdPO.setCmpnyCde("TFS");
        mcCommCalcMtdPO.setCommCalcMtdDescr(items.getPercentRate() + "-" + PropertyConverUtils.standardizeCurrencyCode(items.getCcy()) + items.getMinBrokerage());
        mcCommCalcMtdPO.setCommFrCde("CLIENT");
        mcCommCalcMtdPO.setCommTypCde("COMP_BASE_SCHEME");
        mcCommCalcMtdPO.setCommBaseValCde("CONSIDERATION");
        mcCommCalcMtdPO.setCommCalcTypCde(Objects.equals(items.getType(), "S") ? "SLIDING" : "FIXED");
        mcCommCalcMtdPO.setPayCcyCde(PropertyConverUtils.standardizeCurrencyCode(items.getCcy()));
        mcCommCalcMtdPO.setIsObsol("N");
        mcCommCalcMtdPO.setMinCommAmt(items.getMinBrokerage());
        mcCommCalcMtdPO.setMaxCommAmt(items.getMaxBrokerage());
        mcCommCalcMtdPO.setFixRate(items.getPercentRate());
        mcCommCalcMtdPO.setAddtnFixAmt(items.getAdditionalAmount());
        mcCommCalcMtdPO.setIsAdvanceSetg("N");
        mcCommCalcMtdPO.setIsAeRbCmpnyBslnPrtct("N");
        mcCommCalcMtdPO.setVerStat("EFFECTIVE");
        mcCommCalcMtdPO.setRecVerNum(0L);
        mcCommCalcMtdPO.setInitTime(LocalDateTime.now());
        mcCommCalcMtdPO.setLastUpdTime(LocalDateTime.now());
        mcCommCalcMtdPO.setLastUpdBy("MIG");
        mcCommCalcMtdPO.setTagSeq(0L);

        result.setMcCommRuleRecord(mcCommRulePO);
        result.setMcCommCalcMtdRecord(mcCommCalcMtdPO);
        return result;
    }
}

