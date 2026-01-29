package org.example.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.pojo.McRmAcInstrLndratPO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JackChen
 * @since 2026-01-22
 */
@DS("oracle")
@Mapper
public interface McRmAcInstrLndratMapper extends BaseMapper<McRmAcInstrLndratPO> {

    int batchInsert(@Param("list") List<McRmAcInstrLndratPO> list);

}
