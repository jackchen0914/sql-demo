package org.example.batch.processor;

import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.McInstrMapper;
import org.example.mapper.McMrktMapper;
import org.example.pojo.*;
import org.example.pojo.dtos.CaRSCDTO;
import org.example.pojo.dtos.CaRSCResultDTO;
import org.example.service.IdGeneratorService;
import org.example.utils.PropertyConverUtils;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class CaRSCProcessor implements ItemProcessor<CaRSCDTO, CaRSCResultDTO> {

    private final McMrktMapper mcMrktMapper;
    private final IdGeneratorService idGeneratorService;
    private final McInstrMapper mcInstrMapper;

    @Override
    public CaRSCResultDTO process(CaRSCDTO caRSCDTO) throws Exception {
        if(caRSCDTO == null){
            return null;
        }

        CaRSCResultDTO result = new CaRSCResultDTO();

        Long mainId = idGeneratorService.generateMainId();

        List<McCeventPO> mcCeventPOList = new ArrayList<>();

        McCeventPO mcCeventPO = new McCeventPO();
        McConvEvntPO mcConvEvntPO = new McConvEvntPO();

        mcCeventPO.setCeventId(mainId);
        mcCeventPO.setCeventTypGrpCde("CONVERSION_EVENT");
        mcCeventPO.setCeventTypCde(caRSCDTO.getActionTypeList().contains("/RGHT/") ? "RIGHTS_SUBSCRIP" : "SPLIT_CONSOLID");
        mcCeventPO.setEvntNam("Coupon Payment");
        mcCeventPO.setCeventStatCde(PropertyConverUtils.divAnnStatusCovert(caRSCDTO.getStatus()));
        mcCeventPO.setMrktId(findMarketId(PropertyConverUtils.marketCodeConver(caRSCDTO.getMarket())));
        mcCeventPO.setInstrId(findInstrIdByCodeProcess(caRSCDTO.getInstrument()));
        mcCeventPO.setCcassAnnouncementNum(caRSCDTO.getReferenceNo());
        mcCeventPO.setSholdDate(caRSCDTO.getExDividendDate());
        mcCeventPO.setCutoffDate(caRSCDTO.getPayableDate());
        mcCeventPO.setAuthDate(caRSCDTO.getExDividendDate());
        mcCeventPO.setNomineeLetrInd("N");
        mcCeventPO.setIsAlertAftrActn("N");
        mcCeventPO.setIsAlertAftrDeadline("N");
        mcCeventPO.setRemrk(caRSCDTO.getAnnouncementSummary());
        mcCeventPO.setIsSholdCaptured("N");
        mcCeventPO.setIsNotfSent("N");
        mcCeventPO.setIsPendActivate("N");
        mcCeventPO.setCmpltDate(caRSCDTO.getPayableDate());
        mcCeventPO.setActivationDate(caRSCDTO.getPayableDate());
        mcCeventPO.setActCutoffDate(caRSCDTO.getPayableDate());
        mcCeventPO.setCutoffRevCnt(0);
        mcCeventPO.setAuthRevCnt(0);
        mcCeventPO.setExchRateUpdCnt(0);
        mcCeventPO.setCmpnyCde("TFS");
        mcCeventPO.setExcessAlotCnt(0);
        mcCeventPO.setRcalcChrgCnt(0);
        mcCeventPO.setRecVerNum(0L);
        mcCeventPO.setInitTime(LocalDateTime.now());
        mcCeventPO.setLastUpdTime(LocalDateTime.now());
        mcCeventPO.setLastUpdBy("MIG");
        mcCeventPO.setTagSeq(0L);
        mcCeventPO.setIsPrces("N");
        mcCeventPO.setIsSholdAmended("N");
        mcCeventPO.setIsEnotf("N");
        mcCeventPO.setCeventCreatTypCde("MANUAL");
        mcCeventPO.setDefTaxRate(BigDecimal.ONE);
        mcCeventPO.setIsOtcrTxn("N");

        mcConvEvntPO.setCeventId(mainId);
        //mcAcuintTxnPO.setAcuintTxnStatCde("NEW".equals(caRSCDTO.getStatus()) ? "PENDING" : "POST".equals(caRSCDTO.getStatus()) ? "POSTED" : caRSCDTO.getStatus());
        mcConvEvntPO.setNewInstrId(caRSCDTO.getActionTypeList().contains("RGHT") ? findInstrIdByCodeProcess(caRSCDTO.getRightsInstrument()) : caRSCDTO.getActionTypeList().contains("SPLT") ? findInstrIdByCodeProcess(caRSCDTO.getSplitInstrument()) : findInstrIdByCodeProcess(caRSCDTO.getCombineInstrument()));
        mcConvEvntPO.setSharRatioInit(BigDecimal.valueOf(caRSCDTO.getActionTypeList().contains("RGHT") ? caRSCDTO.getRightsIssue() : caRSCDTO.getActionTypeList().contains("SPLT") ? caRSCDTO.getSplitFrom() : caRSCDTO.getCombineFrom()));
        mcConvEvntPO.setSharRatioTo(BigDecimal.valueOf(caRSCDTO.getActionTypeList().contains("RGHT") ? caRSCDTO.getRightsFor() : caRSCDTO.getActionTypeList().contains("SPLT") ? caRSCDTO.getSplitTo() : caRSCDTO.getCombineTo()));
        mcConvEvntPO.setIsCcassStk("N");

        result.setMainRecord(mcConvEvntPO);
        mcCeventPOList.add(mcCeventPO);
        result.setDetailRecord(mcCeventPOList);

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
}

