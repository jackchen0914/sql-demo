package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.service.IMcCeventService;
import org.example.service.IMcConvEvntService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author JackChen
 * @since 2026-01-07
 */
@RestController
@RequestMapping("/mcConvEvntPO")
@RequiredArgsConstructor
public class McConvEvntController {

    private final IMcConvEvntService IMcConvEvntService;

    @GetMapping("/exec")
    public String writeProcessedData() {
        IMcConvEvntService.writeProcessedData();
        return "ok";
    }
}
