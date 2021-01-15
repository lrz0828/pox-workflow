package com.sgai.pox.engine.core.validator.group;

import javax.validation.GroupSequence;

/**
 * 定义校验顺序，如果AddGroup组失败，则UpdateGroup组不会再校验
 *
 * @author pox
 * @date 2021年01月04日
 */
@GroupSequence({AddGroup.class, UpdateGroup.class})
public interface Group {

}
