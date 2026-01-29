package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.service.BatchJobService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/migration")
@RequiredArgsConstructor
public class BatchJobController {

    private final BatchJobService batchJobService;

    @GetMapping("/portfolioFee")
    public String startPortfolioFeeMigration(){
        return batchJobService.startPortfolioFeeMigration();
    }

    @GetMapping("/caRSC")
    public String startCaRSCMigration(){
        return batchJobService.startCaRSCMigration();
    }

    @GetMapping("/instrumentVoucher")
    public String startInstrumentVoucherMigration(){
        return batchJobService.startInstrumentVoucherMigration();
    }

//    @GetMapping("/clntPriceCap")
//    public String startClntPriceCapMigration(){
//        return batchJobService.startClntPriceCapMigration();
//    }

    @GetMapping("/cashTransferAll")
    public String startCashTransferAllMigration(){
        return batchJobService.startCashTransferAllMigration();
    }

    @GetMapping("/brokerage")
    public String startBrokerageMigration(){
        return batchJobService.startBrokerageMigration();
    }

    @GetMapping("/brokerageWithRage")
    public String startBrokerageWithRageMigration(){
        return batchJobService.startBrokerageWithRageMigration();
    }

    @GetMapping("/full")
    public String migrateAll(){
        return batchJobService.runFullMigration();
    }
}
