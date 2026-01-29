package org.example.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.McInstclVerPO;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JackChen
 * @since 2025-12-22
 */
@Mapper
@DS("oracle")
public interface McInstclVerMapper extends BaseMapper<McInstclVerPO> {

    @Select("SELECT INSTCL_VID FROM MC_INSTCL_VER miv WHERE INSTCL_ID = #{vid} AND to_date(substr(#{date},1,10),'YYYY-MM-DD') BETWEEN VER_EFF_DATE AND VER_EXPR_DATE")
    McInstclVerPO findInstclVidByDate(@Param("vid") Long vid,@Param("date") String date);

}
