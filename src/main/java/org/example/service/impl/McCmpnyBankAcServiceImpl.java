package org.example.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.CashVoucherMapper;
import org.example.mapper.CashVoucherRequestMapper;
import org.example.mapper.McCmpnyBankAcMapper;
import org.example.pojo.CashVoucherPO;
import org.example.pojo.McCmpnyBankAcPO;
import org.example.service.DatabaseManipulationService;
import org.example.service.IMcCmpnyBankAcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
public class McCmpnyBankAcServiceImpl extends ServiceImpl<McCmpnyBankAcMapper, McCmpnyBankAcPO> implements IMcCmpnyBankAcService {

    private final CashVoucherMapper cashVoucherMapper;

    private final CashVoucherRequestMapper cashVoucherRequestMapper;

    private final McCmpnyBankAcMapper mcCmpnyBankAcMapper;

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void writeProcessedData() {
        List<CashVoucherPO> cashVoucherPOS = cashVoucherMapper.selectTop5();
        log.info("---->{}",cashVoucherPOS);
        log.info("LocalDateTime---->{}",LocalDateTime.now().toString().replaceAll("T",""));
        log.info("LocalDateTime---->{}",LocalDateTime.now().format(DATE_TIME_FORMATTER));
        List<McCmpnyBankAcPO> dataList = new ArrayList<>();
        for (int i = 0; i < cashVoucherPOS.size(); i++) {
            CashVoucherPO po = cashVoucherPOS.get(i);
            McCmpnyBankAcPO mcCmpnyBankAcPO = new McCmpnyBankAcPO();
            mcCmpnyBankAcPO.setCmpnyBankAcId((long) (2048004));
//            mcCmpnyBankAcPO.setCmpnyBankAcId(IdUtil.getSnowflakeNextId());
            log.info("xunhuandezhi ======>{}",i);
            mcCmpnyBankAcPO.setCmpnyCde("TFS");
            mcCmpnyBankAcPO.setCmpnyBankCde(po.getSource());
            mcCmpnyBankAcPO.setCmpnyBankAcCde(Objects.equals(po.getBankAccount(), "") ? String.valueOf(100000+i) : po.getBankAccount());
            mcCmpnyBankAcPO.setCmpnyBankAcNum(po.getBankAccount());
//            mcCmpnyBankAcPO.setClntLedgAcCde(null);
//            mcCmpnyBankAcPO.setBrkrLedgAcCde(null);
            mcCmpnyBankAcPO.setIsInact("N");
            mcCmpnyBankAcPO.setIsIpo("N");
            mcCmpnyBankAcPO.setIsInhouse("N");
            mcCmpnyBankAcPO.setCmpnyBankAcDescr(po.getBankAccount());
//            mcCmpnyBankAcPO.setOverdraftAmtDescr(null);
            mcCmpnyBankAcPO.setRecVerNum(0L);
//            mcCmpnyBankAcPO.setInitTime(LocalDateTime.now().format(DATE_TIME_FORMATTER));
            mcCmpnyBankAcPO.setInitTime(LocalDateTime.now());
            mcCmpnyBankAcPO.setLastUpdTime(LocalDateTime.now());
            mcCmpnyBankAcPO.setLastUpdBy("MIG");
            mcCmpnyBankAcPO.setTagSeq(0L);
//            mcCmpnyBankAcPO.setErpGlCde(null);
            mcCmpnyBankAcPO.setCmpnyBankAcTypCde("CLIENT");
            dataList.add(mcCmpnyBankAcPO);
        }
        log.info("dataList=>{}",dataList);
//        dataList.forEach(mcCmpnyBankAcMapper::insert);
//        mcCmpnyBankAcMapper.insert(dataList);
        saveToOracle(dataList);
    }

    @DS("oracle")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveToOracle(List<McCmpnyBankAcPO> list){
        mcCmpnyBankAcMapper.insert(list);
    }

}
