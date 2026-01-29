package org.example.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
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
@TableName("CashVoucherRequest")
public class CashVoucherRequestPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("VoucherNo")
    private Integer voucherNo;

    @TableField("Market")
//    @TableId("Market")
    private String market;

    @TableField("BankName")
    private String bankName;

    @TableField("DepositDate")
    private LocalDateTime depositDate;

    @TableField("Mode")
    private String mode;

    @TableField("TranClntCode")
    private String tranClntCode;

    @TableField("TranSource")
    private String tranSource;

    @TableField("IssuerName")
    private String issuerName;

    @TableField("IDNo4")
    private String iDNo4;

    @TableField("Country4")
    private String country4;

    @TableField("TransferorName")
    private String transferorName;

    @TableField("IDNo5")
    private String iDNo5;

    @TableField("Country5")
    private String country5;

    @TableField("ThirdParty")
    private String thirdParty;

    @TableField("Relationship")
    private String relationship;

    @TableField("OtherRelationship")
    private String otherRelationship;

    @TableField("Reason")
    private String reason;

    @TableField("OtherReason")
    private String otherReason;

    @TableField("ThirdPartyW")
    private String thirdPartyW;

    @TableField("BenfName")
    private String benfName;

    @TableField("BenfIDNo")
    private String benfIDNo;

    @TableField("CountryW")
    private String countryW;

    @TableField("RelationshipW")
    private String relationshipW;

    @TableField("OtherRelationshipW")
    private String otherRelationshipW;

    @TableField("ReasonW")
    private String reasonW;

    @TableField("OtherReasonW")
    private String otherReasonW;

    @TableField("WithdrawMode")
    private String withdrawMode;
}
