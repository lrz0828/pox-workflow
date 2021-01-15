package com.sgai.pox.admin.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sgai.pox.engine.core.base.Result;
import com.sgai.pox.engine.core.base.BaseController;
import com.sgai.pox.admin.sys.entity.SysPostUser;
import com.sgai.pox.admin.sys.service.SysPostUserService;
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
 * 岗位和用户关系Controller
 *
 * @author pox
 */
@RestController
@RequestMapping("/sys/postUser")
public class SysPostUserController extends BaseController {
    @Autowired
    private SysPostUserService sysPostUserService;

    /**
     * 自定义查询列表
     *
     * @param sysPostUser
     * @param current
     * @param size
     * @return
     */
    @PoxPreAuthorize("@elp.single('sys:postUser:list')")
    @GetMapping(value = "/list")
    public Result list(SysPostUser sysPostUser, @RequestParam Integer current, @RequestParam Integer size) {
        IPage<SysPostUser> pageList = sysPostUserService.list(new Page<SysPostUser>(current, size), sysPostUser);
        return Result.ok(pageList);
    }

    @PoxPreAuthorize("@elp.single('sys:postUser:list')")
    @GetMapping(value = "/queryById")
    public Result queryById(@RequestParam String id) {
        SysPostUser sysPostUser = sysPostUserService.getById(id);
        return Result.ok(sysPostUser);
    }

    /**
     * @param sysPostUser
     * @return
     * @功能：新增
     */
    @PoxPreAuthorize("@elp.single('sys:postUser:save')")
    @PostMapping(value = "/save")
    public Result save(@Valid @RequestBody SysPostUser sysPostUser) {
        sysPostUserService.save(sysPostUser);
        return Result.ok();
    }

    /**
     * @param sysPostUser
     * @return
     * @功能：修改
     */
    @PoxPreAuthorize("@elp.single('sys:postUser:update')")
    @PutMapping(value = "/update")
    public Result update(@Valid @RequestBody SysPostUser sysPostUser) {
        sysPostUserService.updateById(sysPostUser);
        return Result.ok();
    }

    /**
     * @param ids
     * @return
     * @功能：批量删除
     */
    @PoxPreAuthorize("@elp.single('sys:postUser:delete')")
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam String ids) {
        if (ids == null || ids.trim().length() == 0) {
            return Result.error("ids can't be empty");
        }
        String[] idsArr = ids.split(",");
        if (idsArr.length > 1) {
            sysPostUserService.removeByIds(Arrays.asList(idsArr));
        } else {
            sysPostUserService.removeById(idsArr[0]);
        }
        return Result.ok();
    }
}
