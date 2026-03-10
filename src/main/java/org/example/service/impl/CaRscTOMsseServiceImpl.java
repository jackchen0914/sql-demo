package org.example.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.*;
import org.example.pojo.*;
import org.example.pojo.dtos.CaRSCDTO;
import org.example.service.CaRscTOMsseService;
import org.example.service.IdGeneratorService;
import org.example.utils.PropertyConverUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.swing.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JackChen
 * @since 2026-01-07
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CaRscTOMsseServiceImpl implements CaRscTOMsseService {

    private final McMrktMapper mcMrktMapper;
    private final McInstrMapper mcInstrMapper;
    private final McCeventMapper mcCeventMapper;
    private final McConvEvntMapper mcConvEvntMapper;
    private final McCeventOptnMapper mcCeventOptnMapper;

    private final DivAnnMapper divAnnMapper;
    private final DivAnnRightsMapMSSEMapper divAnnRightsMapMSSEMapper;
    private final DivAnnSplitMapMSSEMapper divAnnSplitMapMSSEMapper;
    private final DivAnnCombineMapMSSEMapper divAnnCombineMapMSSEMapper;
    private final DivAnnMapMSSEMapper divAnnMapMSSEMapper;

    private final IdGeneratorService idGeneratorService;

    @Override
    public String writeProcessedData() {
        List<CaRSCDTO> divAnnPOS = divAnnMapper.selectDivAnnWithRight(1, 10);
        List<McCeventPO> mcCeventPOList = new ArrayList<>();
        List<McConvEvntPO> mcConvEvntPOList = new ArrayList<>();
        List<McCeventOptnPO> mcCeventOptnPOList = new ArrayList<>();
        List<DivAnnRightsMapMSSEPO> divAnnRightsMapMSSEPOList = new ArrayList<>();
        List<DivAnnSplitMapMSSEPO> divAnnSplitMapMSSEPOList = new ArrayList<>();
        List<DivAnnCombineMapMSSEPO> divAnnCombineMapMSSEPOList = new ArrayList<>();
        List<DivAnnMapMSSEPO> divAnnMapMSSEPOList = new ArrayList<>();
        for (int i = 0; i < divAnnPOS.size(); i++) {
            CaRSCDTO po = divAnnPOS.get(i);
            Long mainId = idGeneratorService.generateMainId();


            McCeventPO mcCeventPO = new McCeventPO();
            McConvEvntPO mcConvEvntPO = new McConvEvntPO();
            McCeventOptnPO mcCeventOptnPO = new McCeventOptnPO();
            Long mcCeventMrktId = findMarketId(PropertyConverUtils.marketCodeConver(po.getMarket()));
            Long mcCeventInstrId = findInstrIdByCodeProcess(po.getInstrument());
            Long mcConvEvntNewInstrId = po.getActionTypeList().contains("RGHT") ? findInstrIdByCodeProcess(po.getRightsInstrument()) : po.getActionTypeList().contains("SPLT") ? findInstrIdByCodeProcess(po.getSplitInstrument()) : findInstrIdByCodeProcess(po.getCombineInstrument());

            mcCeventPO.setCeventId(mainId);
            mcCeventPO.setCeventTypGrpCde("CONVERSION_EVENT");
            mcCeventPO.setCeventTypCde(po.getActionTypeList().contains("/RGHT/") ? "RIGHTS_SUBSCRIP" : "SPLIT_CONSOLID");
            mcCeventPO.setEvntNam("Coupon Payment");
            mcCeventPO.setCeventStatCde(PropertyConverUtils.divAnnStatusCovert(po.getStatus()));
            mcCeventPO.setMrktId(mcCeventMrktId);
            mcCeventPO.setInstrId(mcCeventInstrId);
            mcCeventPO.setCcassAnnouncementNum(po.getReferenceNo());
            mcCeventPO.setSholdDate(po.getExDividendDate());
            mcCeventPO.setCutoffDate(po.getPayableDate());
            mcCeventPO.setAuthDate(po.getExDividendDate());
            mcCeventPO.setNomineeLetrInd("N");
            mcCeventPO.setIsAlertAftrActn("N");
            mcCeventPO.setIsAlertAftrDeadline("N");
            mcCeventPO.setRemrk(po.getAnnouncementSummary());
            mcCeventPO.setIsSholdCaptured("N");
            mcCeventPO.setIsNotfSent("N");
            mcCeventPO.setIsPendActivate("N");
            mcCeventPO.setCmpltDate(po.getPayableDate());
            mcCeventPO.setActivationDate(po.getPayableDate());
            mcCeventPO.setActCutoffDate(po.getPayableDate());
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
            //mcAcuintTxnPO.setAcuintTxnStatCde("NEW".equals(po.getStatus()) ? "PENDING" : "POST".equals(po.getStatus()) ? "POSTED" : po.getStatus());
            mcConvEvntPO.setNewInstrId(mcConvEvntNewInstrId);
            mcConvEvntPO.setSharRatioInit(BigDecimal.valueOf(po.getActionTypeList().contains("RGHT") ? po.getRightsIssue() : po.getActionTypeList().contains("SPLT") ? po.getSplitFrom() : po.getCombineFrom()));
            mcConvEvntPO.setSharRatioTo(BigDecimal.valueOf(po.getActionTypeList().contains("RGHT") ? po.getRightsFor() : po.getActionTypeList().contains("SPLT") ? po.getSplitTo() : po.getCombineTo()));
            mcConvEvntPO.setIsCcassStk("N");

            mcCeventOptnPO.setCeventOptnId(idGeneratorService.generateDetailId());
            mcCeventOptnPO.setCeventId(mainId);
            mcCeventOptnPO.setOptnNum(0);
            mcCeventOptnPO.setCeventPaidStatCde(divAnnStatusCovert(po.getStatus()));
            mcCeventOptnPO.setExptPayDate(po.getPayableDate());
            mcCeventOptnPO.setActPaidDate(po.getPayableDate());
            mcCeventOptnPO.setExSubPaidStatCde(divAnnStatusCovert(po.getStatus()));
            mcCeventOptnPO.setIsDisabled("N");
            mcCeventOptnPO.setRecVerNum(0L);
            mcCeventOptnPO.setInitTime(LocalDateTime.now());
            mcCeventOptnPO.setLastUpdTime(LocalDateTime.now());
            mcCeventOptnPO.setLastUpdBy("MIG");
            mcCeventOptnPO.setTagSeq(0L);

            if (po.getActionTypeList().contains("RGHT")) {
                DivAnnRightsMapMSSEPO divAnnRightsMapMSSEPO = new DivAnnRightsMapMSSEPO();
                divAnnRightsMapMSSEPO.setMarket(po.getMarket());
                divAnnRightsMapMSSEPO.setCcy(po.getCcy());
                divAnnRightsMapMSSEPO.setAnnouncementNo(po.getAnnouncementNo());
                divAnnRightsMapMSSEPO.setInstrument(po.getInstrument());
                divAnnRightsMapMSSEPO.setActionTypeList(po.getActionTypeList());
                divAnnRightsMapMSSEPO.setReferenceNo(po.getReferenceNo());

                divAnnRightsMapMSSEPO.setRightsFor(BigDecimal.valueOf(po.getRightsFor()));
                divAnnRightsMapMSSEPO.setRightsIssue(BigDecimal.valueOf(po.getRightsIssue()));
                divAnnRightsMapMSSEPO.setRightsInstrument(po.getRightsInstrument());
                divAnnRightsMapMSSEPO.setCeventId(mainId);
                divAnnRightsMapMSSEPO.setMrktId(mcCeventMrktId);
                divAnnRightsMapMSSEPO.setInstrId(mcCeventInstrId);
                divAnnRightsMapMSSEPO.setCcassAnnouncementNum(po.getReferenceNo());
                divAnnRightsMapMSSEPO.setCeventTypGrpCde("CONVERSION_EVENT");
                divAnnRightsMapMSSEPO.setSharRatioInit(new BigDecimal(po.getRightsIssue()));
                divAnnRightsMapMSSEPO.setSharRatioTo(BigDecimal.valueOf(po.getRightsFor()));
                divAnnRightsMapMSSEPO.setNewInstrId(mcConvEvntNewInstrId);
                divAnnRightsMapMSSEPOList.add(divAnnRightsMapMSSEPO);
                divAnnMapMSSEPOList.add(divAnnMapMSSEBuilder(po,mainId,mcCeventMrktId,mcCeventInstrId));
            }

            if (po.getActionTypeList().contains("SPLT")) {
                DivAnnSplitMapMSSEPO divAnnRightsMapMSSEPO = new DivAnnSplitMapMSSEPO();
                divAnnRightsMapMSSEPO.setMarket(po.getMarket());
                divAnnRightsMapMSSEPO.setCcy(po.getCcy());
                divAnnRightsMapMSSEPO.setAnnouncementNo(po.getAnnouncementNo());
                divAnnRightsMapMSSEPO.setInstrument(po.getInstrument());
                divAnnRightsMapMSSEPO.setActionTypeList(po.getActionTypeList());
                divAnnRightsMapMSSEPO.setReferenceNo(po.getReferenceNo());

                divAnnRightsMapMSSEPO.setSplitFrom(BigDecimal.valueOf(po.getSplitFrom()));
                divAnnRightsMapMSSEPO.setSplitTo(BigDecimal.valueOf(po.getSplitTo()));
                divAnnRightsMapMSSEPO.setSplitInstrument(po.getSplitInstrument());
                divAnnRightsMapMSSEPO.setCeventId(mainId);
                divAnnRightsMapMSSEPO.setMrktId(mcCeventMrktId);
                divAnnRightsMapMSSEPO.setInstrId(mcCeventInstrId);
                divAnnRightsMapMSSEPO.setCcassAnnouncementNum(po.getReferenceNo());
                divAnnRightsMapMSSEPO.setCeventTypGrpCde("CONVERSION_EVENT");
                divAnnRightsMapMSSEPO.setSharRatioInit(new BigDecimal(po.getSplitFrom()));
                divAnnRightsMapMSSEPO.setSharRatioTo(BigDecimal.valueOf(po.getSplitTo()));
                divAnnRightsMapMSSEPO.setNewInstrId(mcConvEvntNewInstrId);
                divAnnSplitMapMSSEPOList.add(divAnnRightsMapMSSEPO);
                divAnnMapMSSEPOList.add(divAnnMapMSSEBuilder(po,mainId,mcCeventMrktId,mcCeventInstrId));
            }

            if (po.getActionTypeList().contains("CNSD")) {
                DivAnnCombineMapMSSEPO divAnnRightsMapMSSEPO = new DivAnnCombineMapMSSEPO();
                divAnnRightsMapMSSEPO.setMarket(po.getMarket());
                divAnnRightsMapMSSEPO.setCcy(po.getCcy());
                divAnnRightsMapMSSEPO.setAnnouncementNo(po.getAnnouncementNo());
                divAnnRightsMapMSSEPO.setInstrument(po.getInstrument());
                divAnnRightsMapMSSEPO.setActionTypeList(po.getActionTypeList());
                divAnnRightsMapMSSEPO.setReferenceNo(po.getReferenceNo());

                divAnnRightsMapMSSEPO.setCombineFrom(BigDecimal.valueOf(po.getCombineFrom()));
                divAnnRightsMapMSSEPO.setCombineTo(BigDecimal.valueOf(po.getCombineTo()));
                divAnnRightsMapMSSEPO.setCombineInstrument(po.getCombineInstrument());
                divAnnRightsMapMSSEPO.setCeventId(mainId);
                divAnnRightsMapMSSEPO.setMrktId(mcCeventMrktId);
                divAnnRightsMapMSSEPO.setInstrId(mcCeventInstrId);
                divAnnRightsMapMSSEPO.setCcassAnnouncementNum(po.getReferenceNo());
                divAnnRightsMapMSSEPO.setCeventTypGrpCde("CONVERSION_EVENT");
                divAnnRightsMapMSSEPO.setSharRatioInit(new BigDecimal(po.getCombineFrom()));
                divAnnRightsMapMSSEPO.setSharRatioTo(BigDecimal.valueOf(po.getCombineTo()));
                divAnnRightsMapMSSEPO.setNewInstrId(mcConvEvntNewInstrId);
                divAnnCombineMapMSSEPOList.add(divAnnRightsMapMSSEPO);
                divAnnMapMSSEPOList.add(divAnnMapMSSEBuilder(po,mainId,mcCeventMrktId,mcCeventInstrId));
            }

            mcCeventPOList.add(mcCeventPO);
            mcConvEvntPOList.add(mcConvEvntPO);
            mcCeventOptnPOList.add(mcCeventOptnPO);
        }
        divAnnRightsMapMSSEMapper.insert(divAnnRightsMapMSSEPOList);
        divAnnSplitMapMSSEMapper.insert(divAnnSplitMapMSSEPOList);
        divAnnCombineMapMSSEMapper.insert(divAnnCombineMapMSSEPOList);
        divAnnMapMSSEMapper.insert(divAnnMapMSSEPOList);
        saveToOracleMcCevent(mcCeventPOList);
        saveToOracleMcConEvnt(mcConvEvntPOList);
        saveToOracleMcCeventOptn(mcCeventOptnPOList);
        return "ok";
    }

    public DivAnnMapMSSEPO divAnnMapMSSEBuilder(CaRSCDTO po,Long ceventId,Long mrktId,Long instrId){
            DivAnnMapMSSEPO divAnnMapMSSEPO = new DivAnnMapMSSEPO();
            divAnnMapMSSEPO.setAnnouncementNo(po.getAnnouncementNo());
            divAnnMapMSSEPO.setMarket(po.getMarket());
            divAnnMapMSSEPO.setCcy(po.getCcy());
            divAnnMapMSSEPO.setInstrument(po.getInstrument());
            divAnnMapMSSEPO.setReferenceNo(po.getReferenceNo());
            divAnnMapMSSEPO.setActionTypeList(po.getActionTypeList());
            divAnnMapMSSEPO.setCeventTypGrpCde("CONVERSION_EVENT");
            divAnnMapMSSEPO.setCcassAnnouncementNum(po.getReferenceNo());
            divAnnMapMSSEPO.setCeventId(ceventId);
            divAnnMapMSSEPO.setMrktId(mrktId);
            divAnnMapMSSEPO.setInstrId(instrId);
        return divAnnMapMSSEPO;
    }

    public String divAnnStatusCovert(String status){
        if("NEW".equals(status) || "PEND".equals(status)){
            return "PENDING";
        }else if("FRZ".equals(status)){
            return "SUBMITTED";
        }else if("APP".equals(status)){
            return "PAID";
        }
        return status;
    }

    @DS("oracle")
    @Transactional(rollbackFor = Exception.class)
    public void saveToOracleMcCeventOptn(List<McCeventOptnPO> list) {
        mcCeventOptnMapper.batchInsert(list);
    }

    @DS("oracle")
    @Transactional(rollbackFor = Exception.class)
    public void saveToOracleMcCevent(List<McCeventPO> list) {
        mcCeventMapper.batchInsert(list);
    }

    @DS("oracle")
    @Transactional(rollbackFor = Exception.class)
    public void saveToOracleMcConEvnt(List<McConvEvntPO> list) {
        mcConvEvntMapper.batchInsert(list);
    }

    private Long findInstrIdByCodeProcess(String instrument) {
        List<McInstrPO> instrIdByCodeList = mcInstrMapper.findInstrIdByCodeList(instrument);
        if (CollectionUtils.isEmpty(instrIdByCodeList)) {
            return null;
        }
        return instrIdByCodeList.get(0).getInstrId();
    }

    private Long findMarketId(String market) {
        McMrktPO instrIdByCode = mcMrktMapper.findInstrIdByCode(market);
        return instrIdByCode.getMrktId();
    }
}
