//package com.example;
//
//import lombok.extern.slf4j.Slf4j;
//import org.example.DataMigrationApplication;
//import org.example.service.impl.McCmpnyBankAcServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//
//@SpringBootTest(classes = DataMigrationApplication.class)
//@ActiveProfiles("dev")
//@Slf4j
//public class TestDM {
//
//    @Autowired
//    private McCmpnyBankAcServiceImpl mcCmpnyBankAcService;
//
//
//    @Test
//    public void test(){
//        long l = System.currentTimeMillis();
//        try {
//        mcCmpnyBankAcService.simpleFullMigration();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        long l1 = System.currentTimeMillis();
//
//        log.info("haoshi==>{}",l1-l);
//
//    }
//
//}
