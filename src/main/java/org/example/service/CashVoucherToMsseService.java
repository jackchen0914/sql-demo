package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.pojo.McAcFundRecPO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JackChen
 * @since 2025-12-12
 */
public interface CashVoucherToMsseService {

    /**
     * 全量迁移
     */
    String writeProcessedData();

    /**
     * 限制最大迁移条数（用于测试，0 或负数表示不限制）
     *
     * @param maxRows 最大迁移行数，例如 1_000_000 表示只跑 100 万
     */
    String writeProcessedData(long maxRows);

}
