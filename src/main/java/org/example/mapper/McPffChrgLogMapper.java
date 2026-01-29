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
public interface McPffChrgLogMapper extends BaseMapper<McPffChrgLogPO> {

//    @Insert("INSERT INTO MSSAPP.MC_PFF_CHRG_LOG(PFF_CHRG_LOG_ID, TXN_TYP_ID, SPCAL_CHRG_GRP_CDE, YEAR_MTH, FROM_DATE, TO_DATE, CMPNY_CDE, MRKT_GRP_ID, MRKT_ID, AC_ID, CHRG_CCY_CDE, ACRU_CHRG_AMT, ACRU_CHRWV_DR_AMT, ACRU_CHRWV_WITHOUT_POST_AMT, IS_SHOW_STMT, IS_POST_LEDG, POST_LEDG_DATE, AC_FUND_TXN_RID, REV_AC_FUND_TXN_RID, REC_VER_NUM, INIT_TIME, LAST_UPD_TIME, LAST_UPD_BY, TAG_SEQ)" +
//            "VALUES (#{logId},#{txnTypId},#{spcalChrgGrpCde},#{yearMth},#{fromDate},#{toDate},#{cmpnyCde},#{mrktGrpId},#{mrktId},#{acId}," +
//            "#{chrgCcyCde},#{acruChrgAmt},#{acruChrwvDrAmt},#{acruChrwvWithoutPostAmt},#{isShowStmt},#{isPostLedg},#{postLedgDate},#{acFundTxnRid}," +
//            "#{revAcFundTxnRid},#{recVerNum},#{initTime},#{lastUpdTime},#{lastUpdBy},#{tagSeq},)")
//    @Options(useGeneratedKeys = true,keyProperty = "pffChrgLogId")
//    int insert(McPffChrgLogPO log);

    int batchInsert(@Param("list") List<McPffChrgLogPO> list);
}
