package org.example.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.*;
import org.example.pojo.*;
import org.example.pojo.dtos.CaRSCDTO;
import org.example.pojo.dtos.DivAnnWithRightDTO;
import org.example.service.IMcCeventService;
import org.example.utils.PropertyConverUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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
public class McCeventServiceImpl extends ServiceImpl<McCeventMapper, McCeventPO> implements IMcCeventService {

    private final DivAnnMapper divAnnMapper;

    private final McMrktMapper mcMrktMapper;

    private final McInstrMapper mcInstrMapper;

    private final McCeventMapper mcCeventMapper;

    private final McConvEvntMapper mcConvEvntMapper;

    @Override
    public void writeProcessedData() {
        List<CaRSCDTO> divAnnPOS = divAnnMapper.selectDivAnnWithRight(1,9);
        List<McCeventPO> mcCeventPOList = new ArrayList<>();
        List<McConvEvntPO> mcConvEvntPOList = new ArrayList<>();
        for (int i = 0; i < divAnnPOS.size(); i++) {
            CaRSCDTO po = divAnnPOS.get(i);
            McCeventPO mcCeventPO = new McCeventPO();
            McConvEvntPO mcConvEvntPO = new McConvEvntPO();

            mcCeventPO.setCeventId((long) (4040952 + i));
            mcCeventPO.setCeventTypGrpCde("CONVERSION_EVENT");
            mcCeventPO.setCeventTypCde(po.getActionTypeList().contains("/RGHT/") ? "RIGHTS_SUBSCRIP" : "SPLIT_CONSOLID");
            mcCeventPO.setEvntNam("Coupon Payment");
            mcCeventPO.setCeventStatCde(PropertyConverUtils.divAnnStatusCovert(po.getStatus()));
            mcCeventPO.setMrktId(findMarketId(PropertyConverUtils.marketCodeConver(po.getMarket())));
            mcCeventPO.setInstrId(findInstrIdByCodeProcess(po.getInstrument()));
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

            mcConvEvntPO.setCeventId((long) (4040952 + i));
            //mcAcuintTxnPO.setAcuintTxnStatCde("NEW".equals(po.getStatus()) ? "PENDING" : "POST".equals(po.getStatus()) ? "POSTED" : po.getStatus());
            mcConvEvntPO.setNewInstrId(po.getActionTypeList().contains("RGHT") ? findInstrIdByCodeProcess(po.getRightsInstrument()) : po.getActionTypeList().contains("SPLT") ? findInstrIdByCodeProcess(po.getSplitInstrument()) : findInstrIdByCodeProcess(po.getCombineInstrument()));
            mcConvEvntPO.setSharRatioInit(BigDecimal.valueOf(po.getActionTypeList().contains("RGHT") ? po.getRightsIssue() : po.getActionTypeList().contains("SPLT") ? po.getSplitFrom() : po.getCombineFrom()));
            mcConvEvntPO.setSharRatioTo(BigDecimal.valueOf(po.getActionTypeList().contains("RGHT") ? po.getRightsFor() : po.getActionTypeList().contains("SPLT") ? po.getSplitTo() : po.getCombineTo()));
            mcConvEvntPO.setIsCcassStk("N");
            mcCeventPOList.add(mcCeventPO);
            mcConvEvntPOList.add(mcConvEvntPO);
        }
        saveToOracleMcCevent(mcCeventPOList);
        saveToOracleMcConEvnt(mcConvEvntPOList);
    }

    @DS("oracle")
    @Transactional(rollbackFor = Exception.class)
    public void saveToOracleMcCevent(List<McCeventPO> list){
        mcCeventMapper.insert(list);
    }

    @DS("oracle")
    @Transactional(rollbackFor = Exception.class)
    public void saveToOracleMcConEvnt(List<McConvEvntPO> list){
        mcConvEvntMapper.insert(list);
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
