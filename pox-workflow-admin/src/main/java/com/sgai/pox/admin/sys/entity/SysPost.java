package com.sgai.pox.admin.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sgai.pox.engine.core.base.BaseEntity;
import com.sgai.pox.engine.core.validator.constraints.LengthForUtf8;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * 【岗位】实体类
 *
 * @author pox
 */
@Data
@TableName("T_SYS_POST")
public class SysPost extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 岗位ID
     */
    @TableId
    @NotNull
    @LengthForUtf8(max = 32)
    private String postId;

    /**
     * 岗位名称
     */
    @NotNull
    @LengthForUtf8(max = 255)
    private String postName;

    /**
     * 组织编码
     */
    @LengthForUtf8(max = 255)
    private String orgId;

    /**
     * 排序号
     */
    @Max(9999)
    private Integer sortNo;

    /**
     * 备注
     */
    @LengthForUtf8(max = 255)
    private String remark;

}