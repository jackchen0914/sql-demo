package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.service.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/migration")
@RequiredArgsConstructor
public class DataMigrationController {

    private final CaRscTOMsseService caRscTOMsseService;
    private final CashTransferToMsseService cashTransferToMsseService;
    private final CashVoucherToMsseService cashVoucherToMsseService;
    private final HoldCashToMsseService holdCashToMsseService;
    private final HoldInstrumentToMsseService holdInstrumentToMsseService;
    private final InstrumentVoucherToMsseService instrumentVoucherToMsseService;
    private final InterestDailyToMsseService interestDailyToMsseService;
    private final PortfolioFeeDailyToMsseService portfolioFeeDailyToMsseService;
    private final BrokerageToMsseService brokerageToMsseService;
    private final ClntPriceCapToMsseService clntPriceCapToMsseService;

    @GetMapping("/caRscTable")
    public String caRscTable(){
        return caRscTOMsseService.writeProcessedData();
    }

    @GetMapping("/cashTransferTable")
    public String cashTransferTable() {
        return cashTransferToMsseService.writeProcessedData();
    }

    @GetMapping("/cashVoucherTable")
    public String cashVoucherTable() {
        return cashVoucherToMsseService.writeProcessedData();
    }

    @GetMapping("/holdCashTable")
    public String holdCashTable() {
        return holdCashToMsseService.writeProcessedData();
    }

    @GetMapping("/holdInstrumentTable")
    public String holdInstrumentTable() {
        return holdInstrumentToMsseService.writeProcessedData();
    }

    @GetMapping("/instrumentVoucherTable")
    public String instrumentVoucherTable() {
        return instrumentVoucherToMsseService.writeProcessedData();
    }

    @GetMapping("/interestDailyTable")
    public String interestDailyTable() {
        return interestDailyToMsseService.writeProcessedData();
    }

    @GetMapping("/portfolioFeeDailyTable")
    public String portfolioFeeDailyTable() {
        return portfolioFeeDailyToMsseService.writeProcessedData();
    }

    @GetMapping("/brokerageTable")
    public String brokerageTable() {
        return brokerageToMsseService.writeProcessedData();
    }

    @GetMapping("/clntPriceCapTable")
    public String clntPriceCapTable() {
        return clntPriceCapToMsseService.writeProcessedData();
    }
}
