package org.example.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.example.mapper.*;
import org.example.pojo.*;
import org.example.service.IMcAcHoldStkTxnDtlService;
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
public class McAcHoldStkTxnDtlServiceImpl extends ServiceImpl<McAcHoldStkTxnDtlMapper, McAcHoldStkTxnDtlPO> implements IMcAcHoldStkTxnDtlService {

    private final InstrumentVoucherMapper instrumentVoucherMapper;

    private final McAcHoldStkTxnDtlMapper mcAcHoldStkTxnDtlMapper;

    private final McAcHoldStkTxnMapper mcAcHoldStkTxnMapper;

    private final McInstrMapper mcInstrMapper;

    private final McMrktMapper mcMrktMapper;

    @Override
    public void writeProcessedData() {
        List<InstrumentVoucherPO> instrumentVoucherPOS = instrumentVoucherMapper.selectByPage(1,9);
        List<McAcHoldStkTxnDtlPO> holdStkTxnDtlPOList = new ArrayList<>();
        List<McAcHoldStkTxnPO> holdStkTxnPOList = new ArrayList<>();
        for (int i = 0; i < instrumentVoucherPOS.size(); i++) {
            InstrumentVoucherPO po = instrumentVoucherPOS.get(i);
            McAcHoldStkTxnDtlPO mcAcHoldStkTxnDtlPO = new McAcHoldStkTxnDtlPO();
            McAcHoldStkTxnPO mcAcHoldStkTxnPO = new McAcHoldStkTxnPO();
            mcAcHoldStkTxnPO.setAcHoldStkTxnId((long) (13700000 + i));
            mcAcHoldStkTxnPO.setIsRelse(po.getStatusFlag().charAt(8) == 'Y' ? "Y" : "N");
            mcAcHoldStkTxnPO.setRecVerNum(0L);
            mcAcHoldStkTxnPO.setInitTime(LocalDateTime.now());
            mcAcHoldStkTxnPO.setLastUpdTime(LocalDateTime.now());
            mcAcHoldStkTxnPO.setLastUpdBy("MIG");
            mcAcHoldStkTxnPO.setTagSeq(0L);

            mcAcHoldStkTxnDtlPO.setAcHoldStkTxnDtlId((long) (13800000+i));
            mcAcHoldStkTxnDtlPO.setAcHoldStkTxnId((long) (13700000 + i));
            mcAcHoldStkTxnDtlPO.setTxnRefNum("HE"+po.getVoucherNo());
//            mcAcHoldStkTxnDtlPO.setTxnTypId();
//            mcAcHoldStkTxnDtlPO.setAcId(po.getClnt());
            mcAcHoldStkTxnDtlPO.setAcId("02-0000457-30");
            mcAcHoldStkTxnDtlPO.setCmpnyCde("TFS");
            mcAcHoldStkTxnDtlPO.setInstrId(findInstrIdByCodeProcess(po.getInstrument()));
            mcAcHoldStkTxnDtlPO.setInstclId(findInstrIdByCodeProcess(po.getInstrument()));
            mcAcHoldStkTxnDtlPO.setMrktId(marketCodeConversion(po));
            mcAcHoldStkTxnDtlPO.setMrktBusdate(po.getVoucherDate());
            mcAcHoldStkTxnDtlPO.setHoldStkQty(po.getQuantity());
            mcAcHoldStkTxnDtlPO.setExprDate(po.getValueDate());
            mcAcHoldStkTxnDtlPO.setHoldStkStatCde(holdStkStatCodeConver(po));
            mcAcHoldStkTxnDtlPO.setHoldStkChnlCde(Objects.equals(po.getManualInput(), "Yes") ? "MANUAL" : "SYSTEM");
            mcAcHoldStkTxnDtlPO.setRemrk(po.getRemark());
            mcAcHoldStkTxnDtlPO.setHoldDate(po.getValueDate());
            mcAcHoldStkTxnDtlPO.setHoldBy(po.getUserid());
            mcAcHoldStkTxnDtlPO.setRelseDate(Objects.equals(accordingStatusConverVal(po.getStatusFlag()), "Y") ? po.getValueDate() : null);
            mcAcHoldStkTxnDtlPO.setRelseBy(Objects.equals(accordingStatusConverVal(po.getStatusFlag()), "Y") ? po.getUserid() : null);
            mcAcHoldStkTxnDtlPO.setRelseStkQty(Objects.equals(accordingStatusConverVal(po.getStatusFlag()), "Y") ? po.getQuantity() : BigDecimal.ZERO);
            mcAcHoldStkTxnDtlPO.setSrcSysCde("OctOBO");
            mcAcHoldStkTxnDtlPO.setRecVerNum(0L);
            mcAcHoldStkTxnDtlPO.setInitTime(LocalDateTime.now());
            mcAcHoldStkTxnDtlPO.setLastUpdTime(LocalDateTime.now());
            mcAcHoldStkTxnDtlPO.setLastUpdBy("MIG");
            mcAcHoldStkTxnDtlPO.setTagSeq(0L);

            holdStkTxnPOList.add(mcAcHoldStkTxnPO);
            holdStkTxnDtlPOList.add(mcAcHoldStkTxnDtlPO);
        }
        saveToOracleMcAcHoldStkTxn(holdStkTxnPOList);
        saveToOracleMcAcHoldStkTxnDtl(holdStkTxnDtlPOList);
    }

