package org.example.config;

import org.example.batch.processor.*;
import org.example.batch.reader.*;
import org.example.batch.writer.*;
import org.example.pojo.ClntPriceCapPO;
import org.example.pojo.InstrumentVoucherPO;
import org.example.pojo.PortfoliofeeDailyPO;
import org.example.pojo.dtos.*;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private PortfolioFeeDailyProcessor portfolioFeeDailyProcessor;

    @Autowired
    private PortfolioFeeDailyReader portfolioFeeDailyReader;

    @Autowired
    private PortfolioFeeDailyWriter portfolioFeeDailyWriter;

    //=========== PortfolioFeeDaily ============
    @Bean
    public Step portfolioFeeMigrationStep(){
        return stepBuilderFactory.get("portfolioFeeMigrationStep")
                .<PortfoliofeeDailyPO, FeeMigrationResultDTO>chunk(1000)
                .reader(portfolioFeeDailyReader)
                .processor(portfolioFeeDailyProcessor)
                .writer(portfolioFeeDailyWriter)
                .faultTolerant()
                .skipLimit(3)
                .skip(Exception.class)
                .build();
    }

    @Bean
    public Job portfolioFeeMigrationJob(){
        return jobBuilderFactory.get("portfolioFeeMigrationJob")
                .incrementer(new RunIdIncrementer())
                .start(portfolioFeeMigrationStep())
                .build();
    }

    //=========== CA Rights Split Consolidation ============
    @Autowired
    private CaRSCProcessor caRSCProcessor;

    @Autowired
    private CaRSCReader caRSCReader;

    @Autowired
    private CaRSCWriter caRSCWriter;

    @Bean
    public Step caRSCtMigrationStep(){
        return stepBuilderFactory.get("caRSCtMigrationStep")
                .<CaRSCDTO, CaRSCResultDTO>chunk(1000)
                .reader(caRSCReader)
                .processor(caRSCProcessor)
                .writer(caRSCWriter)
                .faultTolerant()
                .skipLimit(3)
                .skip(Exception.class)
                .build();
    }

    @Bean
    public Job caRSCFeeMigrationJob(){
        return jobBuilderFactory.get("caRSCFeeMigrationJob")
                .incrementer(new RunIdIncrementer())
                .start(caRSCtMigrationStep())
                .build();
    }

    //=========== InstrumentVoucher ============
    @Autowired
    private InstrumentVoucherProcessor instrumentVoucherProcessor;

    @Autowired
    private InstrumentVoucherReader instrumentVoucherReader;

    @Autowired
    private InstrumentVoucherWriter instrumentVoucherWriter;

    @Bean
    public Step instrumentVoucherMigrationStep(){
        return stepBuilderFactory.get("instrumentVoucherMigrationStep")
                .<InstrumentVoucherPO, InstrumentVoucherResultDTO>chunk(1000)
                .reader(instrumentVoucherReader)
                .processor(instrumentVoucherProcessor)
                .writer(instrumentVoucherWriter)
                .faultTolerant()
                .skipLimit(3)
                .skip(Exception.class)
                .build();
    }

    @Bean
    public Job instrumentVoucherMigrationJob(){
        return jobBuilderFactory.get("instrumentVoucherMigrationJob")
                .incrementer(new RunIdIncrementer())
                .start(instrumentVoucherMigrationStep())
                .build();
    }

    //=========== ClntPriceCap ============
//    @Autowired
//    private ClntPriceCapProcessor clntPriceCapProcessor;
//
//    @Autowired
//    private ClntPriceCapReader clntPriceCapReader;
//
//    @Autowired
//    private ClntPriceCapWriter clntPriceCapWriter;
//
//    @Bean
//    public Step clntPriceCapMigrationStep(){
//        return stepBuilderFactory.get("clntPriceCapMigrationStep")
//                .<ClntPriceCapPO, ClntPriceCapResultDTO>chunk(1000)
//                .reader(clntPriceCapReader)
//                .processor(clntPriceCapProcessor)
//                .writer(clntPriceCapWriter)
//                .faultTolerant()
//                .skipLimit(3)
//                .skip(Exception.class)
//                .build();
//    }
//
//    @Bean
//    public Job clntPriceCapMigrationJob(){
//        return jobBuilderFactory.get("clntPriceCapMigrationJob")
//                .incrementer(new RunIdIncrementer())
//                .start(clntPriceCapMigrationStep())
//                .build();
//    }

    //=========== CashTransferAll ============
    @Autowired
    private CashTransferAllProcessor cashTransferAllProcessor;

    @Autowired
    private CashTransferAllReader cashTransferAllReader;

    @Autowired
    private CashTransferAllWriter cashTransferAllWriter;

    @Bean
    public Step cashTransferAllMigrationStep(){
        return stepBuilderFactory.get("cashTransferAllMigrationStep")
                .<CashTransferAllDTO, CashTransferAllResultDTO>chunk(1000)
                .reader(cashTransferAllReader)
                .processor(cashTransferAllProcessor)
                .writer(cashTransferAllWriter)
                .faultTolerant()
                .skipLimit(3)
                .skip(Exception.class)
                .build();
    }

    @Bean
    public Job cashTransferAllMigrationJob(){
        return jobBuilderFactory.get("cashTransferAllMigrationJob")
                .incrementer(new RunIdIncrementer())
                .start(cashTransferAllMigrationStep())
                .build();
    }

    //=========== Brokerage ============
    @Autowired
    private BrokerageProcessor brokerageProcessor;

    @Autowired
    private BrokerageReader brokerageReader;

    @Autowired
    private BrokerageWriter brokerageWriter;

    @Bean
    public Step brokerageMigrationStep(){
        return stepBuilderFactory.get("brokerageMigrationStep")
                .<BrokerageWithRageDTO, BrokerageWithRageResultDTO>chunk(1000)
                .reader(brokerageReader)
                .processor(brokerageProcessor)
                .writer(brokerageWriter)
                .faultTolerant()
                .skipLimit(3)
                .skip(Exception.class)
                .build();
    }

    @Bean
    public Job brokerageMigrationJob(){
        return jobBuilderFactory.get("brokerageMigrationJob")
                .incrementer(new RunIdIncrementer())
                .start(brokerageMigrationStep())
                .build();
    }

    //=========== CA Rights Split Consolidation ============
    @Autowired
    private BrokerageWithRageProcessor brokerageWithRageProcessor;

    @Autowired
    private BrokerageWithRageReader brokerageWithRageReader;

    @Autowired
    private BrokerageWithRageWriter brokerageWithRageWriter;

    @Bean
    public Step brokerageWithRageMigrationStep(){
        return stepBuilderFactory.get("brokerageWithRageMigrationStep")
                .<BrokerageWithRageDTO, BrokerageWithRageResultDTO>chunk(1000)
                .reader(brokerageWithRageReader)
                .processor(brokerageWithRageProcessor)
                .writer(brokerageWithRageWriter)
                .faultTolerant()
                .skipLimit(3)
                .skip(Exception.class)
                .build();
    }

    @Bean
    public Job brokerageWithRageMigrationJob(){
        return jobBuilderFactory.get("brokerageWithRageMigrationJob")
                .incrementer(new RunIdIncrementer())
                .start(brokerageWithRageMigrationStep())
                .build();
    }

    //=========== Full Migration Job ============
    @Bean
    public Job fullMigrationJob(){
        return jobBuilderFactory.get("fullMigrationJob")
                .incrementer(new RunIdIncrementer())
                .start(portfolioFeeMigrationStep())
                .next(caRSCtMigrationStep())
                .build();
    }
}
