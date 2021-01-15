package com.sgai.pox.engine.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sgai.pox.engine.core.base.BaseEntity;
import com.sgai.pox.engine.core.validator.constraints.LengthForUtf8;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author pox
 * @date 2021年01月04日
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