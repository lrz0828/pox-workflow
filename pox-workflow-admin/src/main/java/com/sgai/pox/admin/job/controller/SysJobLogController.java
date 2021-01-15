package com.sgai.pox.admin.job.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sgai.pox.admin.job.service.SysJobLogService;
import com.sgai.pox.admin.sys.entity.SysJobLog;
import com.sgai.pox.engine.core.annotation.PoxPreAuthorize;
import com.sgai.pox.engine.core.base.BaseController;
import com.sgai.pox.engine.core.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
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
 * 定时任务执行日志Controller
 *
 * @author pox
 */
@RestController
@RequestMapping("/sys/jobLog")
public class SysJobLogController extends BaseController {
    @Autowired
    private SysJobLogService sysJobLogService;

    /**
     * 自定义查询列表
     *
     * @param sysJobLog
     * @param current
     * @param size
     * @return
     */
    @PoxPreAuthorize("@elp.single('sys:jobLog:list')")
    @GetMapping(value = "/list")
    public Result list(SysJobLog sysJobLog, @RequestParam Integer current, @RequestParam Integer size) {
        IPage<SysJobLog> pageList = sysJobLogService.list(new Page<SysJobLog>(current, size), sysJobLog);
        return Result.ok(pageList);
    }

    @PoxPreAuthorize("@elp.single('sys:jobLog:list')")
    @GetMapping(value = "/queryById")
    public Result queryById(@RequestParam String id) {
        SysJobLog sysJobLog = sysJobLogService.getById(id);
        return Result.ok(sysJobLog);
    }

    /**
     * @param sysJobLog
     * @return
     * @功能：新增
     */
    @PoxPreAuthorize("@elp.single('sys:jobLog:save')")
    @PostMapping(value = "/save")
    public Result save(@Valid @RequestBody SysJobLog sysJobLog) {
        sysJobLogService.save(sysJobLog);
        return Result.ok();
    }

    /**
     * @param sysJobLog
     * @return
     * @功能：修改
     */
    @PoxPreAuthorize("@elp.single('sys:jobLog:update')")
    @PutMapping(value = "/update")
    public Result update(@Valid @RequestBody SysJobLog sysJobLog) {
        sysJobLogService.updateById(sysJobLog);
        return Result.ok();
    }

    /**
     * @param ids
     * @return
     * @功能：批量删除
     */
    @PoxPreAuthorize("@elp.single('sys:jobLog:delete')")
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam String ids) {
        if (ids == null || ids.trim().length() == 0) {
            return Result.error("ids can't be empty");
        }
        String[] idsArr = ids.split(",");
        if (idsArr.length > 1) {
            sysJobLogService.removeByIds(Arrays.asList(idsArr));
        } else {
            sysJobLogService.removeById(idsArr[0]);
        }
        return Result.ok();
    }
}
