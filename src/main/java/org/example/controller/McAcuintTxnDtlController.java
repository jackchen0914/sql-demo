package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.service.IMcAcuintTxnDtlService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author JackChen
 * @since 2025-12-25
 */

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/mcAcuintTxnDtlPO")
public class McAcuintTxnDtlController {

    private final IMcAcuintTxnDtlService iMcAcuintTxnDtlService;

    @GetMapping("/exec")
    public String writeProcessedData() {
        iMcAcuintTxnDtlService.writeProcessedData();
        return "ok";
    }

}
