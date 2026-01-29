package org.example.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.ForexRatePO;
import org.example.pojo.TransactionTypesPO;

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
    ForexRatePO selectRateByDate(@Param("currency") String currency,@Param("date") String date);

}
