package org.example.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2025-12-25
 */
@Getter
@Setter
@ToString
@TableName("CashTransfer")
public class CashTransferPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("SeqNo")
    private Integer seqNo;

    @TableField("TransferDate")
    private LocalDateTime transferDate;

    @TableField("Status")
    private String status;

    @TableField("AllClient")
    private String allClient;

    @TableField("ClntCode")
    private String clntCode;

    @TableField("AcctType")
    private String acctType;

    @TableField("MarketFrom")
    private String marketFrom;

    @TableField("MarketTo")
    private String marketTo;

    @TableField("CCYFrom")
    private String cCYFrom;

    @TableField("CCYTo")
    private String cCYTo;

    @TableField("BankFrom")
    private String bankFrom;

    @TableField("BankTo")
    private String bankTo;

    @TableField("AmountFrom")
    private BigDecimal amountFrom;

    @TableField("AmountTo")
    private BigDecimal amountTo;

    @TableField("FXrate")
    private BigDecimal fXrate;

    @TableField("Remarks")
    private String remarks;

    @TableField("UserIDSave")
    private String userIDSave;

    @TableField("UserIDCancel")
    private String userIDCancel;

    @TableField("TimeStamp")
    private byte[] timeStamp;

    @TableField("SyncRef")
    private String syncRef;

    @TableField("ExternalSeqNo")
    private String externalSeqNo;

    @TableField("BuyInXRate")
    private BigDecimal buyInXRate;

    @TableField("SellOutXRate")
    private BigDecimal sellOutXRate;

    @TableField("BatchID")
    private String batchID;
}
