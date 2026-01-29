package org.example.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.McInstrPO;
import org.example.pojo.McMrktPO;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JackChen
 * @since 2025-12-19
 */
@DS("oracle")
@Mapper
public interface McMrktMapper extends BaseMapper<McMrktPO> {

    @Select("select mrkt_id  from mc_mrkt where  mrkt_cde = #{code}")
    McMrktPO findInstrIdByCode(@Param("code") String code);

}
