//package org.example.config;
//
//import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Configuration
//public class TransactionConfig {
//
//    @Bean
//    @Primary
//    public PlatformTransactionManager transactionManager(DynamicRoutingDataSource dataSourceProperties) {
//        return new DataSourceTransactionManager(dataSourceProperties);
//    }
//
//}
