package com.sgai.pox.admin.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgai.pox.engine.common.core.base.BaseService;
import com.sgai.pox.admin.sys.entity.SysConfig;

/**
 * 系统参数Service
 *
 * @author pox
 */
public interface SysConfigService extends BaseService<SysConfig> {

    /**
     * 分页查询系统参数
     *
     * @param page
     * @param sysConfig
     * @return
     */
    IPage<SysConfig> list(IPage<SysConfig> page, SysConfig sysConfig);

    /**
     * 加载系统参数到redis
     *
     * @param configIds
     */
    void loadSysConfigToRedis(String configIds);

    /**
     * 保存系统参数，并加载进redis缓存
     *
     * @param sysConfig
     */
    void saveSysConfig(SysConfig sysConfig);

    /**
     * 修改系统参数，并加载进redis缓存
     *
     * @param sysConfig
     */
    void updateSysConfig(SysConfig sysConfig);

    /**
     * 删除系统参数，并重新加载redis缓存
     *
     * @param ids
     */
    void deleteSysConfig(String ids);
}
