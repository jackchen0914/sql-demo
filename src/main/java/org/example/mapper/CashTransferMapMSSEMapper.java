package org.example.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.CashTransferMapMSSEPO;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JackChen
 * @since 2026-03-03
 */
@DS("master")
@Mapper
public interface CashTransferMapMSSEMapper extends BaseMapper<CashTransferMapMSSEPO> {

}
