package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.service.IMcAcHoldStkTxnDtlService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author JackChen
 * @since 2025-12-24
 */
@RestController
@RequestMapping("/mcAcHoldStkTxnDtlPO")
@RequiredArgsConstructor
@Slf4j
public class McAcHoldStkTxnDtlController {

    private final IMcAcHoldStkTxnDtlService iMcAcHoldStkTxnDtlService;

    @GetMapping("/exec")
    public String writeProcessedData() {
        iMcAcHoldStkTxnDtlService.writeProcessedData();
        return "ok";
    }

}
