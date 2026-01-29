package org.example.config;


import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Component
public class CustomBatchConfigurer extends DefaultBatchConfigurer {

    @Override
    public void setDataSource(DataSource dataSource) {
        DataSource h2DataSource = DataSourceBuilder.create()
                .driverClassName("org.h2.Driver")
                .url("jdbc:h2:mem:batchdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE")
                .username("sa")
                .password("")
                .build();
        super.setDataSource(h2DataSource);
    }

//    @Bean(name = "batchDataSource")
//    public DataSource batchDataSource() {
//        return DataSourceBuilder.create()
//                .driverClassName("org.h2.Driver")
//                .url("jdbc:h2:mem:batchdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE")
//                .username("sa")
//                .password("")
//                .build();
//    }
//
//    @Bean(name = "batchTransactionManager")
//    public PlatformTransactionManager batchTransactionManager(@Qualifier("batchDataSource") DataSource batchDataSource) {
//        return new DataSourceTransactionManager(batchDataSource);
//    }
//
//    @Bean
//    public JobRepository jobRepository(@Qualifier("batchDataSource") DataSource batchDataSource,
//                                       @Qualifier("batchTransactionManager") PlatformTransactionManager transactionManager) throws Exception {
//        JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
//        jobRepositoryFactoryBean.setDataSource(batchDataSource);
//        jobRepositoryFactoryBean.setTransactionManager(transactionManager);
//        jobRepositoryFactoryBean.setIsolationLevelForCreate("ISOLATION_SERIALIZABLE");
//        jobRepositoryFactoryBean.setTablePrefix("BATCH_");
//        jobRepositoryFactoryBean.afterPropertiesSet();
//        return jobRepositoryFactoryBean.getObject();
//    }

//    @Autowired
//    private DynamicDataSourceProperties dynamicDataSourceProperties;

//    @Autowired
//    private DataSource dataSource;

//    @Bean
//    public JobRepository jobRepository(PlatformTransactionManager transactionManager) throws Exception {
//        JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
//        jobRepositoryFactoryBean.setDataSource(dataSource);
//        jobRepositoryFactoryBean.setTransactionManager(transactionManager);
//        jobRepositoryFactoryBean.afterPropertiesSet();
//        return jobRepositoryFactoryBean.getObject();
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager() {
//        return new DataSourceTransactionManager(dataSource);
//    }


//    @Bean
//    @Primary
//    public DataSource dataSource() {
//        DynamicRoutingDataSource dataSource = new DynamicRoutingDataSource();
//        Map<String, DataSource> dataSourceMap = dynamicDataSourceProperties.getDatasource().entrySet().stream()
//                .collect(Collectors.toMap(
//                        Map.Entry::getKey,
//                        entry -> DataSourceBuilder.create()
//                                .driverClassName(entry.getValue().getDriverClassName())
//                                .url(entry.getValue().getUrl())
//                                .username(entry.getValue().getUsername())
//                                .password(entry.getValue().getPassword())
//                                .build()
//                ));
//        dataSource.
//        return dataSource;
//    }
}
