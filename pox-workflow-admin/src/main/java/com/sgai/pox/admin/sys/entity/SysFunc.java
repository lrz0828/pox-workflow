package com.sgai.pox.admin.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sgai.pox.engine.core.base.BaseEntity;
import com.sgai.pox.engine.core.validator.constraints.LengthForUtf8;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * 【功能】实体类
 *
 * @author pox
 */
@Data
@TableName("T_SYS_FUNC")
public class SysFunc extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 功能ID
     */
    @TableId
    @NotNull
    @LengthForUtf8(max = 32)
    private String funcId;

    /**
     * 功能名称
     */
    @NotNull
    @LengthForUtf8(max = 100)
    private String funcName;

    /**
     * 菜单ID
     */
    @NotNull
    @LengthForUtf8(max = 32)
    private String menuId;

    /**
     * 授权
     */
    @LengthForUtf8(max = 255)
    private String funcPermissions;

    /**
     * 备注
     */
    @LengthForUtf8(max = 255)
    private String remark;

    /**
     * 排序号
     */
    @Max(9999)
    private Integer sortNo;

}