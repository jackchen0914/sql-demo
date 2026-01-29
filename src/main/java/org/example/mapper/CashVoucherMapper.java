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

   //select ClntCode,Name from dbo._Clnt order by ClntCode OFFSET 0 ROWS FETCH NEXT 1000 ROW ONLY;
   @Select("SELECT VoucherNo, VoucherDate, ConfirmationDate, ReferenceNo, Clnt, ClntAcctType, Market, BankNo, Amount, CCY, Comment, Userid, Status, [TimeStamp], Charge, ValueDate, TxnType, PrintChq, CancelDate, ChequeNo, ChequeDate, ManualInput, Remark, GLCode, BankGLCode, AuthorizedUserID1, AuthorizedUserID2, SendOutUserID, CancelSendOutUserID, VerifiedStatus, StatusFlag, [Source], Reason, BatchNo, ExportTime, ExportCount, BankAccount, AccountName, AccountNumber, AccountAddress, AccountContactNo, AccountMobileNo, ChargeChannel, BankVRemark, PaymentPurposeDetail, InputDate, VoucherType, UserIDTime, Approver, ApprovalTime, SPISource, SPIBankAccount\n" +
           "FROM dbo.CashVoucher order by VoucherNo OFFSET 0 ROWS FETCH NEXT 1000 ROW ONLY")
   List<CashVoucherPO> selectAll();

   List<CashVoucherWithRequestDTO> selectCashVoucherWithRequest();

}
