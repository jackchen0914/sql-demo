package org.example.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.TransactionTypesPO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JackChen
 * @since 2025-12-15
 */
@Mapper
@DS("master")
public interface TransactionTypesMapper extends BaseMapper<TransactionTypesPO> {

    @Select("select SignIndicator from dbo.TransactionTypes where  StkMoney = 'M' and TransactionTypes = #{TransactionTypes}")
    TransactionTypesPO selectTxnTypeCode(@Param("TransactionTypes") String transactionTypes);

    @Select("select SignIndicator from dbo.TransactionTypes where  StkMoney = 'S' and TransactionTypes = #{TransactionTypes}")
    TransactionTypesPO selectTxnTypeActionCode(@Param("TransactionTypes") String transactionTypes);

    /**
     * 一次性批量查询所有 StkMoney='M' 的记录，用于预加载到 Map，避免 N+1 查询
     */
    @Select("select TransactionTypes, SignIndicator from dbo.TransactionTypes where StkMoney = 'M'")
    List<TransactionTypesPO> selectAllTxnTypeMoney();

    /**
     * 一次性批量查询所有 StkMoney='S' 的记录，用于预加载到 Map，避免 N+1 查询
     */
    @Select("select TransactionTypes, SignIndicator from dbo.TransactionTypes where StkMoney = 'S'")
    List<TransactionTypesPO> selectAllTxnTypeAction();
}