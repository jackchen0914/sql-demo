package org.example;


import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@SpringBootApplication
@MapperScan("org.example.mapper") //org.example.**.mapper
//public class DataMigrationApplication implements ApplicationRunner {
public class DataMigrationApplication {

//    @Autowired
//    private DataSource dataSource;

    public static void main(String[] args) {
        SpringApplication.run(DataMigrationApplication.class, args);
    }

//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//
//        log.info("数据源类型{}",dataSource.getClass().getName());
//
//        if (dataSource instanceof DynamicRoutingDataSource){
//            log.info("多数据源配置成功");
//            DynamicRoutingDataSource ds = (DynamicRoutingDataSource)dataSource;
//            log.info("可用{}",ds.getDataSources().keySet());
//        }
//
//    }
}


