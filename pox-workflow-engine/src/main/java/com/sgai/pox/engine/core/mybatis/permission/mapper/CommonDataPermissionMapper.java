package com.sgai.pox.engine.core.mybatis.permission.mapper;



import com.sgai.pox.engine.core.mybatis.permission.CommonDataPermissionVO;

import java.util.List;

/**
 * @author pox
 */
public interface CommonDataPermissionMapper {
    /**
     * 查询数据权限信息
     *
     * @param commonDataPermissionVO
     * @return
     */
    List<CommonDataPermissionVO> selectDataPermissions(CommonDataPermissionVO commonDataPermissionVO);
}
