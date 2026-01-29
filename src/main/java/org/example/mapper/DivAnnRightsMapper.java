package org.example.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.DivAnnPO;
import org.example.pojo.DivAnnRightsPO;

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
public interface DivAnnRightsMapper extends BaseMapper<DivAnnRightsPO> {

    @Select("SELECT Top 5 AnnouncementNo, RightsIssue, RightsFor, RightsInstrument, Status, [timestamp]\n" +
            "FROM dbo.DivAnnRights")
    List<DivAnnRightsPO> selectTop5();

}
