package com.sgai.pox.admin.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sgai.pox.engine.core.base.Result;
import com.sgai.pox.engine.core.base.BaseController;
import com.sgai.pox.admin.sys.entity.SysRolePermission;
import com.sgai.pox.admin.sys.service.SysRolePermissionService;
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
import java.util.Arrays;

/**
 * 操作权限Controller
 *
 * @author pox
 */
@RestController
@RequestMapping("/sys/rolePermission")
public class SysRolePermissionController extends BaseController {
    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    /**
     * 自定义查询列表
     *
     * @param sysRolePermission
     * @param current
     * @param size
     * @return
     */
    @PoxPreAuthorize("@elp.single('sys:rolePermission:list')")
    @GetMapping(value = "/list")
    public Result list(SysRolePermission sysRolePermission, @RequestParam Integer current, @RequestParam Integer size) {
        IPage<SysRolePermission> pageList = sysRolePermissionService.list(new Page<SysRolePermission>(current, size),
                sysRolePermission);
        return Result.ok(pageList);
    }

    @PoxPreAuthorize("@elp.single('sys:rolePermission:list')")
    @GetMapping(value = "/queryById")
    public Result queryById(@RequestParam String id) {
        SysRolePermission sysRolePermission = sysRolePermissionService.getById(id);
        return Result.ok(sysRolePermission);
    }

    /**
     * @param sysRolePermission
     * @return
     * @功能：新增
     */
    @PoxPreAuthorize("@elp.single('sys:rolePermission:save')")
    @PostMapping(value = "/save")
    public Result save(@Valid @RequestBody SysRolePermission sysRolePermission) {
        sysRolePermissionService.save(sysRolePermission);
        return Result.ok();
    }

    /**
     * @param sysRolePermission
     * @return
     * @功能：修改
     */
    @PoxPreAuthorize("@elp.single('sys:rolePermission:update')")
    @PutMapping(value = "/update")
    public Result update(@Valid @RequestBody SysRolePermission sysRolePermission) {
        sysRolePermissionService.updateById(sysRolePermission);
        return Result.ok();
    }

    /**
     * @param ids
     * @return
     * @功能：批量删除
     */
    @PoxPreAuthorize("@elp.single('sys:rolePermission:delete')")
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam String ids) {
        if (ids == null || ids.trim().length() == 0) {
            return Result.error("ids can't be empty");
        }
        String[] idsArr = ids.split(",");
        if (idsArr.length > 1) {
            sysRolePermissionService.removeByIds(Arrays.asList(idsArr));
        } else {
            sysRolePermissionService.removeById(idsArr[0]);
        }
        return Result.ok();
    }
}
