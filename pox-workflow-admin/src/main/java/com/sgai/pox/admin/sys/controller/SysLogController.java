package com.sgai.pox.admin.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sgai.pox.engine.core.base.Result;
import com.sgai.pox.engine.core.base.BaseController;
import com.sgai.pox.admin.sys.entity.SysLog;
import com.sgai.pox.admin.sys.service.SysLogService;
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
 * 系统日志Controller
 *
 * @author pox
 */
@RestController
@RequestMapping("/sys/log")
public class SysLogController extends BaseController {
    @Autowired
    private SysLogService sysLogService;

    /**
     * 自定义查询列表
     *
     * @param sysLog
     * @param current
     * @param size
     * @return
     */
    @PoxPreAuthorize("@elp.single('sys:log:list')")
    @GetMapping(value = "/list")
    public Result list(SysLog sysLog, @RequestParam Integer current, @RequestParam Integer size) {
        IPage<SysLog> pageList = sysLogService.list(new Page<SysLog>(current, size), sysLog);
        return Result.ok(pageList);
    }

    @PoxPreAuthorize("@elp.single('sys:log:list')")
    @GetMapping(value = "/queryById")
    public Result queryById(@RequestParam String id) {
        SysLog sysLog = sysLogService.getById(id);
        return Result.ok(sysLog);
    }

    /**
     * @param sysLog
     * @return
     * @功能：新增
     */
    @PoxPreAuthorize("@elp.single('sys:log:save')")
    @PostMapping(value = "/save")
    public Result save(@Valid @RequestBody SysLog sysLog) {
        sysLogService.save(sysLog);
        return Result.ok();
    }

    /**
     * @param sysLog
     * @return
     * @功能：修改
     */
    @PoxPreAuthorize("@elp.single('sys:log:update')")
    @PutMapping(value = "/update")
    public Result update(@Valid @RequestBody SysLog sysLog) {
        sysLogService.updateById(sysLog);
        return Result.ok();
    }

    /**
     * @param ids
     * @return
     * @功能：批量删除
     */
    @PoxPreAuthorize("@elp.single('sys:log:delete')")
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam String ids) {
        if (ids == null || ids.trim().length() == 0) {
            return Result.error("ids can't be empty");
        }
        String[] idsArr = ids.split(",");
        if (idsArr.length > 1) {
            sysLogService.removeByIds(Arrays.asList(idsArr));
        } else {
            sysLogService.removeById(idsArr[0]);
        }
        return Result.ok();
    }
}
