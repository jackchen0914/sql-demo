package org.example.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.McAcFundGenStmtRemrkPO;

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
public interface McAcFundGenStmtRemrkMapper extends BaseMapper<McAcFundGenStmtRemrkPO> {

    void batchInsert(List<McAcFundGenStmtRemrkPO> mcAcFundGenStmtRemrkPOS);
}
