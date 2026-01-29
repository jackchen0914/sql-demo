package org.example.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author JackChen
 * @since 2025-12-15
 */
@Getter
@Setter
@ToString
@TableName("TransactionTypes")
public class TransactionTypesPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("TransactionTypes")
    private String transactionTypes;

    @TableField("Description")
    private String description;

    @TableField("GLCode")
    private String gLCode;

    @TableField("SignIndicator")
    private String signIndicator;

    @TableField("StkMoney")
    private String stkMoney;

    @TableField("NumValueDate")
    private Integer numValueDate;

    @TableField("Active")
    private String active;

    @TableField("SystemUsed")
    private String systemUsed;

    @TableField("ChequePrint")
    private String chequePrint;

    @TableField("Visible")
    private String visible;

    @TableField("ShortCut")
    private String shortCut;

    @TableField("Category")
    private String category;

    @TableField("TimeStamp")
    private byte[] timeStamp;

    @TableField("SendSMS")
    private String sendSMS;

    @TableField("SyncRef")
    private String syncRef;
}
