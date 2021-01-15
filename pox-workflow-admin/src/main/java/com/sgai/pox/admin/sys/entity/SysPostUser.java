package com.sgai.pox.admin.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sgai.pox.engine.core.base.BaseEntity;
import com.sgai.pox.engine.core.validator.constraints.LengthForUtf8;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 【岗位和用户关系】实体类
 *
 * @author pox
 */
@Data
@TableName("T_SYS_POST_USER")
public class SysPostUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * UUID
     */
    @TableId(type = IdType.ASSIGN_UUID)
    @NotNull
    @LengthForUtf8(max = 32)
    private String postUserId;

    /**
     * 岗位ID
     */
    @NotNull
    @LengthForUtf8(max = 32)
    private String postId;

    /**
     * 用户ID
     */
    @NotNull
    @LengthForUtf8(max = 32)
    private String userId;

    @TableField(exist = false)
    private String userName;

    public SysPostUser() {
    }

    public SysPostUser(String postId, String userId) {
        this.postUserId = postId + '-' + userId;
        this.postId = postId;
        this.userId = userId;
    }
}