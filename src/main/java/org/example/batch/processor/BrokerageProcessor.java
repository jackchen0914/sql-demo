package org.example.batch.processor;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.GlobalSettingsMapper;
import org.example.mapper.McInstrMapper;
import org.example.mapper.McMrktMapper;
import org.example.pojo.*;
import org.example.pojo.dtos.BrokerageWithRageDTO;
import org.example.pojo.dtos.BrokerageWithRageResultDTO;
import org.example.pojo.dtos.CashTransferAllDTO;
import org.example.pojo.dtos.CashTransferAllResultDTO;
import org.example.service.IdGeneratorService;
import org.example.utils.PropertyConverUtils;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Component
@Slf4j
@RequiredArgsConstructor
public class BrokerageProcessor implements ItemProcessor<BrokerageWithRageDTO, BrokerageWithRageResultDTO> {

    private final McMrktMapper mcMrktMapper;
    private final McInstrMapper mcInstrMapper;
    private final GlobalSettingsMapper globalSettingsMapper;
    private final IdGeneratorService idGeneratorService;

    @Override
    public BrokerageWithRageResultDTO process(BrokerageWithRageDTO items) throws Exception {
        if(items == null){
            return null;
        }

        BrokerageWithRageResultDTO result = new BrokerageWithRageResultDTO();

        Long mainId = idGeneratorService.generateMainId();

        McCommRulePO mcCommRulePO = new McCommRulePO();
        McCommCalcMtdPO mcCommCalcMtdPO = new McCommCalcMtdPO();

        mcCommRulePO.setCommRuleId(idGeneratorService.generateDetailId());
        mcCommRulePO.setCommRuleDescr(items.getMarket()+"-"+"Stock"+findSettingsValueBySource(items.getSource())+
                "-"+items.getPercentRate()+"-"+PropertyConverUtils.standardizeCurrencyCode(items.getCcy())+items.getMinBrokerage());
        mcCommRulePO.setCommFrCde("CLIENT");
        mcCommRulePO.setCommTypCde("COMP_BASE_SCHEME");
//        mcCommRulePO.setMrktId(findMarketId(PropertyConverUtils.marketCodeConver(items.getMarket())));
        mcCommRulePO.setCommInstrTypGrpCde("STOCK");
        mcCommRulePO.setTradCcyCde(PropertyConverUtils.standardizeCurrencyCode(items.getCcy()));
        mcCommRulePO.setIsObsol("N");
        mcCommRulePO.setCmpnyCde("TFS");
        mcCommRulePO.setRecVerNum(0L);
        mcCommRulePO.setInitTime(LocalDateTime.now());
        mcCommRulePO.setLastUpdTime(LocalDateTime.now());
        mcCommRulePO.setLastUpdBy("MIG");
        mcCommRulePO.setTagSeq(0L);

        mcCommCalcMtdPO.setCommCalcMtdId(idGeneratorService.generateDetailId());
        mcCommCalcMtdPO.setCmpnyCde("TFS");
        mcCommCalcMtdPO.setCommCalcMtdDescr(items.getPercentRate()+"-"+PropertyConverUtils.standardizeCurrencyCode(items.getCcy())+items.getMinBrokerage());
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

        Long instrIdByCodeProcess = findInstrIdByCodeProcess("02994");

        result.setMcCommRuleRecord(mcCommRulePO);
        result.setMcCommCalcMtdRecord(mcCommCalcMtdPO);
        return result;
    }

    private Long findInstrIdByCodeProcess(String instrument){
        List<McInstrPO> instrIdByCodeList = mcInstrMapper.findInstrIdByCodeList(instrument);
        if(CollectionUtils.isEmpty(instrIdByCodeList)){
            return null;
        }
        return instrIdByCodeList.get(0).getInstrId();
    }

    private Long findMarketId(String market) {
        McMrktPO instrIdByCode = mcMrktMapper.findInstrIdByCode(market);
        return instrIdByCode.getMrktId();
    }

    private String findSettingsValueBySource(String key){
        GlobalSettingsPO globalSettingsPO = globalSettingsMapper.selectSettingBySource(key);
        if(Objects.nonNull(globalSettingsPO)){
            return globalSettingsPO.getSetting();
        }
        return " ";
    }

}
