package org.example.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.pojo.McAcFundHoldRecPO;
import org.example.pojo.McAcFundHoldTxnPO;

import java.util.List;

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
public interface McAcFundHoldTxnMapper extends BaseMapper<McAcFundHoldTxnPO> {

    int batchInsert(@Param("list") List<McAcFundHoldRecPO> mcAcFundHoldRecPOS);
}
