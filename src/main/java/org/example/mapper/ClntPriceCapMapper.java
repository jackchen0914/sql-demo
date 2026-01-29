package org.example.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.ClntPriceCapPO;
import org.example.pojo.InstrumentVoucherPO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JackChen
 * @since 2026-01-22
 */
@DS("master")
@Mapper
public interface ClntPriceCapMapper extends BaseMapper<ClntPriceCapPO> {
    @Select("SELECT ClntCode, Market, Instrument, MarginPercent, PriceCap, Description, SyncRef, SyncRefCMS, [timestamp], TTLRef, StockLimit\n" +
            "FROM dbo.ClntPriceCap WHERE Instrument not like '%[a-zA-Z]%' escape '\\' ORDER BY ClntCode DESC OFFSET #{offset} ROWS FETCH NEXT #{limit} ROWS ONLY")
    List<ClntPriceCapPO> selectByPage(@Param("offset") int offset, @Param("limit") int limit);
}
