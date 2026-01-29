package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.pojo.McCmpnyBankAcPO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JackChen
 * @since 2025-12-12
 */
public interface IMcCmpnyBankAcService extends IService<McCmpnyBankAcPO> {

    void writeProcessedData();

}
