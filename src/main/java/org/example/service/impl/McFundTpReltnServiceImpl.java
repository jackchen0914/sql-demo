package org.example.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.*;
import org.example.pojo.CashVoucherRequestPO;
import org.example.pojo.McAcFundTxnRecPO;
import org.example.pojo.McCmpnyBankAcPO;
import org.example.pojo.McFundTpReltnPO;
import org.example.service.DatabaseManipulationService;
import org.example.service.IMcAcFundTxnRecService;
import org.example.service.IMcFundTpReltnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类 CashVoucher
 * </p>
 *
 * @author JackChen
 * @since 2025-12-12
 */
@Service
@Slf4j
@RequiredArgsConstructor
//public class McFundTpReltnServiceImpl implements DatabaseManipulationService {
public class McFundTpReltnServiceImpl extends ServiceImpl<McFundTpReltnMapper, McFundTpReltnPO> implements IMcFundTpReltnService {

    private final CashVoucherMapper cashVoucherMapper;

    private final CashVoucherRequestMapper cashVoucherRequestMapper;

    private final McFundTpReltnMapper mcFundTpReltnMapper;

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void writeProcessedData() {
        List<CashVoucherRequestPO> cashVoucherRequestPO = cashVoucherRequestMapper.selectTop5();
        List<McFundTpReltnPO> dataList = new ArrayList<>();
        for (int i = 0; i < cashVoucherRequestPO.size(); i++) {
            CashVoucherRequestPO po = cashVoucherRequestPO.get(i);
            McFundTpReltnPO mcFundTpReltnPO = new McFundTpReltnPO();
            mcFundTpReltnPO.setFundTpReltnCde(po.getOtherRelationship() + i);
            mcFundTpReltnPO.setFundTpReltnDscr(po.getOtherRelationship() + i);
            mcFundTpReltnPO.setFundTpReltnPri((long) (17 + i));
            mcFundTpReltnPO.setIsInact("N");
            mcFundTpReltnPO.setRecVerNum(0L);
            mcFundTpReltnPO.setInitTime(LocalDateTime.now());
            mcFundTpReltnPO.setLastUpdTime(LocalDateTime.now());
            mcFundTpReltnPO.setLastUpdBy("MIG");
            mcFundTpReltnPO.setTagSeq(0L);
            dataList.add(mcFundTpReltnPO);
        }
//        dataList.forEach(mcFundTpReltnMapper::insert);
        saveToOracle(dataList);
    }

    @DS("oracle")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveToOracle(List<McFundTpReltnPO> list){
        mcFundTpReltnMapper.insert(list);
    }
}
