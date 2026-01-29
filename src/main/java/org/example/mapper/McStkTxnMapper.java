package org.example.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.McStkTxnPO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JackChen
 * @since 2025-12-19
 */
@Mapper
@DS("oracle")
public interface McStkTxnMapper extends BaseMapper<McStkTxnPO> {

    void batchInsert(List<McStkTxnPO> mcStkTxnPOList);
}
