package org.example.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.CashTransferMapper;
import org.example.mapper.McAcOnlineFxReqMapper;
import org.example.pojo.*;
import org.example.service.IMcAcOnlineFxReqService;
import org.example.utils.PropertyConverUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JackChen
 * @since 2025-12-25
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class McAcOnlineFxReqServiceImpl extends ServiceImpl<McAcOnlineFxReqMapper, McAcOnlineFxReqPO> implements IMcAcOnlineFxReqService {

    private final McAcOnlineFxReqMapper mcAcOnlineFxReqMapper;

    private final CashTransferMapper cashTransferMapper;

    @Override
    public void writeProcessedData() {
        List<CashTransferPO> instrumentVoucherPOS = cashTransferMapper.selectTop5();
        List<McAcOnlineFxReqPO> cashTransferPOList = new ArrayList<>();
        for (int i = 0; i < instrumentVoucherPOS.size(); i++) {
            CashTransferPO po = instrumentVoucherPOS.get(i);
            McAcOnlineFxReqPO mcAcOnlineFxReqPO = new McAcOnlineFxReqPO();
            mcAcOnlineFxReqPO.setAcOnlineFxReqId((long) i + 1 );
            mcAcOnlineFxReqPO.setCmpnyCde("TFS");
            mcAcOnlineFxReqPO.setCmpnyIbusdate(po.getTransferDate());
            mcAcOnlineFxReqPO.setCmpnyBusdate(po.getTransferDate());
//            mcAcOnlineFxReqPO.setFromAcId(po.getClntCode());
            mcAcOnlineFxReqPO.setFromAcId("02-0000457-30");
            mcAcOnlineFxReqPO.setFromSegrFundId(1L);
            mcAcOnlineFxReqPO.setFromCcyCde(PropertyConverUtils.standardizeCurrencyCode(po.getCCYFrom()));
            mcAcOnlineFxReqPO.setFromAmt(po.getAmountFrom());
//            mcAcOnlineFxReqPO.setToAcId(po.getClntCode());
            mcAcOnlineFxReqPO.setToAcId("02-0000457-30");
            mcAcOnlineFxReqPO.setToSegrFundId(1L);
            mcAcOnlineFxReqPO.setToCcyCde(PropertyConverUtils.standardizeCurrencyCode(po.getCCYTo()));
            mcAcOnlineFxReqPO.setToAmt(po.getAmountTo());
            mcAcOnlineFxReqPO.setValDate(po.getTransferDate());
            mcAcOnlineFxReqPO.setExchRate(po.getFXrate());
            mcAcOnlineFxReqPO.setAprvCmpnyIbusdate(po.getTransferDate());
            mcAcOnlineFxReqPO.setAprvCmpnyBusdate(po.getTransferDate());
            mcAcOnlineFxReqPO.setAprvTime(po.getTransferDate());
            mcAcOnlineFxReqPO.setFxStatCde(fxStatCodeConver(po.getStatus()));
            mcAcOnlineFxReqPO.setRecVerNum(0L);
            mcAcOnlineFxReqPO.setInitTime(LocalDateTime.now());
            mcAcOnlineFxReqPO.setLastUpdTime(LocalDateTime.now());
            mcAcOnlineFxReqPO.setLastUpdBy("MIG");
            mcAcOnlineFxReqPO.setTagSeq(0L);
            cashTransferPOList.add(mcAcOnlineFxReqPO);
        }
        saveToOracle(cashTransferPOList);
    }

    @DS("oracle")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveToOracle(List<McAcOnlineFxReqPO> list){
        mcAcOnlineFxReqMapper.insert(list);
    }

    private static String fxStatCodeConver(String status) {
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
