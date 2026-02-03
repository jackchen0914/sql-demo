package org.example.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.pojo.McAcFundRecPO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JackChen
 * @since 2025-12-12
 */
@Mapper
@DS("oracle")
public interface McAcFundRecMapper extends BaseMapper<McAcFundRecPO> {

    int batchInsert(@Param("list") List<McAcFundRecPO> mcAcFundRecPOS);
}
