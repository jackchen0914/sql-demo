package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.pojo.McAcFundHoldRecPO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JackChen
 * @since 2025-12-23
 */
public interface IMcAcFundHoldRecService extends IService<McAcFundHoldRecPO> {


    void writeProcessedData();
}
