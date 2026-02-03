package org.example.pojo.dtos;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CashVoucherWithRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer voucherNo;

    private LocalDateTime voucherDate;

    private LocalDateTime confirmationDate;

    private String referenceNo;

    private String clnt;

    private String clntAcctType;

    private String market;

    private String bankNo;

    private BigDecimal amount;

    private String ccy;

    private String comment;

    private String userid;

    private String status;

    private byte[] timeStamp;

    private String charge;

    private LocalDateTime valueDate;

    private String txnType;

    private String printChq;

    private LocalDateTime cancelDate;

    private Integer chequeNo;

    private LocalDateTime chequeDate;

    private String manualInput;

    private String remark;

    private String gLCode;

    private String bankGLCode;

    private String authorizedUserID1;

    private String authorizedUserID2;

    private String sendOutUserID;

    private String cancelSendOutUserID;

    private String verifiedStatus;

    private String statusFlag;

    private String source;

    private String reason;

    private Integer batchNo;

    private LocalDateTime exportTime;

    private Integer exportCount;

    private String bankAccount;

    private String accountName;

    private String accountNumber;

    private String accountAddress;

    private String accountContactNo;

    private String accountMobileNo;

    private String chargeChannel;

    private String bankVRemark;

    private String paymentPurposeDetail;

    private LocalDateTime inputDate;

    private String voucherType;

    private LocalDateTime userIDTime;

    private String approver;

    private LocalDateTime approvalTime;

    private String sPISource;

    private String sPIBankAccount;

    private String relationship;

    private String mode;

    private String withdrawMode;

    private String benfName;

    private String txnTypActnCdeValue;
    private Long txnTypIdValue;
    private BigDecimal baseCcyEquAmtValue;
    private Integer index;

}
