package org.example.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.example.pojo.McPffChrgLogDtlPO;
import org.example.pojo.McPffChrgLogPO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JackChen
 * @since 2025-12-29
 */
@Mapper
@DS("oracle")
public interface McPffChrgLogDtlMapper extends BaseMapper<McPffChrgLogDtlPO> {

//    @Insert("INSERT INTO MSSAPP.MC_PFF_CHRG_LOG_DTL(PFF_CHRG_LOG_DTL_ID, PFF_CHRG_LOG_ID, TXN_DATE, CHRG_AMT, CHRWV_AMT, CHRWV_MTD_CDE, IS_WAIVE, PROFO_VAL, CHRG_GRP_VID, REC_VER_NUM, INIT_TIME, LAST_UPD_TIME, LAST_UPD_BY, TAG_SEQ)" +
//            "VALUES (#{pffChrgLogDtlId},#{pffChrgLogId},#{txnDate},#{chrgAmt},#{chrwvAmt},#{chrwvMtdCde},#{isWaive},#{profoVal},#{chrgGrpVid},#{recVerNum}," +
//            "#{initTime},#{lastUpdTime},#{lastUpdBy},#{tagSeq})")
//    @Options(useGeneratedKeys = true,keyProperty = "pffChrgLogDtlId")
//    int insert(McPffChrgLogDtlPO log);

    int batchInsert(@Param("list") List<McPffChrgLogDtlPO> list);

}
