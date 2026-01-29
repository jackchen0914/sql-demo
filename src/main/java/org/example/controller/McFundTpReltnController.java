package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.service.IMcAcFundTxnRecService;
import org.example.service.IMcFundTpReltnService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/mcFundTpReltnPO")
@Slf4j
@RequiredArgsConstructor
public class McFundTpReltnController {


    private final IMcFundTpReltnService iMcFundTpReltnService;

    @GetMapping("/exec")
    public String writeProcessedData(){
        log.info("jinlaile");
        iMcFundTpReltnService.writeProcessedData();
        return "ok";
    }

}
