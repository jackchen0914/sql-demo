package org.example.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.pojo.McCeventOptnPO;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author JackChen
 * @since 2026-03-09
 */
@Mapper
@DS("oracle")
public interface McCeventOptnMapper extends BaseMapper<McCeventOptnPO> {
    int batchInsert(@Param("list") List<McCeventOptnPO> list);
}
