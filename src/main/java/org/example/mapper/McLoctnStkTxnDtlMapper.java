package org.example.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.McLoctnStkTxnDtlPO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JackChen
 * @since 2026-01-16
 */
@DS("oracle")
@Mapper
public interface McLoctnStkTxnDtlMapper extends BaseMapper<McLoctnStkTxnDtlPO> {

    void batchInsert(List<McLoctnStkTxnDtlPO> mcLoctnStkTxnDtlPOS);
}
