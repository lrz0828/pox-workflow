package com.sgai.pox.admin.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sgai.pox.engine.core.base.Result;
import com.sgai.pox.engine.core.base.BaseController;
import com.sgai.pox.engine.core.log.annotation.Log;
import com.sgai.pox.admin.sys.entity.SysCodeType;
import com.sgai.pox.admin.sys.service.SysCodeTypeService;
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

/**
 * 代码类别Controller
 *
 * @author pox
 */
@RestController
@RequestMapping("/sys/codeType")
public class SysCodeTypeController extends BaseController {
    @Autowired
    private SysCodeTypeService sysCodeTypeService;

    /**
     * 自定义查询列表
     *
     * @param sysCodeType
     * @param current
     * @param size
     * @return
     */
    @PoxPreAuthorize("@elp.single('sys:codeType:list')")
    @GetMapping(value = "/list")
    public Result list(SysCodeType sysCodeType, @RequestParam Integer current, @RequestParam Integer size) {
        IPage<SysCodeType> pageList = sysCodeTypeService.list(new Page<SysCodeType>(current, size), sysCodeType);
        return Result.ok(pageList);
    }

    @PoxPreAuthorize("@elp.single('sys:codeType:list')")
    @GetMapping(value = "/queryById")
    public Result queryById(@RequestParam String id) {
        SysCodeType sysCodeType = sysCodeTypeService.getById(id);
        return Result.ok(sysCodeType);
    }

    /**
     * @param sysCodeType
     * @return
     * @功能：新增
     */
    @Log(value = "新增代码类别")
    @PoxPreAuthorize("@elp.single('sys:codeType:save')")
    @PostMapping(value = "/save")
    public Result save(@Valid @RequestBody SysCodeType sysCodeType) {
        sysCodeTypeService.save(sysCodeType);
        return Result.ok();
    }

    /**
     * @param sysCodeType
     * @return
     * @功能：修改
     */
    @Log(value = "修改代码类别")
    @PoxPreAuthorize("@elp.single('sys:codeType:update')")
    @PutMapping(value = "/update")
    public Result update(@Valid @RequestBody SysCodeType sysCodeType) {
        sysCodeTypeService.updateById(sysCodeType);
        return Result.ok();
    }

    /**
     * @param ids
     * @return
     * @功能：批量删除
     */
    @Log(value = "删除代码类别")
    @PoxPreAuthorize("@elp.single('sys:codeType:delete')")
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam String ids) {
        sysCodeTypeService.deleteSysCodeType(ids);
        return Result.ok();
    }
}
