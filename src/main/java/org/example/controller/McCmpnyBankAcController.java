package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.service.DatabaseManipulationService;
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
@Slf4j
@RestController
@RequestMapping("/mcCmpnyBankAcPO")
@RequiredArgsConstructor
public class McCmpnyBankAcController {

//    private final DatabaseManipulationService databaseManipulationService;
    private final IMcCmpnyBankAcService iMcCmpnyBankAcService;

    @GetMapping("/exec")
    public String writeProcessedData(){
        log.info("jinlaile");
        iMcCmpnyBankAcService.writeProcessedData();
        return "ok";
    }

}
