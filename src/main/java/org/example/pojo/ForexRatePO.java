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
 * @since 2025-12-16
 */
@Getter
@Setter
@ToString
@TableName("ForexRate")
public class ForexRatePO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("CCY")
    private String ccy;

//    @TableId("Date")
    @TableField("Date")
    private LocalDateTime date;

    @TableField("XRate")
    private BigDecimal xRate;

    @TableField("TimeStamp")
    private byte[] timeStamp;

    @TableField("BuyInXRate")
    private BigDecimal buyInXRate;

    @TableField("SellOutXRate")
    private BigDecimal sellOutXRate;
}
