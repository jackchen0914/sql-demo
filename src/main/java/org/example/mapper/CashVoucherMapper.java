package org.example.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.CashVoucherPO;
import org.example.pojo.dtos.CashVoucherWithRequestDTO;

import java.time.LocalDateTime;
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

   @Select("select InputDate,Clnt ,CCY ,TxnType ,VoucherNo ,Amount ,ValueDate ,StatusFlag ,ManualInput ,VoucherDate ,Userid ,Remark ,[Source] ,AccountName ,AccountNumber ,Status ,Charge ,CancelDate ,ConfirmationDate ,ApprovalTime ,Market  from CashVoucher where SUBSTRING(StatusFlag ,8,1) = 'Y' and amount < 0 order by VoucherNo,Market OFFSET #{offset} ROWS FETCH NEXT #{limit} ROW ONLY")
   List<CashVoucherPO> selectStatusFlagIsYByPage(@Param("offset") int offset, @Param("limit") int limit);

   List<CashVoucherWithRequestDTO> selectCashVoucherWithRequest(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 查询 CashVoucher 表中最早的 VoucherDate（升序第一条）
     */
    LocalDateTime selectMinVoucherDate();

    /**
     * 查询 CashVoucher 表中最晚的 VoucherDate（降序第一条）
     */
    LocalDateTime selectMaxVoucherDate();

    /**
     * 按年份区间分页查询，用于多线程按年处理
     *
     * @param startTime 年初时间
     * @param endTime   年末时间
     * @param offset    分页偏移
     * @param limit     每页大小
     */
    List<CashVoucherWithRequestDTO> selectCashVoucherByYearPage(
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime,
            @Param("offset") int offset,
            @Param("limit") int limit
    );

    /**
     * 查询指定年份区间内的记录总数，用于计算总批数
     */
    long countCashVoucherByYear(
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime
    );
}
