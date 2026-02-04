package org.example.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.InstrumentVoucherMapper;
import org.example.mapper.McStkTxnMapper;
import org.example.pojo.InstrumentVoucherPO;
import org.example.pojo.McCmpnyBankAcPO;
import org.example.pojo.McStkTxnPO;
import org.example.service.IMcStkTxnService;
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
 * @since 2025-12-19
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class McStkTxnServiceImpl extends ServiceImpl<McStkTxnMapper, McStkTxnPO> implements IMcStkTxnService {


    private final InstrumentVoucherMapper instrumentVoucherMapper;

    private final McStkTxnMapper mcStkTxnMapper;


    @Override
    public void writeProcessedData() {
        List<InstrumentVoucherPO> instrumentVoucherPOS = instrumentVoucherMapper.selectStatusFlagEqualYByPage(1,9);
        List<McStkTxnPO> dataList = new ArrayList<>();
        for (int i = 0; i < instrumentVoucherPOS.size(); i++) {
            InstrumentVoucherPO po = instrumentVoucherPOS.get(i);
            McStkTxnPO mcStkTxnPO = new McStkTxnPO();
            mcStkTxnPO.setStkTxnId((long) (7400164 + i));
            mcStkTxnPO.setStkAprvStatCde("APPROVED");
            mcStkTxnPO.setIsRev("N");
            mcStkTxnPO.setIsAwaiting("N");
            mcStkTxnPO.setRecVerNum(0L);
            mcStkTxnPO.setInitTime(LocalDateTime.now());
            mcStkTxnPO.setLastUpdTime(LocalDateTime.now());
            mcStkTxnPO.setLastUpdBy("MIG");
            mcStkTxnPO.setTagSeq(0L);
            dataList.add(mcStkTxnPO);
        }
        saveToOracle(dataList);
    }

    @DS("oracle")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveToOracle(List<McStkTxnPO> list){
        mcStkTxnMapper.insert(list);
    }
}
