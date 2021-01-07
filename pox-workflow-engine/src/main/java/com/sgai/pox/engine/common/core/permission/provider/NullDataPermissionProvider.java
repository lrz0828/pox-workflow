package com.sgai.pox.engine.common.core.permission.provider;

import com.sgai.pox.engine.common.core.permission.wrapper.PermissionWrapper;

/**
 * 空的DataPermissionProvider，可以用于测试
 *
 * @author pox
 */
public class NullDataPermissionProvider extends AbstractDataPermissionProvider {
    @Override
    public PermissionWrapper wrap(PermissionWrapper permissionWrapper) {
        return permissionWrapper;
    }
}
