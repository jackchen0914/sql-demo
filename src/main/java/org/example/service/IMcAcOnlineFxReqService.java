package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.pojo.McAcOnlineFxReqPO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JackChen
 * @since 2025-12-25
 */
public interface IMcAcOnlineFxReqService extends IService<McAcOnlineFxReqPO> {

    void writeProcessedData();
}
