package com.sgai.pox.engine.entity;

import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sgai.pox.engine.common.core.base.BaseEntity;
import com.sgai.pox.engine.common.core.validator.constraints.LengthForUtf8;

import lombok.Data;

/**
 * @author pox
 * @date 2020年3月23日
 */
@Data
@TableName("T_FLOWABLE_FORM")
public class FlowableForm extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId
    @NotNull
    @LengthForUtf8(max = 100)
    private String formKey;

    @NotNull
    @LengthForUtf8(max = 100)
    private String formName;

    private String formJson;
}