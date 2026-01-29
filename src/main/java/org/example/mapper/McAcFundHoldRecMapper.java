package org.example.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.McAcFundHoldRecPO;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JackChen
 * @since 2025-12-23
 */
@Mapper
@DS("oracle")
public interface McAcFundHoldRecMapper extends BaseMapper<McAcFundHoldRecPO> {

}
