package com.sgai.pox.engine.common.wapper;

import com.sgai.pox.engine.common.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author pox
 * @date 2021年01月04日
 */
@Component
public class ProcInsListWrapper implements IListWrapper {

    @Autowired
    private ResponseFactory responseFactory;

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public List execute(List list) {
        return responseFactory.createHistoricProcessInstanceResponseList(list);
    }
}
