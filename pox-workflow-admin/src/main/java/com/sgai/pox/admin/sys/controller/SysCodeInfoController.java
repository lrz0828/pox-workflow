package com.sgai.pox.admin.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sgai.pox.engine.common.core.Result;
import com.sgai.pox.engine.common.core.base.BaseController;
import com.sgai.pox.engine.common.log.annotation.Log;
import com.sgai.pox.admin.sys.entity.SysCodeInfo;
import com.sgai.pox.admin.sys.service.SysCodeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
 * 代码信息Controller
 *
 * @author pox
 */
@RestController
@RequestMapping("/sys/codeInfo")
public class SysCodeInfoController extends BaseController {
    @Autowired
    private SysCodeInfoService sysCodeInfoService;

    /**
     * 自定义查询列表
     *
     * @param sysCodeInfo
     * @param current
     * @param size
     * @return
     */
    @PreAuthorize("@elp.single('sys:codeInfo:list')")
    @GetMapping(value = "/list")
    public Result list(SysCodeInfo sysCodeInfo, @RequestParam Integer current, @RequestParam Integer size) {
        IPage<SysCodeInfo> pageList = sysCodeInfoService.list(new Page<SysCodeInfo>(current, size), sysCodeInfo);
        return Result.ok(pageList);
    }

    @PreAuthorize("@elp.single('sys:codeInfo:list')")
    @GetMapping(value = "/queryById")
    public Result queryById(@RequestParam String id) {
        SysCodeInfo sysCodeInfo = sysCodeInfoService.getById(id);
        return Result.ok(sysCodeInfo);
    }

    /**
     * @param sysCodeInfo
     * @return
     * @功能：新增
     */
    @Log(value = "新增代码信息")
    @PreAuthorize("@elp.single('sys:codeInfo:save')")
    @PostMapping(value = "/save")
    public Result save(@Valid @RequestBody SysCodeInfo sysCodeInfo) {
        sysCodeInfoService.saveSysCodeInfo(sysCodeInfo);
        return Result.ok();
    }

    /**
     * @param sysCodeInfo
     * @return
     * @功能：修改
     */
    @Log(value = "修改代码信息")
    @PreAuthorize("@elp.single('sys:codeInfo:update')")
    @PutMapping(value = "/update")
    public Result update(@Valid @RequestBody SysCodeInfo sysCodeInfo) {
        sysCodeInfoService.updateSysCodeInfo(sysCodeInfo);
        return Result.ok();
    }

    /**
     * @param ids
     * @return
     * @功能：批量删除
     */
    @Log(value = "删除代码信息")
    @PreAuthorize("@elp.single('sys:codeInfo:delete')")
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam String ids) {
        sysCodeInfoService.deleteSysCodeInfo(ids);
        return Result.ok();
    }

    /**
     * 根据代码类型查询代码信息清单
     *
     * @param codeTypeIds
     * @return
     */
    @GetMapping(value = "/getSysCodeInfos")
    public Result getSysCodeInfos(String codeTypeIds) {
        Map<String, List<SysCodeInfo>> sysCodeInfosMap = sysCodeInfoService.getSysCodeInfosFromRedis(codeTypeIds);
        if (sysCodeInfosMap == null) {
            sysCodeInfosMap = sysCodeInfoService.getSysCodeInfosFromDb(codeTypeIds);
        }
        return Result.ok(sysCodeInfosMap);
    }
}
