package com.sgai.pox.engine.common.wapper;

import java.util.List;

/**
 * @author pox
 * @date 2021年01月04日
 */
public interface IListWrapper {
    /**
     * 执行List转换封装
     *
     * @param list
     * @return
     */
    @SuppressWarnings("rawtypes")
    public List execute(List list);
}