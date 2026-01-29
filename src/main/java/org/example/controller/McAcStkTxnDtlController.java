package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.service.IMcAcStkTxnDtlService;
import org.example.service.IMcStkTxnService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author JackChen
 * @since 2025-12-19
 */
@RestController
@RequestMapping("/mcAcStkTxnDtlPO")
@RequiredArgsConstructor
@Slf4j
public class McAcStkTxnDtlController {


    private final IMcAcStkTxnDtlService iMcAcStkTxnDtlService;

    @GetMapping("/exec")
    public String writeProcessedData() throws InterruptedException {
        iMcAcStkTxnDtlService.writeProcessedData();
        return "ok";
    }

}
