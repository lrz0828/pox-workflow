package com.sgai.pox.engine.common.core.base;

import com.sgai.pox.engine.common.core.Result;

public interface LogService {
    Result save(LogInfo logInfo, String inner);
}
