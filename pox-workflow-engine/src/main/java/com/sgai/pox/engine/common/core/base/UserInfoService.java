package com.sgai.pox.engine.common.core.base;

import com.sgai.pox.engine.common.core.Result;

public interface UserInfoService {
    Result<UserInfo> info(String userId, String inner);
}
