package org.example.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.pojo.BrokeragePO;
import org.example.pojo.dtos.BrokerageWithRageDTO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JackChen
 * @since 2026-01-27
 */
@Mapper
@DS("master")
public interface BrokerageMapper extends BaseMapper<BrokeragePO> {

   List<BrokerageWithRageDTO> selectBrokerageAll(@Param("offset") int offset, @Param("limit") int limit);

}
