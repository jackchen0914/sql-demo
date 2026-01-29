package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.service.IMcAcFundRecService;
import org.example.service.IMcAcFundTxnRecService;
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
@RequestMapping("/mcAcFundRecPO")
@RequiredArgsConstructor
@Slf4j
public class McAcFundRecController {

    private final IMcAcFundRecService iMcAcFundRecService;

    @GetMapping("/exec")
    public String writeProcessedData() throws InterruptedException {
        log.info("jinlaile");
        iMcAcFundRecService.writeProcessedData();
        return "ok";
    }

}
