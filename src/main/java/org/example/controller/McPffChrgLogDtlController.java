package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.service.IMcAcuintTxnDtlService;
import org.example.service.IMcPffChrgLogDtlService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author JackChen
 * @since 2025-12-29
 */
@RestController
@RequestMapping("/mcPffChrgLogDtlPO")
@RequiredArgsConstructor
public class McPffChrgLogDtlController {

    private final IMcPffChrgLogDtlService iMcPffChrgLogDtlService;

    @GetMapping("/exec")
    public String writeProcessedData() {
        iMcPffChrgLogDtlService.writeProcessedData();
        return "ok";
    }

}
