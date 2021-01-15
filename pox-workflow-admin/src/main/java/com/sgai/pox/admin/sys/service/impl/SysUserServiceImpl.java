package com.sgai.pox.admin.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import com.sgai.pox.admin.core.base.UserInfo;
import com.sgai.pox.admin.core.base.UserInfoService;
import com.sgai.pox.admin.sys.entity.SysMenu;
import com.sgai.pox.admin.sys.entity.SysOrg;
import com.sgai.pox.admin.sys.entity.SysPostUser;
import com.sgai.pox.admin.sys.entity.SysRole;
import com.sgai.pox.admin.sys.entity.SysRoleUser;
import com.sgai.pox.admin.sys.entity.SysUser;
import com.sgai.pox.admin.sys.service.SysOrgService;
import com.sgai.pox.admin.sys.service.SysPostUserService;
import com.sgai.pox.admin.sys.service.SysRoleService;
import com.sgai.pox.admin.sys.service.SysRoleUserService;
import com.sgai.pox.admin.sys.service.SysUserService;
import com.sgai.pox.engine.core.base.Result;
import com.sgai.pox.engine.core.base.BaseServiceImpl;
import com.sgai.pox.engine.core.constant.CacheConstants;
import com.sgai.pox.engine.core.constant.Constants;
import com.sgai.pox.engine.core.exception.SysException;
import com.sgai.pox.engine.core.redis.util.RedisUtil;
import com.sgai.pox.engine.core.session.AcctLogin;
import com.sgai.pox.engine.core.session.AcctSession;
import com.sgai.pox.engine.core.session.AssertContext;
import com.sgai.pox.engine.core.session.Authority;
import com.sgai.pox.engine.core.session.CacheKeyConstant;
import com.sgai.pox.engine.core.session.SecurityUser;
import com.sgai.pox.engine.core.util.CommonUtil;
import com.sgai.pox.engine.core.util.CookieUtil;
import com.sgai.pox.engine.core.util.JacksonUtil;
import com.sgai.pox.engine.core.util.PasswordUtil;
import com.sgai.pox.engine.core.util.SpringContextUtils;
import com.sgai.pox.admin.sys.entity.vo.*;
import com.sgai.pox.admin.sys.mapper.SysUserMapper;
import com.sgai.pox.engine.core.util.base64.Base64Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用户Service
 *
 * @author pox
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements SysUserService, UserInfoService {
    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysRoleUserService sysRoleUserService;

    @Autowired
    private SysPostUserService sysPostUserService;

    @Autowired
    private SysOrgService sysOrgService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public IPage<SysUser> list(IPage<SysUser> page, SysUser sysUser) {
        List<SysUser> records = baseMapper.list(page, sysUser);
        if (page == null) {
            page = new Page<SysUser>();
            page.setTotal(records != null ? records.size() : 0L);
        }
        return page.setRecords(records);
    }

    /**
     * 公共选人查询
     *
     * @param page
     * @param sysUser
     * @return
     */
    @Override
    public IPage<SysUser> listSelectUser(IPage<SysUser> page, SysUser sysUser) {
        return page.setRecords(baseMapper.list(page, sysUser));
    }

    @Override
    public List<SysRole> getRoleByUserId(String userId) {
        return baseMapper.getRolesByUserId(userId);
    }

    /**
     * 获取用户信息 ，
     * <p>
     * 若用户变更角色，则roleId不能为空,并且将变更后的roleId更新到T_SYS_USER表中
     *
     * @param userId
     * @param roleId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysUserInfo saveGetUserInfo(String userId, String roleId) {
        SysUserInfo sysUserInfo = info(userId, roleId, true);
        SysUser sysUser = sysUserInfo.getSysUser();
        SysRole sysRole = sysUserInfo.getSysRole();
        // 切换角色获取用户信息，需要更新用户表角色ID
        if (!sysRole.getRoleId().equals(sysUser.getRoleId())) {
            SysUser updateRoleIdUser = new SysUser();
            updateRoleIdUser.setUserId(sysUser.getUserId());
            updateRoleIdUser.setRoleId(roleId);
            updateById(updateRoleIdUser);
        }
        if (roleId != null && !roleId.isEmpty()) {
//            TokenStore tokenStore = SpringContextUtils.getBean(TokenStore.class);
//            OAuth2Authentication authentication = (OAuth2Authentication) SecurityUtils.getAuthentication();
//            SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
//            SecurityUser newSecurityUser = new SecurityUser(sysRole.getRoleId(), sysUser.getOrgId(),
//                    sysUser.getUserName(), securityUser.getAdditionalInformation(), sysUser.getUserId(), "", true,
//                    true, true, true, sysUserInfo.getAuthorities());
//            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
//                    new UsernamePasswordAuthenticationToken(newSecurityUser, authentication.getCredentials(),
//                            newSecurityUser.getAuthorities());
//            usernamePasswordAuthenticationToken.setDetails(authentication.getUserAuthentication().getDetails());
//            OAuth2Authentication newAuthentication = new OAuth2Authentication(authentication.getOAuth2Request(),
//                    usernamePasswordAuthenticationToken);
//            newAuthentication.setDetails(authentication.getDetails());
//            OAuth2AccessToken token = tokenStore.getAccessToken(authentication);
//            if (CommonUtil.isNotEmptyObject(token.getAdditionalInformation())) {
//                token.getAdditionalInformation().put("roleId", roleId);
//            }
//            tokenStore.storeAccessToken(token, newAuthentication);
        }

        return sysUserInfo;
    }

    @Override
    public Result<UserInfo> info(String userId, String inner) {
        SysUserInfo sysUserInfo = info(userId, null, false);
        SysUser sysUser = sysUserInfo.getSysUser();
        UserInfo userInfo = new UserInfo(userId, sysUser.getUserName(), sysUser.getPassword(), sysUser.getOrgId(),
                sysUserInfo.getSysRole().getRoleId(), ImmutableMap.of("orgLevelCode",
                sysUserInfo.getSysOrg().getOrgLevelCode()), sysUserInfo.getAuthorities());
        return Result.ok(userInfo);
    }

    private SysUserInfo info(String userId, String roleId, boolean loadRoutes) {
        SysUser sysUser = getById(userId);
        List<SysRole> sysRoles = getRoleByUserId(sysUser.getUserId());
        if (sysRoles == null || sysRoles.size() == 0) {
            if (!Constants.ADMIN.equals(sysUser.getUserId())) {
                throw new SysException("用户未配置角色权限，请联系管理员授权!");
            }
            SysRole sysRoleAdmin = sysRoleService.getById("admin");
            if (sysRoleAdmin == null) {
                throw new SysException("系统未配置admin角色，请联系管理员!");
            }
            sysRoles.add(sysRoleAdmin);
        }
        // 默认以T_SYS_USER表中的角色登录
        if (CommonUtil.isEmptyStr(roleId)) {
            roleId = sysUser.getRoleId();
        }

        SysRole sysRoleUser = null;
        if (CommonUtil.isEmptyStr(roleId)) {
            roleId = sysRoles.get(0).getRoleId();
        } else {
            for (SysRole sysRole : sysRoles) {
                if (sysRole.getRoleId().equals(roleId)) {
                    sysRoleUser = sysRole;
                    break;
                }
            }
            if (sysRoleUser == null) {
                roleId = sysRoles.get(0).getRoleId();
                sysRoleUser = sysRoles.get(0);
            }
        }

        List<Authority> authorities = loadPermissions(sysUser, roleId);
        SysOrg sysOrg = this.sysOrgService.getById(sysUser.getOrgId());
        List<Route> routes = null;
        if (loadRoutes) {
            routes = this.loadRoutes(sysUser, roleId);
        }
        return new SysUserInfo(sysUser, sysOrg, sysRoleUser, sysRoles, routes, authorities);
    }

    /**
     * @param sysUser
     * @param roleId
     * @return
     */
    @Override
    public List<Authority> loadPermissions(SysUser sysUser, String roleId) {
        String userId = sysUser.getUserId();
        List<SysRolePermissionVO> authList = null;
        if (Constants.ADMIN.equals(userId)) {
            authList = baseMapper.getAdminPermissions();
        } else {
            authList = baseMapper.getRolePermissions(roleId);
        }
        Set<String> permissions = Sets.newHashSet();
        for (SysRolePermissionVO sysAuthOperVO : authList) {
            if ("1".equals(sysAuthOperVO.getPermissionType())) {
                if (CommonUtil.isNotEmptyStr(sysAuthOperVO.getMenuUrl())) {
                    if (CommonUtil.isNotEmptyStr(sysAuthOperVO.getMenuPermissions())) {
                        // 以逗号分隔
                        String[] menuPermissions = sysAuthOperVO.getMenuPermissions().split(",");
                        for (String permission : menuPermissions) {
                            permissions.add(permission);
                        }
                    }
                }
            } else if ("2".equals(sysAuthOperVO.getPermissionType())) {
                if (CommonUtil.isNotEmptyStr(sysAuthOperVO.getFuncPermissions())) {
                    // 以逗号分隔
                    String[] funcPermissions = sysAuthOperVO.getFuncPermissions().split(",");
                    for (String permission : funcPermissions) {
                        permissions.add(permission);
                    }
                }
            }
        }
        List<Authority> authorities = new ArrayList<>(permissions.size());
        for (String permission : permissions) {
            Authority authority = new Authority();
            authority.setAuthority(permission);
            authorities.add(authority);
        }
        return authorities;
    }

    @Override
    public List<Route> loadRoutes(SysUser sysUser, String roleId) {
        String userId = sysUser.getUserId();
        List<SysMenu> menuList = null;
        if (Constants.ADMIN.equals(userId)) {
            menuList = baseMapper.getRoleMenus("");
        } else {
            menuList = baseMapper.getRoleMenus(roleId);
        }
        Map<String, Route> routeMap = new LinkedHashMap<String, Route>();
        for (SysMenu menu : menuList) {
            if (!"1".equals(menu.getIsRoute())) {
                continue;
            }
            Route route = new Route();
            route.setRouteId(menu.getMenuId());
            route.setPath(menu.getMenuUrl());
            route.setComponent(menu.getComponent());
            route.setHidden("1".equals(menu.getHidden()));
            route.setRedirect(menu.getRedirect());
            if (CommonUtil.isNotEmptyStr(menu.getRouteName())) {
                route.setName(menu.getRouteName());
            } else {
                route.setName(urlToRouteName(menu.getMenuUrl()));
            }

            Meta meta = new Meta();
            meta.setTitle(menu.getMenuName());
            meta.setIcon(menu.getMenuIcon());
            meta.setIsCache("1".equals(menu.getIsCache()));
            meta.setAffix("1".equals(menu.getAffix()));
            route.setMeta(meta);

            routeMap.put(route.getRouteId(), route);
            if (CommonUtil.isNotEmptyStr(menu.getParentMenuId()) && routeMap.containsKey(menu.getParentMenuId())) {
                route.setRouteParentId(menu.getParentMenuId());
                Route parentRoute = routeMap.get(menu.getParentMenuId());
                parentRoute.addChildren(route);
            }
        }

        List<Route> result = new ArrayList<Route>();
        routeMap.forEach((k, v) -> {
            if (CommonUtil.isEmptyStr(v.getRouteParentId())) {
                result.add(v);
            }
        });

        return result;
    }

    /**
     * 通过URL生成路由name（去掉URL前缀斜杠，替换内容中的斜杠‘/’为-）
     * <p>
     * 举例： URL = /sys/config RouteName = sysConfig
     *
     * @return
     */
    private String urlToRouteName(String url) {
        if (CommonUtil.isNotEmptyStr(url)) {
            if (url.startsWith(Constants.FORWARD_SLASH)) {
                url = url.substring(1);
            }
            url = url.replace(Constants.FORWARD_SLASH, Constants.CROSSBAR);
            return url;
        } else {
            return null;
        }
    }

    /**
     * 新增用户
     *
     * @param sysUser
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveSysUser(SysUser sysUser) {
        // String salt = PasswordUtil.randomGen(8);
        String defaultPassword = (String) redisUtil.get(CacheConstants.SYS_CONFIG + "defaultPassword", "1");
        // 默认密码
        String password = PasswordUtil.encryptPassword(defaultPassword);
        // sysUser.setSalt(salt);
        sysUser.setPassword(password);
        SysRoleUser sysRoleUser = new SysRoleUser(sysUser.getRoleId(), sysUser.getUserId());
        sysRoleUserService.saveOrUpdate(sysRoleUser);
        boolean result = this.save(sysUser);
        return result;
    }

    /**
     * 修改用户
     *
     * @param sysUser
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSysUser(SysUser sysUser) {
        SysUser sysUserDb = this.getById(sysUser.getUserId());
        if (!sysUserDb.getRoleId().equals(sysUser.getRoleId())) {
            SysRoleUser sysRoleUserDb = new SysRoleUser(sysUserDb.getRoleId(), sysUserDb.getUserId());
            this.sysRoleUserService.removeById(sysRoleUserDb.getRoleUserId());

            SysRoleUser sysRoleUser = new SysRoleUser(sysUser.getRoleId(), sysUser.getUserId());
            sysRoleUserService.saveOrUpdate(sysRoleUser);
        }
        // 密码设置为空，防止密码被恶意修改
        sysUser.setPassword(null);
        // 密码盐设置为空，防止密码被恶意修改
        sysUser.setSalt(null);
        boolean result = this.updateById(sysUser);
        return result;
    }

    /**
     * 删除用户
     *
     * @param ids
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String ids) {
        if (ids == null || ids.trim().length() == 0) {
            throw new SysException("ids can't be empty");
        }
        String[] idsArr = ids.split(",");
        for (int i = 0; i < idsArr.length; i++) {
            if (Constants.ADMIN.equals(idsArr[i])) {
                throw new SysException("不允许删除[admin]用户");
            }
        }
        if (idsArr.length > 1) {
            removeByIds(Arrays.asList(idsArr));
        } else {
            removeById(idsArr[0]);
        }
        sysRoleUserService.remove(new QueryWrapper<SysRoleUser>().in("user_id", (Object[]) idsArr));
        sysPostUserService.remove(new QueryWrapper<SysPostUser>().in("user_id", (Object[]) idsArr));
    }

    /**
     * 修改密码
     *
     * @param sysPasswordForm
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updatePassword(SysPasswordForm sysPasswordForm) {
        String userId = AssertContext.getUserId();
        SysUser sysUser = this.getById(userId);
        if (sysUser == null) {
            throw new SysException("用户不存在");
        }
        if (!PasswordUtil.matchesPassword(sysPasswordForm.getPassword(), sysUser.getPassword())) {
            throw new SysException("旧密码错误");
        }
        // String salt = PasswordUtil.randomGen(8);
        String newPassword = PasswordUtil.encryptPassword(sysPasswordForm.getNewPassword());
        sysUser.setPassword(newPassword);
        // sysUser.setSalt(salt);
        return this.update(sysUser, new QueryWrapper<SysUser>().eq("user_id", userId));
    }

    @Override
    public SecurityUser userLogin(AcctLogin acctLogin) {
        SysUser sysUser = getById(acctLogin.getUserName());
        // 判断密码是否正确
        // TODO: 2021/1/14  
//        if (!PasswordUtil.matchesPassword(acctLogin.getPassword(), sysUser.getPassword())) {
//            throw new SysException("密码错误!");
//        }
        Result<UserInfo> result = this.info(acctLogin.getUserName(), "false");
        UserInfo info = result.getData();

        // 缓存用户信息
        AcctSession acctSession = this.loginSuccess(info, acctLogin);

        // 构造security用户
        return new SecurityUser(info.getRoleId(), info.getOrgId(), info.getUserName(),
                info.getAdditionalInformation(), info.getUserId(), info.getPassword(), true, true, true, true,
                info.getAuthorities() != null ? info.getAuthorities() : Collections.emptyList(), acctSession.getSessionId());
    }


    private AcctSession loginSuccess(UserInfo info, AcctLogin acctLogin) {
        //生成key
        String suffix = acctLogin.getSessionId() + acctLogin.getClientType();
        String sessionId = Base64Util.encode(suffix);
        String key = CacheKeyConstant.ACCT_SESSION + suffix;

        AcctSession acctSession = new AcctSession();
        acctSession.setUserId(info.getUserId());
        acctSession.setOrgId(info.getOrgId());
        acctSession.setAdditionalInformation(info.getAdditionalInformation());
        acctSession.setSessionId(acctLogin.getSessionId());
        acctSession.setUserRealName(info.getUserName());
        //保存到 session 中 60 分钟
        redisUtil.set(key, JacksonUtil.objToStr(acctSession), CacheConstants.TIME_EXPIRE);

        acctSession.setSessionId(sessionId);
        AssertContext.init(acctSession);

        return acctSession;
    }
}
