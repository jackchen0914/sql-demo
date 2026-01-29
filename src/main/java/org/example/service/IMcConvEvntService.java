package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.pojo.McConvEvntPO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JackChen
 * @since 2026-01-07
 */
public interface IMcConvEvntService extends IService<McConvEvntPO> {

    void writeProcessedData();
}
