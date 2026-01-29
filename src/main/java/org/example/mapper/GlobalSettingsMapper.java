package org.example.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import oracle.jdbc.proxy.annotation.ProxyAccess;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.GlobalSettingsPO;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JackChen
 * @since 2026-01-28
 */
@DS("master")
@Mapper
public interface GlobalSettingsMapper extends BaseMapper<GlobalSettingsPO> {

    @Select("select Setting from GlobalSettings where SettingName like 'OrderSource%' and replace(SettingName,'OrderSource','') = #{source} ")
    GlobalSettingsPO selectSettingBySource(@Param("source") String source);

}
