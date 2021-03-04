package com.baiyang.oms.modular.business.controller;

import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.common.annotion.BussinessLog;
import com.baiyang.oms.core.common.annotion.Permission;
import com.baiyang.oms.core.common.constant.Const;
import com.baiyang.oms.core.common.constant.dictmap.HouseDict;
import com.baiyang.oms.core.common.constant.factory.PageFactory;
import com.baiyang.oms.core.common.response.Result;
import com.baiyang.oms.core.shiro.ShiroKit;
import com.baiyang.oms.modular.business.model.MdRegion;
import com.baiyang.oms.modular.business.model.MdWarehouse;
import com.baiyang.oms.modular.business.pojo.WarehouseReq;
import com.baiyang.oms.modular.business.service.IMdRegionService;
import com.baiyang.oms.modular.business.service.IMdWarehouseService;
import com.baiyang.oms.modular.business.warpper.BHouseWarpper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 库存管理
 *
 * @author fengshuonan
 * @Date 2018-06-25 14:43:14
 */
@Controller
@RequestMapping("/bWarehouse")
public class MdWarehouseController extends BaseController {

    @Autowired
    private IMdWarehouseService bWarehouseService;

    @Autowired
    private IMdRegionService mdRegionService;


    @RequestMapping("/getArea")
    @ResponseBody
    public Result<Map<String, Object>> getArea() {
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        List<MdRegion> list = mdRegionService.getAreaByParentId(0);
        resultMap.put("areas", list);
        return new Result<>(resultMap);
    }

    /**
     * 获取修改数据
     */
    @RequestMapping("/find/{bWarehouseId}")
    @ResponseBody
    public Result<Map<String, Object>> bWarehouseUpdate(@PathVariable Integer bWarehouseId) {
        MdWarehouse bWarehouse = bWarehouseService.selectById(bWarehouseId);
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        resultMap.put("warehouse", bWarehouse);
        List<MdRegion> cityList = mdRegionService.getAreaByParentId(bWarehouse.getProvinceId().intValue());
        resultMap.put("cityList", cityList);
        List<MdRegion> districtList = mdRegionService.getAreaByParentId(bWarehouse.getCityId().intValue());
        resultMap.put("districtList", districtList);
        return new Result<>(resultMap);
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Result<Object> list(@RequestBody Map<String, String> params) {
        Page<MdWarehouse> page = new PageFactory<MdWarehouse>().defaultPage(Integer.parseInt(params.get("pageNo")),
                Integer.parseInt(params.get("pageSize")), params.get("sort"), params.get("order"));
        String houseName = params.get("houseName");
        String houseTypeStr = params.get("houseTypeId");
        String storageTypeStr = params.get("storageTypeId");
        String functionTypeStr = params.get("functionTypeId");
        String tradeTypeStr = params.get("tradeTypeId");
        MdWarehouse bwh = new MdWarehouse();
        bwh.setIsDeleted(0);
        bwh.setWarehouseName(houseName);
        if (StringUtils.isNotEmpty(houseTypeStr)) {
            bwh.setWarehouseType(Integer.parseInt(houseTypeStr));
        }
        if (StringUtils.isNotEmpty(storageTypeStr)) {
            bwh.setStorageType(Integer.parseInt(storageTypeStr));
        }
        if (StringUtils.isNotEmpty(functionTypeStr)) {
            bwh.setFunctionType(Integer.parseInt(functionTypeStr));
        }
        if (StringUtils.isNotEmpty(tradeTypeStr)) {
            bwh.setTradeType(Integer.parseInt(tradeTypeStr));
        }
        List<Map<String, Object>> houseList = bWarehouseService.pageGrade(page, bwh, params.get("keyword"));
        page.setRecords((List<MdWarehouse>) new BHouseWarpper(houseList).warp());
        return new Result<>(super.packForBT(page));
    }

    @RequestMapping(value = "/area/{pid}")
    @ResponseBody
    public Result<Object> getAreaAjax(@PathVariable("pid") Integer pid) {
        List<MdRegion> list = mdRegionService.getAreaByParentId(pid);
        return new Result<>(list);
    }

    /**
     *
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Result<Void> add(@RequestBody MdWarehouse bWarehouse) {
        Long userId = Long.parseLong(ShiroKit.getUser().id.toString());
        bWarehouse.setUpdatedBy(userId);
        bWarehouse.setCreatedBy(userId);
        bWarehouse.setIsDeleted(0);
        bWarehouse.setStatus(1);
        bWarehouse.setUpdateTime(new Date());
        bWarehouse.setCreateTime(new Date());
        bWarehouseService.insert(bWarehouse);
        return new Result<>();
    }

    /**
     *
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Result<Void> delete(@RequestBody List<Integer> idList) {
        Map<String, Object> map = Maps.newConcurrentMap();
        for (Integer id : idList) {
            map.put("delete", "1");
            map.put("id", id);
            //逻辑删除
            bWarehouseService.updateStatusById(map);
        }
        return new Result<>();
    }

    /**
     *
     */
    @Permission(Const.ADMIN_NAME)
    @RequestMapping(value = "/freeze")
    @BussinessLog(value = "冻结", key = "name", dict = HouseDict.class)
    @ResponseBody
    public Result<Void> freeze(@RequestBody List<Integer> idList) {
        for (Integer id : idList) {
            Map<String, Object> map = Maps.newConcurrentMap();
            map.put("status", "0");
            map.put("id", id);
            bWarehouseService.updateStatusById(map);
        }
        return new Result<>();
    }

    /**
     *
     */
    @RequestMapping(value = "/unfreeze")
    @BussinessLog(value = "解冻", key = "name", dict = HouseDict.class)
    @ResponseBody
    public Result<Void> unfreeze(@RequestBody List<Integer> idList) {
        Map<String, Object> map = Maps.newConcurrentMap();
        for (Integer id : idList) {
            map.put("status", "1");
            map.put("id", id);
            bWarehouseService.updateStatusById(map);
        }
        return new Result<>();
    }

    /**
     *
     */
    @RequestMapping(value = "/update")
    @BussinessLog(value = "修改", key = "name", dict = HouseDict.class)
    @ResponseBody
    public Result<Void> update(@RequestBody WarehouseReq warehouseReq) {
        MdWarehouse mdWarehouse = bWarehouseService.selectById(warehouseReq.getId());
        Long userId = Long.parseLong(ShiroKit.getUser().id.toString());
        BeanUtils.copyProperties(warehouseReq, mdWarehouse);
        mdWarehouse.setUpdatedBy(userId);
        mdWarehouse.setUpdateTime(new Date());
        bWarehouseService.updateById(mdWarehouse);
        return new Result<>();
    }

}
