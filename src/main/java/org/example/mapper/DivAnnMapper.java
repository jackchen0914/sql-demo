package org.example.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.DivAnnPO;
import org.example.pojo.dtos.CaRSCDTO;
import org.example.pojo.dtos.DivAnnWithRightDTO;

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
public interface DivAnnMapper extends BaseMapper<DivAnnPO> {

    @Select("SELECT Top 5 AnnouncementNo, DeclarationDate, ExDividendDate, BookClosedDate, PayableDate, Market, CCY, FeeCCY, Instrument, AnnouncementSummary, WaiveScripFee, ActionTypeList, Status, ReferenceNo, [timestamp], Location, IncomeCode, TaxType, NetOfNegNetAmt FROM dbo.DivAnn WHERE ActionTypeList = '/RGHT/'")
    List<DivAnnPO> selectTop5();

    List<CaRSCDTO> selectDivAnnWithRight(@Param("offset") int offset, @Param("limit") int limit);
}
