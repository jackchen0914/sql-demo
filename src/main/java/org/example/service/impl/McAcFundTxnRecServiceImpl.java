package org.example.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.CashVoucherMapper;
import org.example.mapper.CashVoucherRequestMapper;
import org.example.mapper.McAcFundTxnRecMapper;
import org.example.pojo.CashVoucherPO;
import org.example.pojo.McAcFundTxnRecPO;
import org.example.pojo.McCmpnyBankAcPO;
import org.example.service.DatabaseManipulationService;
import org.example.service.IMcAcFundTxnRecService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类 CashVoucher
 * </p>
 *
 * @author JackChen
 * @since 2025-12-12
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class McAcFundTxnRecServiceImpl extends ServiceImpl<McAcFundTxnRecMapper, McAcFundTxnRecPO> implements IMcAcFundTxnRecService {
    @Override
    public void writeProcessedData() {

    }
//public class McAcFundTxnRecServiceImpl implements DatabaseManipulationService {

//    private final CashVoucherMapper cashVoucherMapper;
//
//    private final CashVoucherRequestMapper cashVoucherRequestMapper;
//
//    private final McAcFundTxnRecMapper mcAcFundTxnRecMapper;
//
//    @Override
//    public void writeProcessedData() {
//        List<CashVoucherPO> cashVoucherPOS = cashVoucherMapper.selectTop5();
//        log.info("---->{}",cashVoucherPOS);
//        List<McAcFundTxnRecPO> dataList = new ArrayList<>();
//        for (int i = 0; i < cashVoucherPOS.size(); i++) {
//            CashVoucherPO po = cashVoucherPOS.get(i);
//            McAcFundTxnRecPO mcAcFundTxnRecPO = new McAcFundTxnRecPO();
//            mcAcFundTxnRecPO.setAcFundTxnRid((long) (16000000+i));
//            mcAcFundTxnRecPO.setFundStatCde(fundStatCdeTrans(po.getStatus()));
//            mcAcFundTxnRecPO.setIsRev("N");
//            mcAcFundTxnRecPO.setRevAcFundTxnRid(null);
//            mcAcFundTxnRecPO.setRecVerNum(0L);
//            mcAcFundTxnRecPO.setInitTime(LocalDateTime.now());
//            mcAcFundTxnRecPO.setLastUpdTime(LocalDateTime.now());
//            mcAcFundTxnRecPO.setLastUpdBy("MIG");
//            mcAcFundTxnRecPO.setTagSeq(0L);
//            dataList.add(mcAcFundTxnRecPO);
//        }
////        dataList.forEach(mcAcFundTxnRecMapper::insert);
//        saveToOracle(dataList);
//    }
//
//    @DS("oracle")
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    public void saveToOracle(List<McAcFundTxnRecPO> list){
//        mcAcFundTxnRecMapper.insert(list);
//    }
//
//    private String fundStatCdeTrans(String code) {
//        switch (code){
//            case "NEW": return "DRAFT";
//            case "DEL": return "DELETED";
//            case "REJ": return "REJECTED";
//            case "APP": return "APPROVED";
//            case "CAN": return "REVERSED";
//            default: return " ";
//        }
//    }


}
