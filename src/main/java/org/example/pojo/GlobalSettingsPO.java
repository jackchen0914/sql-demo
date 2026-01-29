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
 * @since 2026-01-28
 */
@Getter
@Setter
@ToString
@TableName("GlobalSettings")
public class GlobalSettingsPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("SettingName")
    private String settingName;

    @TableField("Setting")
    private String setting;

    @TableField("timestamp")
    private byte[] timestamp;
}
