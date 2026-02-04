package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.example.batch.reader.*;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class BatchJobService{

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    @Qualifier("portfolioFeeMigrationJob")
    private Job portfolioFeeMigrationJob;

    @Autowired
    private PortfolioFeeDailyReader portfolioFeeDailyReader;

    public String startPortfolioFeeMigration(){
        try {
            portfolioFeeDailyReader.reset();
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("timestamp",System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(portfolioFeeMigrationJob, jobParameters);
            return "successfully";
        } catch (Exception e) {
            log.error("error",e);
            return "error: " + e.getMessage();
        }
    }

    //=========== CA Rights Split Consolidation ============
    @Autowired
    @Qualifier("caRSCFeeMigrationJob")
    private Job caRSCFeeMigrationJob;

    @Autowired
    private CaRSCReader caRSCReader;

    public String startCaRSCMigration(){
        try {
            caRSCReader.reset();
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("timestamp",System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(caRSCFeeMigrationJob, jobParameters);
            return "successfully";
        } catch (Exception e) {
            log.error("error",e);
            return "error: " + e.getMessage();
        }
    }

    //=========== InstrumentVoucher ============
    @Autowired
    @Qualifier("instrumentVoucherMigrationJob")
    private Job instrumentVoucherMigrationJob;

    @Autowired
    private InstrumentVoucherReader instrumentVoucherReader;

    public String startInstrumentVoucherMigration(){
        try {
            instrumentVoucherReader.reset();
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("timestamp",System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(instrumentVoucherMigrationJob, jobParameters);
            return "successfully";
        } catch (Exception e) {
            log.error("error",e);
            return "error: " + e.getMessage();
        }
    }

    //=========== ClntPriceCap ============
    @Autowired
    @Qualifier("clntPriceCapMigrationJob")
    private Job clntPriceCapMigrationJob;

    @Autowired
    private ClntPriceCapReader clntPriceCapReader;

    public String startClntPriceCapMigration(){
        try {
            clntPriceCapReader.reset();
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("timestamp",System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(clntPriceCapMigrationJob, jobParameters);
            return "successfully";
        } catch (Exception e) {
            log.error("error",e);
            return "error: " + e.getMessage();
        }
    }

    //=========== CashTransferAll ============
    @Autowired
    @Qualifier("cashTransferAllMigrationJob")
    private Job cashTransferAllMigrationJob;

    @Autowired
    private CashTransferAllReader cashTransferAllReader;

    public String startCashTransferAllMigration(){
        try {
            cashTransferAllReader.reset();
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("timestamp",System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(cashTransferAllMigrationJob, jobParameters);
            return "successfully";
        } catch (Exception e) {
            log.error("error",e);
            return "error: " + e.getMessage();
        }
    }

    //=========== Brokerage ============
    @Autowired
    @Qualifier("brokerageWithRageMigrationJob")
    private Job brokerageWithRageMigrationJob;

    @Autowired
    private BrokerageWithRageReader brokerageWithRageReader;

    public String startBrokerageWithRageMigration(){
        try {
            brokerageWithRageReader.reset();
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("timestamp",System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(brokerageWithRageMigrationJob, jobParameters);
            return "successfully";
        } catch (Exception e) {
            log.error("error",e);
            return "error: " + e.getMessage();
        }
    }

    //=========== InterestDaily ============
    @Autowired
    @Qualifier("interestDailyMigrationJob")
    private Job interestDailyMigrationJob;

    @Autowired
    private InterestDailyReader interestDailyReader;

    public String startInterestDailyMigration(){
        try {
            interestDailyReader.reset();
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("timestamp",System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(interestDailyMigrationJob, jobParameters);
            return "successfully";
        } catch (Exception e) {
            log.error("error",e);
            return "error: " + e.getMessage();
        }
    }

    //=========== CashVoucher ============
    @Autowired
    @Qualifier("cashVoucherMigrationJob")
    private Job cashVoucherMigrationJob;

    @Autowired
    private CashVoucherReader cashVoucherReader;

    public String startCashVoucherMigration(){
        try {
            cashVoucherReader.reset();
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("timestamp",System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(cashVoucherMigrationJob, jobParameters);
            return "successfully";
        } catch (Exception e) {
            log.error("error",e);
            return "error: " + e.getMessage();
        }
    }

    //=========== HoldCash ============
    @Autowired
    @Qualifier("holdCashMigrationJob")
    private Job holdCashMigrationJob;

    @Autowired
    private HoldCashReader holdCashReader;

    public String startHoldCashMigration(){
        try {
            holdCashReader.reset();
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("timestamp",System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(holdCashMigrationJob, jobParameters);
            return "successfully";
        } catch (Exception e) {
            log.error("error",e);
            return "error: " + e.getMessage();
        }
    }

    //=========== HoldInstrument ============
    @Autowired
    @Qualifier("holdInstrumentMigrationJob")
    private Job holdInstrumentMigrationJob;

    @Autowired
    private HoldInstrumentReader holdInstrumentReader;

    public String startHoldInstrumentMigration(){
        try {
            holdInstrumentReader.reset();
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("timestamp",System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(holdInstrumentMigrationJob, jobParameters);
            return "successfully";
        } catch (Exception e) {
            log.error("error",e);
            return "error: " + e.getMessage();
        }
    }

    //=========== Full Migration ============
    @Autowired
    @Qualifier("fullMigrationJob")
    private Job fullMigrationJob;

    public String runFullMigration() {
        try {
            portfolioFeeDailyReader.reset();
            caRSCReader.reset();
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("timestamp",System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(fullMigrationJob, jobParameters);
            return "all successfully";
        } catch (Exception e) {
            log.error("error",e);
            return "all error: " + e.getMessage();
        }
    }

}
