package com.baiyang.oms.core.common.constant.factory;

import com.baiyang.oms.core.common.constant.cache.Cache;
import com.baiyang.oms.core.common.constant.cache.CacheKey;
import com.baiyang.oms.core.common.constant.state.ManagerStatus;
import com.baiyang.oms.core.common.constant.state.MenuStatus;
import com.baiyang.oms.core.log.LogObjectHolder;
import com.baiyang.oms.core.support.StrKit;
import com.baiyang.oms.core.util.Convert;
import com.baiyang.oms.core.util.SpringContextHolder;
import com.baiyang.oms.core.util.ToolUtil;
import com.baiyang.oms.modular.business.dao.*;
import com.baiyang.oms.modular.business.model.Merchant;
import com.baiyang.oms.modular.business.model.Platform;
import com.baiyang.oms.modular.business.util.ObjectUtils;
import com.baiyang.oms.modular.system.dao.*;
import com.baiyang.oms.modular.system.model.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.springframework.cache.annotation.CachePut;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 常量的生产工厂
 *
 * @author fengshuonan
 * @date 2017年2月13日 下午10:55:21
 */
@Component
@DependsOn("springContextHolder")
public class ConstantFactory implements IConstantFactory {

    private RoleMapper roleMapper = SpringContextHolder.getBean(RoleMapper.class);
    private DeptMapper deptMapper = SpringContextHolder.getBean(DeptMapper.class);
    private DictMapper dictMapper = SpringContextHolder.getBean(DictMapper.class);
    private UserMapper userMapper = SpringContextHolder.getBean(UserMapper.class);
    private MenuMapper menuMapper = SpringContextHolder.getBean(MenuMapper.class);
    private NoticeMapper noticeMapper = SpringContextHolder.getBean(NoticeMapper.class);
    //    private OmsAreaMapper omsAreaMapper = SpringContextHolder.getBean(OmsAreaMapper.class);
    private MerchantMapper mdMerchantMapper = SpringContextHolder.getBean(MerchantMapper.class);
    private PlatformMapper platformMapper = SpringContextHolder.getBean(PlatformMapper.class);
    private MdWarehouseMapper warehouseMapper = SpringContextHolder.getBean(MdWarehouseMapper.class);
    //    private MdProductMapper mdProductMapper = SpringContextHolder.getBean(MdProductMapper.class);
    private MdSkuMapper mdSkuMapper = SpringContextHolder.getBean(MdSkuMapper.class);

    private MdRegionMapper regionMapper = SpringContextHolder.getBean(MdRegionMapper.class);

    public static IConstantFactory me() {
        return SpringContextHolder.getBean("constantFactory");
    }

    /**
     * 根据用户id获取用户名称
     *
     * @author stylefeng
     * @Date 2017/5/9 23:41
     */
    @Override
    public String getUserNameById(Integer userId) {
        User user = userMapper.selectById(userId);
        if (user != null) {
            return user.getName();
        } else {
            return "--";
        }
    }

    /**
     * 根据用户id获取用户账号
     *
     * @author stylefeng
     * @date 2017年5月16日21:55:371
     */
    @Override
    public String getUserAccountById(Integer userId) {
        User user = userMapper.selectById(userId);
        if (user != null) {
            return user.getAccount();
        } else {
            return "--";
        }
    }

    /**
     * 通过角色ids获取角色名称
     */
    @Override
    @CachePut(value = Cache.CONSTANT, key = "'" + CacheKey.ROLES_NAME + "'+#roleIds")
    public String getRoleName(String roleIds) {
        Integer[] roles = Convert.toIntArray(roleIds);
        StringBuilder sb = new StringBuilder();
        for (int role : roles) {
            Role roleObj = roleMapper.selectById(role);
            if (ToolUtil.isNotEmpty(roleObj) && ToolUtil.isNotEmpty(roleObj.getName())) {
                sb.append(roleObj.getName()).append(",");
            }
        }
        return StrKit.removeSuffix(sb.toString(), ",");
    }

    /**
     * 通过角色id获取角色名称
     */
    @Override
    @CachePut(value = Cache.CONSTANT, key = "'" + CacheKey.SINGLE_ROLE_NAME + "'+#roleId")
    public String getSingleRoleName(Integer roleId) {
        if (0 == roleId) {
            return "--";
        }
        Role roleObj = roleMapper.selectById(roleId);
        if (ToolUtil.isNotEmpty(roleObj) && ToolUtil.isNotEmpty(roleObj.getName())) {
            return roleObj.getName();
        }
        return "";
    }

