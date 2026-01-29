package org.example.dataMigrationHandle;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.CashVoucherMapper;
import org.example.mapper.CashVoucherRequestMapper;
import org.example.mapper.McCmpnyBankAcMapper;
import org.example.pojo.CashVoucherPO;
import org.example.pojo.McCmpnyBankAcPO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class McCmpnyBankAcHandle {

    private final CashVoucherMapper cashVoucherMapper;

    private final CashVoucherRequestMapper cashVoucherRequestMapper;

    private final McCmpnyBankAcMapper mcCmpnyBankAcMapper;

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void writeProcessedData() {
        List<CashVoucherPO> cashVoucherPOS = cashVoucherMapper.selectList(null);
        log.info("---->{}",cashVoucherPOS);
//        List<McCmpnyBankAcPO> dataList = new ArrayList<>();
//        for (int i = 0; i < cashVoucherPOS.size(); i++) {
//            CashVoucherPO po = cashVoucherPOS.get(i);
//            McCmpnyBankAcPO mcCmpnyBankAcPO = new McCmpnyBankAcPO();
////            mcCmpnyBankAcPO.setCmpnyBankAcId();
//            mcCmpnyBankAcPO.setCmpnyCde("TFS");
//            mcCmpnyBankAcPO.setCmpnyBankCde(po.getSource());
//            mcCmpnyBankAcPO.setCmpnyBankAcCde(po.getBankAccount());
//            mcCmpnyBankAcPO.setCmpnyBankAcNum(po.getBankAccount());
////            mcCmpnyBankAcPO.setClntLedgAcCde(null);
////            mcCmpnyBankAcPO.setBrkrLedgAcCde(null);
//            mcCmpnyBankAcPO.setIsInact("N");
//            mcCmpnyBankAcPO.setIsIpo("N");
//            mcCmpnyBankAcPO.setIsInhouse("N");
//            mcCmpnyBankAcPO.setCmpnyBankAcDescr(po.getBankAccount());
////            mcCmpnyBankAcPO.setOverdraftAmtDescr(null);
//            mcCmpnyBankAcPO.setRecVerNum(0L);
//            mcCmpnyBankAcPO.setInitTime(LocalDateTime.parse(LocalDateTime.now().format(DATE_TIME_FORMATTER)));
//            mcCmpnyBankAcPO.setLastUpdTime(LocalDateTime.parse(LocalDateTime.now().format(DATE_TIME_FORMATTER)));
//            mcCmpnyBankAcPO.setLastUpdBy("MIG");
//            mcCmpnyBankAcPO.setTagSeq(0L);
////            mcCmpnyBankAcPO.setErpGlCde(null);
//            mcCmpnyBankAcPO.setCmpnyBankAcTypCde("CLIENT");
//            dataList.add(mcCmpnyBankAcPO);
//        }
//        mcCmpnyBankAcMapper.insert(dataList);
//        log.info("======>{}",cashVoucherPOS.size());
    }

}
