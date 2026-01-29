package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.service.IMcAcOnlineFxReqService;
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
@RestController
@RequestMapping("/mcAcOnlineFxReqPO")
@Slf4j
@RequiredArgsConstructor
public class McAcOnlineFxReqController {

    private final IMcAcOnlineFxReqService iMcAcOnlineFxReqService;

    @GetMapping("/exec")
    public String writeProcessedData() {
        iMcAcOnlineFxReqService.writeProcessedData();
        return "ok";
    }

}
