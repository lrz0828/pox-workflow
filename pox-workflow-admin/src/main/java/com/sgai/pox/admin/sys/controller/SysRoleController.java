package com.sgai.pox.admin.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sgai.pox.engine.core.base.Result;
import com.sgai.pox.engine.core.base.BaseController;
import com.sgai.pox.admin.sys.entity.SysRole;
import com.sgai.pox.admin.sys.entity.SysRolePermission;
import com.sgai.pox.admin.sys.entity.SysRoleUser;
import com.sgai.pox.admin.sys.entity.SysUser;
import com.sgai.pox.admin.sys.service.SysRoleService;
import com.sgai.pox.engine.core.log.annotation.Log;
import org.springframework.beans.factory.annotation.Autowired;
import com.sgai.pox.engine.core.annotation.PoxPreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 角色Controller
 *
 * @author pox
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends BaseController {
    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 自定义查询列表
     *
     * @param sysRole
     * @param current
     * @param size
     * @return
     */
    @PoxPreAuthorize("@elp.single('sys:role:list')")
    @GetMapping(value = "/list")
    public Result list(SysRole sysRole, @RequestParam Integer current, @RequestParam Integer size) {
        IPage<SysRole> pageList = sysRoleService.list(new Page<SysRole>(current, size), sysRole);
        return Result.ok(pageList);
    }

    @PoxPreAuthorize("@elp.single('sys:role:list')")
    @GetMapping(value = "/queryById")
    public Result queryById(@RequestParam String id) {
        SysRole sysRole = sysRoleService.getById(id);
        return Result.ok(sysRole);
    }

    /**
     * @param sysRole
     * @return
     * @功能：新增
     */
    @Log(value = "新增角色")
    @PoxPreAuthorize("@elp.single('sys:role:save')")
    @PostMapping(value = "/save")
    public Result save(@Valid @RequestBody SysRole sysRole) {
        sysRoleService.save(sysRole);
        return Result.ok();
    }

    /**
     * @param sysRole
     * @return
     * @功能：修改
     */
    @Log(value = "修改角色")
    @PoxPreAuthorize("@elp.single('sys:role:update')")
    @PutMapping(value = "/update")
    public Result update(@Valid @RequestBody SysRole sysRole) {
        sysRoleService.updateById(sysRole);
        return Result.ok();
    }

    /**
     * @param ids
     * @return
     * @功能：批量删除
     */
    @Log(value = "删除角色")
    @PoxPreAuthorize("@elp.single('sys:role:delete')")
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam String ids) {
        if (ids == null || ids.trim().length() == 0) {
            return Result.error("ids can't be empty");
        }
        this.sysRoleService.delete(ids);
        return Result.ok();
    }

    /**
     * 查询角色权限
     *
     * @param roleId
     * @return
     */
    @PoxPreAuthorize("@elp.single('sys:role:getRolePermissions')")
    @GetMapping(value = "/getRolePermissions")
    public Result getRolePermissions(String roleId) {
        Map<String, Object> data = this.sysRoleService.getRolePermissions(roleId);
        return Result.ok(data);
    }

    /**
     * 保存角色权限
     *
     * @param roleId
     * @param menuOrFuncIds   菜单或者按钮ID
     * @param permissionTypes 权限类型 1-菜单 2-按钮
     * @return
     */
    @Log(value = "保存角色权限")
    @PoxPreAuthorize("@elp.single('sys:role:saveRolePermissions')")
    @PostMapping(value = "/saveRolePermissions")
    public Result saveRolePermissions(@RequestBody SysRolePermission sysRolePermission) {
        this.sysRoleService.saveRolePermissions(sysRolePermission.getRoleId(), sysRolePermission.getMenuOrFuncId(),
                sysRolePermission.getPermissionType());
        return Result.ok();
    }

    /**
     * 获取角色用户
     *
     * @param roleId
     * @return
     */
    @PoxPreAuthorize("@elp.single('sys:role:getRoleUser')")
    @GetMapping(value = "/getRoleUser")
    public Result getRoleUser(SysRoleUser sysRoleUser, @RequestParam Integer current, @RequestParam Integer size) {
        IPage<SysUser> pageList = this.sysRoleService.getRoleUser(new Page<SysUser>(current, size), sysRoleUser);
        return Result.ok(pageList);
    }

    /**
     * 保存角色用户
     *
     * @param sysRoleUser
     * @return
     */
    @Log(value = "保存角色用户")
    @PoxPreAuthorize("@elp.single('sys:role:saveRoleUsers')")
    @PostMapping(value = "/saveRoleUsers")
    public Result saveRoleUsers(@RequestBody SysRoleUser sysRoleUser) {
        this.sysRoleService.saveRoleUsers(sysRoleUser.getRoleId(), sysRoleUser.getUserId());
        return Result.ok();
    }

    /**
     * 删除角色用户
     *
     * @param sysRoleUser
     * @return
     */
    @Log(value = "删除角色用户")
    @PoxPreAuthorize("@elp.single('sys:role:deleteRoleUsers')")
    @DeleteMapping(value = "/deleteRoleUsers")
    public Result deleteRoleUsers(String roleId, String userIds) {
        this.sysRoleService.deleteRoleUsers(roleId, userIds);
        return Result.ok();
    }

    /**
     * 查询所有角色
     *
     * @return
     */
    @PoxPreAuthorize("@elp.single('sys:role:listAll')")
    @GetMapping(value = "/listAll")
    public Result listAll() {
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        List<SysRole> roles = sysRoleService.list(queryWrapper.orderByAsc("sort_no"));
        return Result.ok(roles);
    }
}
