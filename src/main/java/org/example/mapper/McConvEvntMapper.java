package org.example.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.pojo.McCeventPO;
import org.example.pojo.McConvEvntPO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JackChen
 * @since 2026-01-07
 */
@DS("oracle")
@Mapper
public interface McConvEvntMapper extends BaseMapper<McConvEvntPO> {

    int batchInsert(@Param("list") List<McConvEvntPO> list);

}
