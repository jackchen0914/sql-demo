package org.example.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.CashVoucherPO;
import org.example.pojo.CashVoucherRequestPO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JackChen
 * @since 2025-12-12
 */
@DS("master")
@Mapper
public interface CashVoucherRequestMapper extends BaseMapper<CashVoucherRequestPO> {

    @Select("SELECT TOP 5 VoucherNo, Market, BankName, DepositDate, Mode, TranClntCode, TranSource, IssuerName, IDNo4, Country4, TransferorName, IDNo5, Country5, ThirdParty, Relationship, OtherRelationship, Reason, OtherReason, ThirdPartyW, BenfName, BenfIDNo, CountryW, RelationshipW, OtherRelationshipW, ReasonW, OtherReasonW, WithdrawMode\n" +
            "FROM dbo.CashVoucherRequest")
    List<CashVoucherRequestPO> selectTop5();

}
