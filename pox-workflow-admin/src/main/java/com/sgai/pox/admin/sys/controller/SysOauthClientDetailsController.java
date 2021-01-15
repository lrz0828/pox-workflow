package com.sgai.pox.admin.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sgai.pox.engine.core.base.Result;
import com.sgai.pox.engine.core.base.BaseController;
import com.sgai.pox.admin.sys.entity.SysOauthClientDetails;
import com.sgai.pox.admin.sys.service.SysOauthClientDetailsService;
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
 * 应用客户端Controller
 *
 * @author pox
 */
@RestController
@RequestMapping("/sys/oauthClientDetails")
public class SysOauthClientDetailsController extends BaseController {
    @Autowired
    private SysOauthClientDetailsService sysOauthClientDetailsService;

    /**
     * 自定义查询列表
     *
     * @param sysOauthClientDetails
     * @param current
     * @param size
     * @return
     */
    @PoxPreAuthorize("@elp.single('sys:oauthClientDetails:list')")
    @GetMapping(value = "/list")
    public Result list(SysOauthClientDetails sysOauthClientDetails, @RequestParam Integer current,
                       @RequestParam Integer size) {
        IPage<SysOauthClientDetails> pageList =
                sysOauthClientDetailsService.list(new Page<SysOauthClientDetails>(current, size),
                        sysOauthClientDetails);
        return Result.ok(pageList);
    }

    @PoxPreAuthorize("@elp.single('sys:oauthClientDetails:list')")
    @GetMapping(value = "/queryById")
    public Result queryById(@RequestParam String id) {
        SysOauthClientDetails sysOauthClientDetails = sysOauthClientDetailsService.getById(id);
        return Result.ok(sysOauthClientDetails);
    }

    /**
     * @param sysOauthClientDetails
     * @return
     * @功能：新增
     */
    @PoxPreAuthorize("@elp.single('sys:oauthClientDetails:save')")
    @PostMapping(value = "/save")
    public Result save(@Valid @RequestBody SysOauthClientDetails sysOauthClientDetails) {
        sysOauthClientDetailsService.saveSysOauthClientDetails(sysOauthClientDetails);
        return Result.ok();
    }

    /**
     * @param sysOauthClientDetails
     * @return
     * @功能：修改
     */
    @PoxPreAuthorize("@elp.single('sys:oauthClientDetails:update')")
    @PutMapping(value = "/update")
    public Result update(@Valid @RequestBody SysOauthClientDetails sysOauthClientDetails) {
        sysOauthClientDetailsService.updateSysOauthClientDetails(sysOauthClientDetails);
        return Result.ok();
    }

    /**
     * @param ids
     * @return
     * @功能：批量删除
     */
    @PoxPreAuthorize("@elp.single('sys:oauthClientDetails:delete')")
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam String ids) {
        sysOauthClientDetailsService.delete(ids);
        return Result.ok();
    }
}
