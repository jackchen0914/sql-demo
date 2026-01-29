package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.pojo.McAcFundTxnRecPO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JackChen
 * @since 2025-12-12
 */
public interface IMcAcFundTxnRecService extends IService<McAcFundTxnRecPO> {

    void writeProcessedData();
}
