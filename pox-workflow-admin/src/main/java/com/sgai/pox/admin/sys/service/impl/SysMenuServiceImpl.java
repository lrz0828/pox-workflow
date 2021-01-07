package com.sgai.pox.admin.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgai.pox.engine.common.core.base.BaseServiceImpl;
import com.sgai.pox.engine.common.core.constant.SysConstants;
import com.sgai.pox.engine.common.core.exception.SysException;
import com.sgai.pox.engine.common.core.util.CommonUtil;
import com.sgai.pox.admin.sys.entity.SysFunc;
import com.sgai.pox.admin.sys.entity.SysMenu;
import com.sgai.pox.admin.sys.entity.vo.ElTree;
import com.sgai.pox.admin.sys.mapper.SysMenuMapper;
import com.sgai.pox.admin.sys.service.SysFuncService;
import com.sgai.pox.admin.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜单Service
 *
 * @author pox
 */
@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysFuncService sysFuncService;

    @Override
    public IPage<SysMenu> list(IPage<SysMenu> page, SysMenu sysMenu) {
        return page.setRecords(baseMapper.list(page, sysMenu));
    }

    /**
     * 新增菜单，自动计算是否叶子
     *
     * @param sysMenu
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveSysMenu(SysMenu sysMenu) {
        // 【1】 判断是否有上级菜单
        if (!CommonUtil.isEmptyStr(sysMenu.getParentMenuId())) {
            // 父节点
            SysMenu parentSysMenu = this.getById(sysMenu.getParentMenuId());
            if (parentSysMenu == null) {
                throw new SysException("保存失败,上级菜单ID【" + sysMenu.getParentMenuId() + "】不存在!");
            }
            // 【2】设置新增菜单的父菜单是否叶子为否
            if (!SysConstants.IS_LEAF_0.equals(parentSysMenu.getIsLeaf())) {
                parentSysMenu.setIsLeaf(SysConstants.IS_LEAF_0);
                this.updateById(parentSysMenu);
            }
        }

        // 【3】设置新增菜单是否叶子为 是
        sysMenu.setIsLeaf("1");
        return this.save(sysMenu);
    }

    /**
     * 修改菜单
     *
     * @param sysMenu
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSysMenu(SysMenu sysMenu) {
        SysMenu sysMenuDb = this.getById(sysMenu.getMenuId());
        if (sysMenuDb == null) {
            throw new SysException("保存失败,菜单ID【" + sysMenu.getMenuId() + "】不存在!");
        }
        String parentMenuIdDb = sysMenuDb.getParentMenuId() == null ? "" : sysMenuDb.getParentMenuId().toString();
        String parentMenuId = sysMenu.getParentMenuId() == null ? "" : sysMenu.getParentMenuId().toString();
        if (!parentMenuIdDb.equals(parentMenuId)) {
            throw new SysException("不允许修改父菜单!");
        }
        // 【1】 判断是否有上级菜单
        if (!CommonUtil.isEmptyStr(parentMenuId)) {
            // 父节点
            SysMenu parentSysMenu = this.getById(sysMenu.getParentMenuId());
            if (parentSysMenu == null) {
                throw new SysException("保存失败,上级菜单ID【" + sysMenu.getParentMenuId() + "】不存在!");
            }
            // 【2】设置新增菜单的父菜单是否叶子为否
            if (!SysConstants.IS_LEAF_0.equals(parentSysMenu.getIsLeaf())) {
                parentSysMenu.setIsLeaf(SysConstants.IS_LEAF_0);
                this.updateById(parentSysMenu);
            }
        }
        return this.updateById(sysMenu);
    }

    /**
     * @param id
     * @return
     * @功能：批量删除
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(String id) {
        int countChildren = this.count(new LambdaQueryWrapper<SysMenu>().eq(SysMenu::getParentMenuId, id));
        if (countChildren > 0) {
            throw new SysException("请先删除叶子节点");
        }
        SysMenu sysMenu = this.getById(id);
        boolean result = this.removeById(id);
        // 删除功能
        sysFuncService.remove(new LambdaQueryWrapper<SysFunc>().eq(SysFunc::getMenuId, id));
        if (!CommonUtil.isEmptyStr(sysMenu.getParentMenuId())) {
            // 若父菜单的下级菜单为空，则修改为是叶子节点
            int countParentChildren = this.count(new LambdaQueryWrapper<SysMenu>().eq(SysMenu::getParentMenuId,
                    sysMenu.getParentMenuId()));
            if (countParentChildren == 0) {
                SysMenu parentSysMenu = this.getById(sysMenu.getParentMenuId());
                parentSysMenu.setIsLeaf("1");
                this.updateById(parentSysMenu);
            }
        }
        return result;
    }

    /**
     * 菜单管理，菜单树数据
     *
     * @return
     */
    @Override
    public List<ElTree> getTreeData() {
        List<SysMenu> list = baseMapper.list(null, new SysMenu());
        Map<String, ElTree> menuMap = new LinkedHashMap<String, ElTree>();
        for (SysMenu sysMenu : list) {
            ElTree elTree = new ElTree();
            elTree.setId(sysMenu.getMenuId());
            elTree.setLabel(sysMenu.getMenuName());
            elTree.setIsLeaf("1".equals(sysMenu.getIsLeaf()));
            elTree.setData(sysMenu);
            menuMap.put(sysMenu.getMenuId(), elTree);
            if (CommonUtil.isNotEmptyStr(sysMenu.getParentMenuId()) && menuMap.containsKey(sysMenu.getParentMenuId())) {
                elTree.setParentId(sysMenu.getParentMenuId());
                ElTree parentElTree = menuMap.get(sysMenu.getParentMenuId());
                parentElTree.addChildren(elTree);
            }
        }

        List<ElTree> result = new ArrayList<ElTree>();
        menuMap.forEach((k, v) -> {
            if (CommonUtil.isEmptyStr(v.getParentId())) {
                result.add(v);
            }
        });
        return result;
    }
}
