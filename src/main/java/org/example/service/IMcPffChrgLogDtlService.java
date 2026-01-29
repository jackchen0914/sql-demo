package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.pojo.McPffChrgLogDtlPO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JackChen
 * @since 2025-12-29
 */
public interface IMcPffChrgLogDtlService extends IService<McPffChrgLogDtlPO> {

    void writeProcessedData();
}
