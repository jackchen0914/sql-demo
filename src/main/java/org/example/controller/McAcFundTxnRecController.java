package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.service.IMcAcFundTxnRecService;
import org.example.service.IMcCmpnyBankAcService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author JackChen
 * @since 2025-12-12
 */
@RestController
@RequestMapping("/mcAcFundTxnRecPO")
@Slf4j
@RequiredArgsConstructor
public class McAcFundTxnRecController {

    private final IMcAcFundTxnRecService iMcAcFundTxnRecService;

    @GetMapping("/exec")
    public String writeProcessedData(){
        log.info("jinlaile");
        iMcAcFundTxnRecService.writeProcessedData();
        return "ok";
    }


}
