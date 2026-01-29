package org.example.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.CashTransferPO;
import org.example.pojo.PortfoliofeeDailyPO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JackChen
 * @since 2025-12-29
 */
@Mapper
@DS("master")
public interface PortfoliofeeDailyMapper extends BaseMapper<PortfoliofeeDailyPO> {

    @Select("SELECT TOP 5 [Date], ClntCode, AcctType, Market, CCY, [Type], PortfolioValue, Rate, CalculationPeriod, Status, PostDate, ReferenceNo, FeeBeforeMin, Fee, ORFee\n" +
            "FROM dbo.PortfolioFee_Daily")
    List<PortfoliofeeDailyPO> selectTop5();

    @Select("SELECT [Date], ClntCode, AcctType, Market, CCY, [Type], PortfolioValue, Rate, CalculationPeriod, Status, PostDate, ReferenceNo, FeeBeforeMin, Fee, ORFee\n" +
            "FROM dbo.PortfolioFee_Daily ORDER BY ClntCode OFFSET #{offset} ROWS FETCH NEXT #{limit} ROWS ONLY")
    List<PortfoliofeeDailyPO> selectByPage(@Param("offset") int offset,@Param("limit") int limit);

    @Select("SELECT COUNT(*) FROM dbo.PortfolioFee_Daily")
    long selectCount();
}
