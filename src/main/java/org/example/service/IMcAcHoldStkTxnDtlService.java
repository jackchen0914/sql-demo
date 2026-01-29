package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.pojo.McAcHoldStkTxnDtlPO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JackChen
 * @since 2025-12-24
 */
public interface IMcAcHoldStkTxnDtlService extends IService<McAcHoldStkTxnDtlPO> {

    void writeProcessedData();
}
