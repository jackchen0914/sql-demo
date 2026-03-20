package org.example.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.ForexRatePO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JackChen
 * @since 2025-12-16
 */
@Mapper
@DS("master")
public interface ForexRateMapper extends BaseMapper<ForexRatePO> {

    @Select("select xrate from dbo.ForexRate fr where ccy = #{currency} and date = #{date};")
    ForexRatePO selectRateByDate(@Param("currency") String currency, @Param("date") String date);

    /**
     * 批量查询指定年份区间内的所有汇率记录，用于预加载到 Map，避免 N+1 查询
     * key 格式：ccy + "_" + date（yyyy-MM-dd），由 Service 层拼接
     */
    @Select("select CCY, Date, XRate from dbo.ForexRate where Date >= #{startDate} and Date < #{endDate}")
    List<ForexRatePO> selectRatesByDateRange(@Param("startDate") String startDate, @Param("endDate") String endDate);
}