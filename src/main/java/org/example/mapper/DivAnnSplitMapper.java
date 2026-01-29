package org.example.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.DivAnnPO;
import org.example.pojo.DivAnnSplitPO;

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
public interface DivAnnSplitMapper extends BaseMapper<DivAnnSplitPO> {

    @Select("SELECT Top 5 AnnouncementNo, SplitFrom, SplitTo, SplitInstrument, Status, SplitClosingPrice, [timestamp]\n" +
            "FROM OctOBackFT_1231.dbo.DivAnnSplit")
    List<DivAnnSplitPO> selectTop5();

}
