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
 * @since 2025-12-19
 */
@Getter
@Setter
@ToString
@TableName("InstrumentVoucher")
public class InstrumentVoucherPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("VoucherNo")
    private Integer voucherNo;

    @TableField("VoucherDate")
    private LocalDateTime voucherDate;

    @TableField("ReferenceNo")
    private String referenceNo;

    @TableField("Clnt")
    private String clnt;

    @TableField("ClntAcctType")
    private String clntAcctType;

    @TableField("Instrument")
    private String instrument;

//    @TableId("Market")
    @TableField("Market")
    private String market;

    @TableField("Depot")
    private String depot;

    @TableField("Location")
    private String location;

    @TableField("Quantity")
    private BigDecimal quantity;

    @TableField("Comment")
    private String comment;

    @TableField("Charge")
    private String charge;

    @TableField("Userid")
    private String userid;

    @TableField("Status")
    private String status;

    @TableField("TimeStamp")
    private byte[] timeStamp;

    @TableField("TxnType")
    private String txnType;

    @TableField("ValueDate")
    private LocalDateTime valueDate;

    @TableField("ConfirmationDate")
    private LocalDateTime confirmationDate;

    @TableField("CancelDate")
    private LocalDateTime cancelDate;

    @TableField("ManualInput")
    private String manualInput;

    @TableField("Remark")
    private String remark;

    @TableField("UserIDTime")
    private LocalDateTime userIDTime;

    @TableField("Approver")
    private String approver;

    @TableField("ApprovalTime")
    private LocalDateTime approvalTime;

    @TableField("StatusFlag")
    private String statusFlag;

    @TableField(exist = false)
    private String txnTypActnCdeValue;
    @TableField(exist = false)
    private String stkStatCdeValue;
    @TableField(exist = false)
    private Long txnTypIdValue;
}
