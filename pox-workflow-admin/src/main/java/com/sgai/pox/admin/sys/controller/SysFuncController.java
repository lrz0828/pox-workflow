package com.sgai.pox.admin.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sgai.pox.engine.core.base.Result;
import com.sgai.pox.engine.core.base.BaseController;
import com.sgai.pox.engine.core.log.annotation.Log;
import com.sgai.pox.admin.sys.entity.SysFunc;
import com.sgai.pox.admin.sys.service.SysFuncService;
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
 * 功能Controller
 *
 * @author pox
 */
@RestController
@RequestMapping("/sys/func")
public class SysFuncController extends BaseController {
    @Autowired
    private SysFuncService sysFuncService;

    /**
     * 自定义查询列表
     *
     * @param sysFunc
     * @param current
     * @param size
     * @return
     */
    @PoxPreAuthorize("@elp.single('sys:func:list')")
    @GetMapping(value = "/list")
    public Result list(SysFunc sysFunc, @RequestParam Integer current, @RequestParam Integer size) {
        IPage<SysFunc> pageList = sysFuncService.list(new Page<SysFunc>(current, size), sysFunc);
        return Result.ok(pageList);
    }

    @PoxPreAuthorize("@elp.single('sys:func:list')")
    @GetMapping(value = "/queryById")
    public Result queryById(@RequestParam String id) {
        SysFunc sysFunc = sysFuncService.getById(id);
        return Result.ok(sysFunc);
    }

    /**
     * @param sysFunc
     * @return
     * @功能：新增
     */
    @Log(value = "新增功能按钮")
    @PoxPreAuthorize("@elp.single('sys:func:save')")
    @PostMapping(value = "/save")
    public Result save(@Valid @RequestBody SysFunc sysFunc) {
        sysFuncService.save(sysFunc);
        return Result.ok();
    }

    /**
     * @param sysFunc
     * @return
     * @功能：修改
     */
    @Log(value = "修改功能按钮")
    @PoxPreAuthorize("@elp.single('sys:func:update')")
    @PutMapping(value = "/update")
    public Result update(@Valid @RequestBody SysFunc sysFunc) {
        sysFuncService.updateById(sysFunc);
        return Result.ok();
    }

    /**
     * @param ids
     * @return
     * @功能：批量删除
     */
    @Log(value = "删除功能按钮")
    @PoxPreAuthorize("@elp.single('sys:func:delete')")
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam String ids) {
        if (ids == null || ids.trim().length() == 0) {
            return Result.error("ids can't be empty");
        }
        String[] idsArr = ids.split(",");
        if (idsArr.length > 1) {
            sysFuncService.removeByIds(Arrays.asList(idsArr));
        } else {
            sysFuncService.removeById(idsArr[0]);
        }
        return Result.ok();
    }
}
