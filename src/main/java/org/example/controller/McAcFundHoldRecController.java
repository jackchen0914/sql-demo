package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.service.IMcAcFundHoldRecService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author JackChen
 * @since 2025-12-23
 */
@RestController
@RequestMapping("/mcAcFundHoldRecPO")
@Slf4j
@RequiredArgsConstructor
public class McAcFundHoldRecController {

    private final IMcAcFundHoldRecService iMcAcFundHoldRecService;

    @GetMapping("/exec")
    public String writeProcessedData() {
        iMcAcFundHoldRecService.writeProcessedData();
        return "ok";
    }

}
