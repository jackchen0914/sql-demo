package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.pojo.McAcuintTxnDtlPO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JackChen
 * @since 2025-12-25
 */
public interface IMcAcuintTxnDtlService extends IService<McAcuintTxnDtlPO> {

    void writeProcessedData();
}
