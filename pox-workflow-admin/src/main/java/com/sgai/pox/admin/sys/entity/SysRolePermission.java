package com.sgai.pox.admin.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sgai.pox.engine.common.core.base.BaseEntity;
import com.sgai.pox.engine.common.core.validator.constraints.LengthForUtf8;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 【操作权限】实体类
 *
 * @author pox
 */
@Data
@TableName("T_SYS_ROLE_PERMISSION")
public class SysRolePermission extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 操作权限ID
     */
    @TableId(type = IdType.ASSIGN_UUID)
    @NotNull
    @LengthForUtf8(max = 32)
    private String rolePermissionId;

    /**
     * 角色ID
     */
    @NotNull
    @LengthForUtf8(max = 32)
    private String roleId;

    /**
     * 权限类型
     */
    @NotNull
    @LengthForUtf8(max = 1)
    private String permissionType;

    /**
     * 菜单或功能ID
     */
    @NotNull
    @LengthForUtf8(max = 32)
    private String menuOrFuncId;

    @TableField(exist = false)
    private String menuOrFuncName;

    public SysRolePermission() {
    }

    public SysRolePermission(String permissionType, String menuOrFuncId, String menuOrFuncName) {
        this.permissionType = permissionType;
        this.menuOrFuncId = menuOrFuncId;
        this.menuOrFuncName = menuOrFuncName;
    }
}