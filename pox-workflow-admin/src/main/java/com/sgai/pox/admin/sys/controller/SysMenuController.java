package com.sgai.pox.admin.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sgai.pox.engine.core.base.Result;
import com.sgai.pox.engine.core.base.BaseController;
import com.sgai.pox.engine.core.log.annotation.Log;
import com.sgai.pox.admin.sys.entity.SysMenu;
import com.sgai.pox.admin.sys.entity.vo.ElTree;
import com.sgai.pox.admin.sys.service.SysMenuService;
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

/**
 * 菜单Controller
 *
 * @author pox
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends BaseController {
    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 自定义查询列表
     *
     * @param sysMenu
     * @param current
     * @param size
     * @return
     */
    @PoxPreAuthorize("@elp.single('sys:menu:list')")
    @GetMapping(value = "/list")
    public Result list(SysMenu sysMenu, @RequestParam Integer current, @RequestParam Integer size) {
        IPage<SysMenu> pageList = sysMenuService.list(new Page<SysMenu>(current, size), sysMenu);
        return Result.ok(pageList);
    }

    @PoxPreAuthorize("@elp.single('sys:menu:list')")
    @GetMapping(value = "/queryById")
    public Result queryById(@RequestParam String id) {
        SysMenu sysMenu = sysMenuService.getById(id);
        return Result.ok(sysMenu);
    }

    /**
     * @param sysMenu
     * @return
     * @功能：新增
     */
    @Log(value = "新增功能菜单")
    @PoxPreAuthorize("@elp.single('sys:menu:save')")
    @PostMapping(value = "/save")
    public Result save(@Valid @RequestBody SysMenu sysMenu) {
        sysMenuService.saveSysMenu(sysMenu);
        return Result.ok(sysMenu);
    }

    /**
     * @param sysMenu
     * @return
     * @功能：修改
     */
    @Log(value = "修改功能菜单")
    @PoxPreAuthorize("@elp.single('sys:menu:update')")
    @PutMapping(value = "/update")
    public Result update(@Valid @RequestBody SysMenu sysMenu) {
        sysMenuService.updateSysMenu(sysMenu);
        return Result.ok(sysMenu);
    }

    /**
     * @param id
     * @return
     * @功能：删除
     */
    @Log(value = "删除功能菜单")
    @PoxPreAuthorize("@elp.single('sys:menu:delete')")
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam String id) {
        sysMenuService.delete(id);
        return Result.ok();
    }

    /**
     * 菜单管理，菜单树数据
     *
     * @return
     */
    @PoxPreAuthorize("@elp.single('sys:menu:getTreeData')")
    @GetMapping(value = "/getTreeData")
    public Result getTreeData() {
        List<ElTree> treeData = sysMenuService.getTreeData();
        return Result.ok(treeData);
    }
}
