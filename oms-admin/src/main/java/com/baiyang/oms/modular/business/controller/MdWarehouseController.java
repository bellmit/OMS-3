package com.baiyang.oms.modular.business.controller;

import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.common.annotion.BussinessLog;
import com.baiyang.oms.core.common.annotion.Permission;
import com.baiyang.oms.core.common.constant.Const;
import com.baiyang.oms.core.common.constant.dictmap.HouseDict;
import com.baiyang.oms.core.common.constant.dictmap.MenuDict;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import com.baiyang.oms.core.log.LogObjectHolder;
import com.baiyang.oms.core.shiro.ShiroKit;

import org.springframework.web.bind.annotation.RequestParam;

import com.baiyang.oms.modular.business.model.MdRegion;
import com.baiyang.oms.modular.business.model.MdWarehouse;
import com.baiyang.oms.modular.business.service.IMdRegionService;
import com.baiyang.oms.modular.business.service.IMdWarehouseService;
import com.baiyang.oms.modular.business.service.OmsAreaService;
import com.baiyang.oms.modular.business.util.ObjectUtils;
import com.baiyang.oms.modular.business.warpper.BHouseWarpper;

/**
 * 库存管理
 *
 * @author fengshuonan
 * @Date 2018-06-25 14:43:14
 */
@Controller
@RequestMapping("/bWarehouse")
public class MdWarehouseController extends BaseController {

    private String PREFIX = "/business/bWarehouse/";

    @Autowired
    private IMdWarehouseService bWarehouseService;
    @Autowired
    private OmsAreaService omsAreaService;
    @Autowired
    private IMdRegionService mdRegionService;

    /**
     * 
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "bWarehouse.html";
    }

    /**
     * 跳转添加
     */
    @RequestMapping("/bWarehouse_add")
    public String bWarehouseAdd(Model model) {
//    	List<OmsArea> list = omsAreaService.getAreaByPid(0);
    	List<MdRegion> list = mdRegionService.getAreaByParentId(0);
    	model.addAttribute("areas",list);
        return PREFIX + "bWarehouse_add.html";
    }

    /**
     * 跳转修改
     */
    @RequestMapping("/bWarehouse_update/{bWarehouseId}")
    public String bWarehouseUpdate(@PathVariable Integer bWarehouseId, Model model) {
        MdWarehouse bWarehouse = bWarehouseService.selectById(bWarehouseId);
        model.addAttribute("item",bWarehouse);
        
//        List<OmsArea> provinceList = omsAreaService.getAreaByPid(0);
        List<MdRegion> provinceList = mdRegionService.getAreaByType(1);
    	model.addAttribute("provinceList",provinceList);
    	
//    	List<OmsArea> cityList = omsAreaService.getAreaByPid(bWarehouse.getProvinceId().intValue());
    	List<MdRegion> cityList = mdRegionService.getAreaByParentId(bWarehouse.getProvinceId().intValue());
    	model.addAttribute("cityList",cityList);
    	
//    	List<OmsArea> districtList = omsAreaService.getAreaByPid(bWarehouse.getCityId().intValue());
    	List<MdRegion> districtList = mdRegionService.getAreaByParentId(bWarehouse.getCityId().intValue());
    	model.addAttribute("districtList",districtList);
        
        LogObjectHolder.me().set(bWarehouse);
        return PREFIX + "bWarehouse_edit.html";
//    	return "";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String houseName,Integer houseTypeId
    		,Integer storageTypeId
    		,Integer functionTypeId
    		,Integer tradeTypeId) {
    	
    	MdWarehouse bwh = new MdWarehouse();
    	if(!ObjectUtils.isEmpty(houseName)){
    		bwh.setWarehouseName(houseName.trim());
    	}
    	if(!ObjectUtils.isEmpty(houseTypeId)){
    		bwh.setWarehouseType(houseTypeId);
    	}
    	if(!ObjectUtils.isEmpty(storageTypeId)){
    		bwh.setStorageType(storageTypeId);
    	}
		if(!ObjectUtils.isEmpty(functionTypeId)){
		    bwh.setFunctionType(functionTypeId);		
		}
		if(!ObjectUtils.isEmpty(tradeTypeId)){
			bwh.setTradeType(tradeTypeId);
		}
		bwh.setIsDeleted(0);
    	bwh.setWarehouseName(houseName);
    	List<Map<String, Object>> houseList = bWarehouseService.selectHouseList(bwh);
    	return new BHouseWarpper(houseList).warp();
    }
    
    @RequestMapping(value = "/area")
    @ResponseBody
    public Object  getAreaAjax(@RequestParam("pid") Integer pid){
//    	List<OmsArea> list = omsAreaService.getAreaByPid(pid);
    	List<MdRegion> list = mdRegionService.getAreaByParentId(pid);
//    	System.out.println("size=="+list.size());
    	return list;
    }

    /**
     * 
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(MdWarehouse bWarehouse) {
    	Long userId = Long.parseLong(ShiroKit.getUser().id+"");
    	bWarehouse.setUpdatedBy(userId);
    	bWarehouse.setCreatedBy(userId);
    	bWarehouse.setIsDeleted(0);
    	bWarehouse.setStatus(1);
    	bWarehouse.setUpdateTime(new Date());
    	bWarehouse.setCreateTime(new Date());
        bWarehouseService.insert(bWarehouse);
        return SUCCESS_TIP;
    }

    /**
     * 
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer bWarehouseId) {
//        bWarehouseService.deleteById(bWarehouseId);
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("delete", "1");
    	map.put("id", bWarehouseId);
    	bWarehouseService.updateStatusById(map);//逻辑删除
        return SUCCESS_TIP;
    }
    
    /**
     * 
     */
    @Permission(Const.ADMIN_NAME)
    @RequestMapping(value = "/freeze")
    @BussinessLog(value = "冻结", key = "name", dict = HouseDict.class)
    @ResponseBody
    public Object freeze(@RequestParam Integer bWarehouseId) {
//        bWarehouseService.deleteById(bWarehouseId);
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("status", "0");
    	map.put("id", bWarehouseId);
    	bWarehouseService.updateStatusById(map);
        return SUCCESS_TIP;
    }
    
    /**
     * 
     */
    @RequestMapping(value = "/unfreeze")
    @BussinessLog(value = "解冻", key = "name", dict = HouseDict.class)
    @ResponseBody
    public Object unfreeze(@RequestParam Integer bWarehouseId) {
//        bWarehouseService.deleteById(bWarehouseId);
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("status", "1");
    	map.put("id", bWarehouseId);
    	bWarehouseService.updateStatusById(map);
        return SUCCESS_TIP;
    }

    /**
     * 
     */
    @RequestMapping(value = "/update")
    @BussinessLog(value = "修改", key = "name", dict = HouseDict.class)
    @ResponseBody
    public Object update(MdWarehouse bWarehouse) {
    	Long userId = Long.parseLong(ShiroKit.getUser().id+"");
    	bWarehouse.setUpdatedBy(userId);
    	bWarehouse.setUpdateTime(new Date());
        bWarehouseService.updateById(bWarehouse);
        return SUCCESS_TIP;
    }

    /**
     * 
     */
    @RequestMapping(value = "/detail/{bWarehouseId}")
    @ResponseBody
    public Object detail(@PathVariable("bWarehouseId") Integer bWarehouseId) {
        return bWarehouseService.selectById(bWarehouseId);
//    	return null;
    }
}
