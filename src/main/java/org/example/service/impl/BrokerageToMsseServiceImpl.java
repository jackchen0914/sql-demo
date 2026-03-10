package org.example.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.*;
import org.example.pojo.*;
import org.example.pojo.dtos.BrokerageWithRageDTO;
import org.example.service.BrokerageToMsseService;
import org.example.service.IdGeneratorService;
import org.example.utils.PropertyConverUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class BrokerageToMsseServiceImpl implements BrokerageToMsseService {

    private final BrokerageMapper brokerageMapper;
    private final GlobalSettingsMapper globalSettingsMapper;

    private final McMrktMapper mcMrktMapper;
    private final McInstrMapper mcInstrMapper;
    private final McCommRuleMapper mcCommRuleMapper;
    private final McCommCalcMtdMapper mcCommCalcMtdMapper;

    private final IdGeneratorService idGeneratorService;

    @Override
    public String writeProcessedData() {
        List<BrokerageWithRageDTO> brokerageWithRageDTOS = brokerageMapper.selectBrokerageAll(1, 10);
        List<McCommRulePO> mcCommRulePOList = new ArrayList<>();
        List<McCommCalcMtdPO> mcCommCalcMtdPOList = new ArrayList<>();
        for (int i = 0; i < brokerageWithRageDTOS.size(); i++) {
            BrokerageWithRageDTO items = brokerageWithRageDTOS.get(i);

            Long mainId = idGeneratorService.generateMainId();

            Long marketId = findMarketId(PropertyConverUtils.marketCodeConver(items.getMarket()));
            String settingsValue = findSettingsValueBySource(items.getSource());


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

            mcCommRulePOList.add(mcCommRulePO);
            mcCommCalcMtdPOList.add(mcCommCalcMtdPO);
        }
        savaToOracleMcCommRule(mcCommRulePOList);
        savaToOracleMcCommCalcMtd(mcCommCalcMtdPOList);
        return "ok";
    }

    @DS("oracle")
    @Transactional(rollbackFor = Exception.class)
    private void savaToOracleMcCommCalcMtd(List<McCommCalcMtdPO> mcCommCalcMtdPOList) {
        mcCommCalcMtdMapper.batchInsert(mcCommCalcMtdPOList);
    }

    @DS("oracle")
    @Transactional(rollbackFor = Exception.class)
    private void savaToOracleMcCommRule(List<McCommRulePO> mcCommRulePOList) {
        mcCommRuleMapper.batchInsert(mcCommRulePOList);
    }

    private String findSettingsValueBySource(String source) {
//        if(source.isBlank()){return " ";}
        GlobalSettingsPO settings = globalSettingsMapper.selectSettingBySource(source);
        if (settings != null) {
            return settings.getSetting();
        } else {
            return " ";
        }
    }

    private Long findMarketId(String market) {
        if (market.isBlank()) {
            return null;
        }
        McMrktPO instrIdByCode = mcMrktMapper.findInstrIdByCode(market);
        return instrIdByCode.getMrktId() != null ? instrIdByCode.getMrktId() : null;
    }
}
