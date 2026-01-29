package com.example;

import lombok.extern.slf4j.Slf4j;
import org.example.DataMigrationApplication;
import org.example.mapper.CashVoucherMapper;
import org.example.pojo.CashVoucherPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest(classes = DataMigrationApplication.class)
@ActiveProfiles("dev")
@Slf4j
public class Test {

    @Autowired
    private CashVoucherMapper cashVoucherMapper;

    @org.junit.jupiter.api.Test
    public void test(){
        List<CashVoucherPO> cashVoucherPOS = cashVoucherMapper.selectAll();
        log.info("===>{}",cashVoucherPOS);
    }

//    @Autowired
//    private ClntMapper clntMapper;
//
//    @Autowired
//    private McCltdMapper mcCltdMapper;
//
//    @org.junit.jupiter.api.Test
//    public void test(){
//        System.out.println("----------测试开始-------------");
//        List<ClntPO> clntPOS = clntMapper.selectList(null);
//        clntPOS.forEach(System.out::println);
//    }
//
//    @org.junit.jupiter.api.Test
//    @DS("oracle")
//    public void test2(){
//        System.out.println("----------测试开始-------------");
//        List<McCltd> clntPOS = mcCltdMapper.selectList(null);
//        clntPOS.forEach(System.out::println);
//    }
}
