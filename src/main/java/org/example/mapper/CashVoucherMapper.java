package org.example.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.CashVoucherPO;
import org.example.pojo.dtos.CashVoucherWithRequestDTO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JackChen
 * @since 2025-12-12
 */
@Mapper
@DS("master")
public interface CashVoucherMapper extends BaseMapper<CashVoucherPO> {

   @Select("SELECT TOP 5 VoucherNo, VoucherDate, ConfirmationDate, ReferenceNo, Clnt, ClntAcctType, Market, BankNo, Amount, CCY, Comment, Userid, Status, [TimeStamp], Charge, ValueDate, TxnType, PrintChq, CancelDate, ChequeNo, ChequeDate, ManualInput, Remark, GLCode, BankGLCode, AuthorizedUserID1, AuthorizedUserID2, SendOutUserID, CancelSendOutUserID, VerifiedStatus, StatusFlag, [Source], Reason, BatchNo, ExportTime, ExportCount, BankAccount, AccountName, AccountNumber, AccountAddress, AccountContactNo, AccountMobileNo, ChargeChannel, BankVRemark, PaymentPurposeDetail, InputDate, VoucherType, UserIDTime, Approver, ApprovalTime, SPISource, SPIBankAccount\n" +
           "FROM dbo.CashVoucher")
   List<CashVoucherPO> selectTop5();

   @Select("select InputDate,Clnt ,CCY ,TxnType ,VoucherNo ,Amount ,ValueDate ,StatusFlag ,ManualInput ,VoucherDate ,Userid ,Remark ,[Source] ,AccountName ,AccountNumber ,Status ,Charge ,CancelDate ,ConfirmationDate ,ApprovalTime ,Market  from CashVoucher where SUBSTRING(StatusFlag ,8,1) = 'Y' order by VoucherNo,Market OFFSET #{offset} ROWS FETCH NEXT #{limit} ROW ONLY")
   List<CashVoucherPO> selectByPage(@Param("offset") int offset, @Param("limit") int limit);

   List<CashVoucherWithRequestDTO> selectCashVoucherWithRequest(@Param("offset") int offset, @Param("limit") int limit);

}
