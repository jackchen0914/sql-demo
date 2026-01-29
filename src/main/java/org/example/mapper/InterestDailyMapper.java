package org.example.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.ClntPriceCapPO;
import org.example.pojo.InterestDailyPO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JackChen
 * @since 2025-12-25
 */
@Mapper
@DS("master")
public interface InterestDailyMapper extends BaseMapper<InterestDailyPO> {

    @Select("SELECT TOP 5 [Date], ClntCode, AcctType, Market, CCY, BankAccount, [Type], ReferenceNo, Interest, Principal, Rate, CalculationPeriod, Status, PostDate, SysInterestCal, [Timestamp], MethodType\n" +
            "FROM dbo.InterestDaily;")
    List<InterestDailyPO> selectTop5();


    @Select("SELECT [Date], ClntCode, AcctType, Market, CCY, BankAccount, [Type], ReferenceNo, Interest, Principal, Rate, CalculationPeriod, Status, PostDate, SysInterestCal, [Timestamp], MethodType\n" +
            "FROM dbo.InterestDaily ORDER BY ClntCode DESC OFFSET #{offset} ROWS FETCH NEXT #{limit} ROWS ONLY")
    List<InterestDailyPO> selectByPage(@Param("offset") int offset, @Param("limit") int limit);
}
