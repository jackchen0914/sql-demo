package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.pojo.McAcStkTxnDtlPO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JackChen
 * @since 2025-12-19
 */
public interface IMcAcStkTxnDtlService extends IService<McAcStkTxnDtlPO> {

    void writeProcessedData() throws InterruptedException;
}
