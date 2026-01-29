package org.example.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.McInstrPO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JackChen
 * @since 2025-12-19
 */
@Mapper
@DS("oracle")
public interface McInstrMapper extends BaseMapper<McInstrPO> {

    @Select("select instr_id,instcl_id from mc_instr where instr_dsply_cde = #{code}")
    McInstrPO findInstrIdByCode(@Param("code") String code);

    @Select("select instr_id,instcl_id from mc_instr where instr_dsply_cde = #{code}")
    List<McInstrPO> findInstrIdByCodeList(@Param("code") String code);

    @Select("select instcl_id from mc_instr where instr_dsply_cde = #{instrument}")
    McInstrPO findInstclVidByInstrument(@Param("instrument") String code);
}
