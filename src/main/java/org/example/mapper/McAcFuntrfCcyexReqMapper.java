package org.example.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.McAcFuntrfCcyexReqPO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JackChen
 * @since 2026-02-09
 */
@DS("oracle")
@Mapper
public interface McAcFuntrfCcyexReqMapper extends BaseMapper<McAcFuntrfCcyexReqPO> {

    void batchInsert(List<McAcFuntrfCcyexReqPO> mcAcFuntrfCcyexReqPOS);
}