    /**
     * 通过角色id获取角色英文名称
     */
    @Override
    @CachePut(value = Cache.CONSTANT, key = "'" + CacheKey.SINGLE_ROLE_TIP + "'+#roleId")
    public String getSingleRoleTip(Integer roleId) {
        if (0 == roleId) {
            return "--";
        }
        Role roleObj = roleMapper.selectById(roleId);
        if (ToolUtil.isNotEmpty(roleObj) && ToolUtil.isNotEmpty(roleObj.getName())) {
            return roleObj.getTips();
        }
        return "";
    }

    /**
     * 获取部门名称
     */
    @Override
    @CachePut(value = Cache.CONSTANT, key = "'" + CacheKey.DEPT_NAME + "'+#deptId")
    public String getDeptName(Integer deptId) {
        Dept dept = deptMapper.selectById(deptId);
        if (ToolUtil.isNotEmpty(dept) && ToolUtil.isNotEmpty(dept.getSimplename())) {
            return dept.getSimplename();
        }
        return "";
    }

    /**
     * 获取菜单的名称们(多个)
     */
    @Override
    public String getMenuNames(String menuIds) {
        Integer[] menus = Convert.toIntArray(menuIds);
        StringBuilder sb = new StringBuilder();
        for (int menu : menus) {
            Menu menuObj = menuMapper.selectById(menu);
            if (ToolUtil.isNotEmpty(menuObj) && ToolUtil.isNotEmpty(menuObj.getName())) {
                sb.append(menuObj.getName()).append(",");
            }
        }
        return StrKit.removeSuffix(sb.toString(), ",");
    }

    /**
     * 获取菜单名称
     */
    @Override
    public String getMenuName(Long menuId) {
        if (ToolUtil.isEmpty(menuId)) {
            return "";
        } else {
            Menu menu = menuMapper.selectById(menuId);
            if (menu == null) {
                return "";
            } else {
                return menu.getName();
            }
        }
    }

    /**
     * 获取菜单名称通过编号
     */
    @Override
    public String getMenuNameByCode(String code) {
        if (ToolUtil.isEmpty(code)) {
            return "";
        } else {
            Menu param = new Menu();
            param.setCode(code);
            Menu menu = menuMapper.selectOne(param);
            if (menu == null) {
                return "";
            } else {
                return menu.getName();
            }
        }
    }

    /**
     * 获取字典名称
     */
    @Override
    public String getDictName(Integer dictId) {
        if (ToolUtil.isEmpty(dictId)) {
            return "";
        } else {
            Dict dict = dictMapper.selectById(dictId);
            if (dict == null) {
                return "";
            } else {
                return dict.getName();
            }
        }
    }

    /**
     * 获取通知标题
     */
    @Override
    public String getNoticeTitle(Integer dictId) {
        if (ToolUtil.isEmpty(dictId)) {
            return "";
        } else {
            Notice notice = noticeMapper.selectById(dictId);
            if (notice == null) {
                return "";
            } else {
                return notice.getTitle();
            }
        }
    }

    /**
     * 根据字典名称和字典中的值获取对应的名称
     */
    @Override
    public String getDictsByName(String name, Integer val) {
        Dict temp = new Dict();
        temp.setName(name);
        Dict dict = dictMapper.selectOne(temp);
        if (dict == null) {
            return "";
        } else {
            Wrapper<Dict> wrapper = new EntityWrapper<>();
            wrapper = wrapper.eq("pid", dict.getId());
            List<Dict> dicts = dictMapper.selectList(wrapper);
            for (Dict item : dicts) {
                if (item.getNum() != null && item.getNum().equals(val)) {
                    return item.getName();
                }
            }
            return "";
        }
    }


    /**
     * 根据字典名称和字典中的名称获取对应的值
     */
    @Override
    public Integer getDisctValueIdByValueName(String dictName, String valueName) {
        Dict temp = new Dict();
        temp.setName(dictName);
        Dict dict = dictMapper.selectOne(temp);
        if (dict == null) {
            return null;
        } else {
            Wrapper<Dict> wrapper = new EntityWrapper<>();
            wrapper = wrapper.eq("pid", dict.getId());
            List<Dict> dicts = dictMapper.selectList(wrapper);
            for (Dict item : dicts) {
                if (item.getName() != null && item.getName().equals(valueName)) {
                    return item.getNum();
                }
            }
            return null;
        }
    }

    /**
     * 获取性别名称
     */
    @Override
    public String getSexName(Integer sex) {
        return getDictsByName("性别", sex);
    }


