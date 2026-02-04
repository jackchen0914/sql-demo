package org.example.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.pojo.McAcHoldStkTxnDtlPO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JackChen
 * @since 2025-12-24
 */
@Mapper
@DS("oracle")
public interface McAcHoldStkTxnDtlMapper extends BaseMapper<McAcHoldStkTxnDtlPO> {

    int batchInsert(@Param("list") List<McAcHoldStkTxnDtlPO> mcAcHoldStkTxnDtlPOS);
}
