package org.example.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author JackChen
 * @since 2025-12-12
 */
@Getter
@Setter
@ToString
@TableName("CashVoucher")
public class CashVoucherPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("VoucherNo")
    private Integer voucherNo;

    @TableField("VoucherDate")
    private LocalDateTime voucherDate;

    @TableField("ConfirmationDate")
    private LocalDateTime confirmationDate;

    @TableField("ReferenceNo")
    private String referenceNo;

    @TableField("Clnt")
    private String clnt;

    @TableField("ClntAcctType")
    private String clntAcctType;

//    @TableId("Market")
    @TableField("Market")
    private String market;

    @TableField("BankNo")
    private String bankNo;

    @TableField("Amount")
    private BigDecimal amount;

    @TableField("CCY")
    private String ccy;

    @TableField("Comment")
    private String comment;

    @TableField("Userid")
    private String userid;

    @TableField("Status")
    private String status;

    @TableField("TimeStamp")
    private byte[] timeStamp;

    @TableField("Charge")
    private String charge;

    @TableField("ValueDate")
    private LocalDateTime valueDate;

    @TableField("TxnType")
    private String txnType;

    @TableField("PrintChq")
    private String printChq;

    @TableField("CancelDate")
    private LocalDateTime cancelDate;

    @TableField("ChequeNo")
    private Integer chequeNo;

    @TableField("ChequeDate")
    private LocalDateTime chequeDate;

    @TableField("ManualInput")
    private String manualInput;

    @TableField("Remark")
    private String remark;

    @TableField("GLCode")
    private String gLCode;

    @TableField("BankGLCode")
    private String bankGLCode;

    @TableField("AuthorizedUserID1")
    private String authorizedUserID1;

    @TableField("AuthorizedUserID2")
    private String authorizedUserID2;

    @TableField("SendOutUserID")
    private String sendOutUserID;

    @TableField("CancelSendOutUserID")
    private String cancelSendOutUserID;

    @TableField("VerifiedStatus")
    private String verifiedStatus;

    @TableField("StatusFlag")
    private String statusFlag;

    @TableField("Source")
    private String source;

    @TableField("Reason")
    private String reason;

    @TableField("BatchNo")
    private Integer batchNo;

    @TableField("ExportTime")
    private LocalDateTime exportTime;

    @TableField("ExportCount")
    private Integer exportCount;

    @TableField("BankAccount")
    private String bankAccount;

    @TableField("AccountName")
    private String accountName;

    @TableField("AccountNumber")
    private String accountNumber;

    @TableField("AccountAddress")
    private String accountAddress;

    @TableField("AccountContactNo")
    private String accountContactNo;

    @TableField("AccountMobileNo")
    private String accountMobileNo;

    @TableField("ChargeChannel")
    private String chargeChannel;

    @TableField("BankVRemark")
    private String bankVRemark;

    @TableField("PaymentPurposeDetail")
    private String paymentPurposeDetail;

    @TableField("InputDate")
    private LocalDateTime inputDate;

    @TableField("VoucherType")
    private String voucherType;

    @TableField("UserIDTime")
    private LocalDateTime userIDTime;

    @TableField("Approver")
    private String approver;

    @TableField("ApprovalTime")
    private LocalDateTime approvalTime;

    @TableField("SPISource")
    private String sPISource;

    @TableField("SPIBankAccount")
    private String sPIBankAccount;
}