    /**
     * 获取用户登录状态
     */
    @Override
    public String getStatusName(Integer status) {
        return ManagerStatus.valueOf(status);
    }

    /**
     * 获取菜单状态
     */
    @Override
    public String getMenuStatusName(Integer status) {
        return MenuStatus.valueOf(status);
    }

    /**
     * 查询字典
     */
    @Override
    public List<Dict> findInDict(Integer id) {
        if (ToolUtil.isEmpty(id)) {
            return null;
        } else {
            EntityWrapper<Dict> wrapper = new EntityWrapper<>();
            List<Dict> dicts = dictMapper.selectList(wrapper.eq("pid", id));
            if (dicts == null || dicts.size() == 0) {
                return null;
            } else {
                return dicts;
            }
        }
    }

    /**
     * 获取被缓存的对象(用户删除业务)
     */
    @Override
    public String getCacheObject(String para) {
        return LogObjectHolder.me().get().toString();
    }

    /**
     * 获取子部门id
     */
    @Override
    public List<Integer> getSubDeptId(Integer deptid) {
        Wrapper<Dept> wrapper = new EntityWrapper<>();
        wrapper = wrapper.like("pids", "%[" + deptid + "]%");
        List<Dept> depts = this.deptMapper.selectList(wrapper);

        ArrayList<Integer> deptids = new ArrayList<>();

        if (depts != null && depts.size() > 0) {
            for (Dept dept : depts) {
                deptids.add(dept.getId());
            }
        }

        return deptids;
    }

    /**
     * 获取所有父部门id
     */
    @Override
    public List<Integer> getParentDeptIds(Integer deptid) {
        Dept dept = deptMapper.selectById(deptid);
        String pids = dept.getPids();
        String[] split = pids.split(",");
        ArrayList<Integer> parentDeptIds = new ArrayList<>();
        for (String s : split) {
            parentDeptIds.add(Integer.valueOf(StrKit.removeSuffix(StrKit.removePrefix(s, "["), "]")));
        }
        return parentDeptIds;
    }

    @Override
    public String getDisctName(String dictName, Integer valueId) {
        // TODO Auto-generated method stub
        return getDictsByName(dictName, valueId);
    }

    @Override
    public String getArea(Integer areaId) {
        // TODO Auto-generated method stub
//		return omsAreaMapper.selectById(areaId);
        return regionMapper.selectAreaNameById(areaId);
    }

    /**
     * 获取商家名称
     */
    @Override
    public Object getMerchantNameById(Integer merchantId) {
        Merchant mdMerchant = mdMerchantMapper.selectById(merchantId);
        if (ObjectUtils.isEmpty(mdMerchant)) {
            return "--";
        } else {
            return mdMerchant.getMerchantName();
        }
    }

    /**
     * 获取平台名称
     */
    @Override
    public Object getPlatformNameById(Integer platformId) {
        Platform platform = platformMapper.selectById(platformId);
        if (ObjectUtils.isEmpty(platform)) {
            return "--";
        } else {
            return platform.getPlatformName();
        }
    }

    @Override
    public String getWareHouseName(Integer houseId) {
        // TODO Auto-generated method stub
        return warehouseMapper.getNameById(houseId);
    }

    @Override
    public Map<String, Object> getProductMap(Long proudctId) {
        // TODO Auto-generated method stub
        return mdSkuMapper.getProductMapById(proudctId);
    }

    @Override
    public String getWareHouseNameByCode(String storeCode) {
        return warehouseMapper.getWareHouseNameByCode(storeCode);
    }

    /**
     * 根据字典表中的状态名字，查询这个状态名字下的所有状态integer值和对应的状态名称；
     *
     * @param dictName
     * @return
     */
    @Override
    public List<Map<String, Object>> getDisctValueIdAndValueNameByName(String dictName) {
        // TODO Auto-generated method stub
        Map<String, Object> map;
        List<Map<String, Object>> listMap = new ArrayList<>();
        Dict temp = new Dict();
        temp.setName(dictName);
        Dict dict = dictMapper.selectOne(temp);
        if (dict == null) {
            return listMap;
        } else {
            Wrapper<Dict> wrapper = new EntityWrapper<>();
            wrapper = wrapper.eq("pid", dict.getId());
            List<Dict> dicts = dictMapper.selectList(wrapper);
            for (Dict item : dicts) {
//                if (item.getName() != null && item.getName().equals(valueName)) {
//                    return item.getNum();
//                }
                map = new HashMap<>();
                map.put("num", item.getNum());
                map.put("name", item.getName());
                listMap.add(map);
            }
            return listMap;
        }
    }


}
