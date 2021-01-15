package com.sgai.pox.admin.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sgai.pox.engine.core.base.BaseServiceImpl;
import com.sgai.pox.engine.core.constant.SysConstants;
import com.sgai.pox.engine.core.exception.SysException;
import com.sgai.pox.engine.core.util.CommonUtil;
import com.sgai.pox.admin.sys.entity.SysOrg;
import com.sgai.pox.admin.sys.entity.vo.ElTree;
import com.sgai.pox.admin.sys.mapper.SysOrgMapper;
import com.sgai.pox.admin.sys.service.SysOrgService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 机构Service
 *
 * @author pox
 */
@Service
public class SysOrgServiceImpl extends BaseServiceImpl<SysOrgMapper, SysOrg> implements SysOrgService {
    @Override
    public IPage<SysOrg> list(IPage<SysOrg> page, SysOrg sysOrg) {
        return page.setRecords(baseMapper.list(page, sysOrg));
    }

    /**
     * 新增机构，自动计算机构级别、机构级次码、是否叶子
     *
     * @param sysOrg
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveSysOrg(SysOrg sysOrg) {
        // 【1】 校验id是否重复.
        if (CommonUtil.isEmptyStr(sysOrg.getParentOrgId())) {
            throw new SysException("保存失败,请先选择上级机构!");
        }
        // 父节点
        SysOrg parentSysOrg = this.getById(sysOrg.getParentOrgId());
        if (parentSysOrg == null) {
            throw new SysException("保存失败,上级机构ID【" + sysOrg.getParentOrgId() + "】不存在!");
        }
        // 【2】计算机构级次,机构级次码
        // 【2-a】设置新增机构的父机构是否叶子为否
        if (!SysConstants.IS_LEAF_0.equals(parentSysOrg.getIsLeaf())) {
            parentSysOrg.setIsLeaf(SysConstants.IS_LEAF_0);
            this.updateById(parentSysOrg);
        }
        Integer orgLevel = Integer.valueOf(parentSysOrg.getOrgLevel()) + 1;
        sysOrg.setOrgLevel(orgLevel.toString());
        sysOrg.setOrgLevelCode(parentSysOrg.getOrgLevelCode() + "," + sysOrg.getOrgId());

        // 【3】设置新增机构是否叶子为 是；
        sysOrg.setIsLeaf("1");
        return this.save(sysOrg);
    }

    /**
     * 修改机构，自动计算机构级别、机构级次码、是否叶子
     *
     * @param sysOrg
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSysOrg(SysOrg sysOrg) {
        if (CommonUtil.isEmptyStr(sysOrg.getParentOrgId())) {
            sysOrg.setOrgLevel("1");
            sysOrg.setOrgLevelCode(sysOrg.getOrgId());
            return this.updateById(sysOrg);
        }
        // 父节点
        SysOrg parentSysOrg = this.getById(sysOrg.getParentOrgId());
        if (parentSysOrg == null) {
            throw new SysException("保存失败,上级机构ID【" + sysOrg.getParentOrgId() + "】不存在!");
        }
        // 【2】计算机构级次,机构级次码
        // 【2-a】设置新增机构的父机构是否叶子为否
        if (!SysConstants.IS_LEAF_0.equals(parentSysOrg.getIsLeaf())) {
            parentSysOrg.setIsLeaf(SysConstants.IS_LEAF_0);
            this.updateById(parentSysOrg);
        }
        Integer orgLevel = Integer.valueOf(parentSysOrg.getOrgLevel()) + 1;
        sysOrg.setOrgLevel(orgLevel.toString());
        sysOrg.setOrgLevelCode(parentSysOrg.getOrgLevelCode() + "," + sysOrg.getOrgId());
        return this.updateById(sysOrg);
    }

    /**
     * 机构管理，机构树数据
     *
     * @return
     */
    @Override
    public List<ElTree> getTreeData() {
        List<SysOrg> orgList = baseMapper.list(null, new SysOrg());
        return makeOrgTree(orgList);
    }

    /**
     * 生成机构树
     *
     * @param orgList
     * @return
     */
    @Override
    public List<ElTree> makeOrgTree(List<SysOrg> orgList) {
        Map<String, ElTree> orgMap = new LinkedHashMap<String, ElTree>();
        for (SysOrg sysOrg : orgList) {
            ElTree elTree = new ElTree();
            elTree.setId(sysOrg.getOrgId());
            elTree.setLabel(sysOrg.getOrgName());
            elTree.setIsLeaf("1".equals(sysOrg.getIsLeaf()));
            elTree.setData(sysOrg);
            orgMap.put(sysOrg.getOrgId(), elTree);
            if (CommonUtil.isNotEmptyStr(sysOrg.getParentOrgId()) && orgMap.containsKey(sysOrg.getParentOrgId())) {
                elTree.setParentId(sysOrg.getParentOrgId());
                ElTree parentElTree = orgMap.get(sysOrg.getParentOrgId());
                parentElTree.addChildren(elTree);
            }
        }

        List<ElTree> result = new ArrayList<ElTree>();
        orgMap.forEach((k, v) -> {
            if (CommonUtil.isEmptyStr(v.getParentId())) {
                result.add(v);
            }
        });
        return result;
    }
}
