package org.example.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.DivAnnCombinePO;
import org.example.pojo.DivAnnPO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JackChen
 * @since 2026-01-07
 */
@DS("master")
@Mapper
public interface DivAnnCombineMapper extends BaseMapper<DivAnnCombinePO> {

    @Select("SELECT Top 5 AnnouncementNo, CombineFrom, CombineTo, CombineInstrument, Status, CombineClosingPrice, [timestamp]\n" +
            "FROM dbo.DivAnnCombine")
    List<DivAnnCombinePO> selectTop5();

}
