package com.sgai.pox.admin.sys.provider;

import com.sgai.pox.engine.core.constant.SysConstants;
import com.sgai.pox.engine.core.permission.provider.AbstractDataPermissionProvider;
import com.sgai.pox.engine.core.permission.wrapper.PermissionWrapper;
import com.sgai.pox.engine.core.session.AcctSession;
import com.sgai.pox.engine.core.session.AssertContext;
import com.sgai.pox.engine.core.util.CommonUtil;
import com.sgai.pox.engine.core.validator.xss.SqlFilter;
import lombok.Getter;
import lombok.Setter;

/**
 * 机构权限
 *
 * @author pox
 */
@Getter
@Setter
public class OrgDataPermissionProvider extends AbstractDataPermissionProvider {
    public static final String TYPE_1 = "1";
    public static final String TYPE_2 = "2";
    public static final String TYPE_3 = "3";
    /**
     * 别名
     */
    private String alias;

    /**
     * 1-机构权限，查询自己及下辖机构的数据
     * <p>
     * 2-只查询当前机构
     * <p>
     * 3-只查询下辖机构不包括自己
     * <p>
     * others-如有需要可添加用户分管机构表等其他场景
     */
    private String type;

    @Override
    public PermissionWrapper wrap(PermissionWrapper permissionWrapper) {
        AcctSession acctSession = AssertContext.get();
        CommonUtil.isEmptyMapWithKey(acctSession.getAdditionalInformation(), SysConstants.ORG_LEVEL_CODE,
                "orgLevelCode is null");
        String orgId = acctSession.getOrgId();
        String orgLevelCode = acctSession.getAdditionalInformation().get(SysConstants.ORG_LEVEL_CODE).toString();
        // 别名，默认 o
        String alias = CommonUtil.isEmptyDefault(this.alias, "o");
        // 防止sql注入
        SqlFilter.sqlInject(alias);
        // 机构数据权限类型，默认1
        String type = CommonUtil.isEmptyDefault(this.type, "1");

        // 机构权限，查询自己及下辖机构的数据
        if (TYPE_1.equals(type)) {
            permissionWrapper.likeRight(alias, "ORG_LEVEL_CODE", orgLevelCode);
        }
        // 只查询当前机构
        else if (TYPE_2.equals(type)) {
            permissionWrapper.eq(alias, "ORG_ID", orgId);
        }
        // 查询下辖机构不包括自己
        else if (TYPE_3.equals(type)) {
            permissionWrapper.likeRight(alias, "ORG_LEVEL_CODE", orgLevelCode).ne(alias, "ORG_ID", orgId);
        }
        return permissionWrapper;
    }
}
