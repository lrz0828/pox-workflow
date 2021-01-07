package com.sgai.pox.engine.wapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sgai.pox.engine.common.ResponseFactory;

/**
 * @author pox
 * @date 2021年01月04日
 */
@Component
public class ProcDefListWrapper implements IListWrapper {

    @Autowired
    private ResponseFactory responseFactory;

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public List execute(List list) {
        return responseFactory.createProcessDefinitionResponseList(list);
    }
}
