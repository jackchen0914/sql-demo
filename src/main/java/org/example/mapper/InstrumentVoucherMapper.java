package org.example.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.InstrumentVoucherPO;
import org.example.pojo.PortfoliofeeDailyPO;

import java.util.List;

@Mapper
@DS("master")
public interface InstrumentVoucherMapper extends BaseMapper<InstrumentVoucherPO> {

    @Select("SELECT VoucherNo, VoucherDate, ReferenceNo, Clnt, ClntAcctType, Instrument, Market, Depot, Location, Quantity, Comment, Charge, Userid, Status, [TimeStamp], TxnType, ValueDate, ConfirmationDate, CancelDate, ManualInput, Remark, UserIDTime, Approver, ApprovalTime, StatusFlag\n" +
            "FROM dbo.InstrumentVoucher WHERE SUBSTRING(StatusFlag,8,1) != 'Y' ORDER BY VoucherNo DESC OFFSET #{offset} ROWS FETCH NEXT #{limit} ROWS ONLY")
//            "FROM dbo.InstrumentVoucher ORDER BY Market,VoucherNo OFFSET #{offset} ROWS FETCH NEXT #{limit} ROWS ONLY")
    List<InstrumentVoucherPO> selectByPage(@Param("offset") int offset, @Param("limit") int limit);

}