    private Long findInstrIdByCodeProcess(String instrument) {
        List<McInstrPO> instrIdByCodeList = mcInstrMapper.findInstrIdByCodeList(instrument);
        if (CollectionUtils.isEmpty(instrIdByCodeList)) {
            return null;
        }
        return instrIdByCodeList.get(0).getInstrId();
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
        if(po.getMarket().equals("AUS")){
            code = "AUS";
        } else if (po.getMarket().equals("BOD") || po.getMarket().equals("BON")||
                po.getMarket().equals("DEO")|| po.getMarket().equals("DEV")|| po.getMarket().equals("FUN")||po.getMarket().equals("MMF")) {
            code = "OTC";
        }else if (po.getMarket().equals("CAN")) {
            code = "TSX";
        }else if (po.getMarket().equals("CHA")) {
            code = "SZSEA";
        }else if (po.getMarket().equals("CHE")) {
            code = "SIX";
        }else if (po.getMarket().equals("DEU")) {
            code = "FSE";
        }else if (po.getMarket().equals("FRA")) {
            code = "PARIS";
        }else if (po.getMarket().equals("HKG")) {
            code = "HKEX";
        }else if (po.getMarket().equals("JPN")) {
            code = "OSE";
        }else if (po.getMarket().equals("KOR")) {
            code = "KSE";
        }else if (po.getMarket().equals("MAL")) {
            code = "KLSE";
        }else if (po.getMarket().equals("SHA")) {
            code = "HKSSE";
        }else if (po.getMarket().equals("SHB")) {
            code = "SSE";
        }else if (po.getMarket().equals("SIN")) {
            code = "SGX";
        }else if (po.getMarket().equals("SZA")) {
            code = "HKSZSE";
        }else if (po.getMarket().equals("SZB")) {
            code = "SZSE";
        }else if (po.getMarket().equals("TWN")) {
            code = "TSEC";
        }else if (po.getMarket().equals("UKG")) {
            code = "LSE";
        }else if (po.getMarket().equals("USA")) {
            code = "NYSEMKT";
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

    @DS("oracle")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveToOracleMcAcHoldStkTxnDtl(List<McAcHoldStkTxnDtlPO> list){
        mcAcHoldStkTxnDtlMapper.insert(list);
    }

    @DS("oracle")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveToOracleMcAcHoldStkTxn(List<McAcHoldStkTxnPO> list){
        mcAcHoldStkTxnMapper.insert(list);
    }

}
