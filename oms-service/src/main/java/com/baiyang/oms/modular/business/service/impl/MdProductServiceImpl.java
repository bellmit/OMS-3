package com.baiyang.oms.modular.business.service.impl;

import com.baiyang.oms.modular.business.dao.MdSkuMapper;
import com.baiyang.oms.modular.business.model.MdSku;
import com.baiyang.oms.modular.business.model.pojo.ProductPojo;
import com.baiyang.oms.modular.business.service.IMdProductService;
import com.baiyang.oms.modular.electronPort.util.ReadWriteXML;
import com.baiyang.oms.modular.business.dao.MdProductMapper;
import com.baiyang.oms.modular.business.model.MdProduct;
import com.baiyang.oms.modular.ningpocang.model.pojo.SynchronizeGoodsReqLog;
import com.baiyang.oms.modular.ningpocang.model.request.Goods;
import com.baiyang.oms.modular.ningpocang.model.request.GoodsExtendProps;
import com.baiyang.oms.modular.ningpocang.model.request.SynchronizeGoodsReq;
import com.baiyang.oms.modular.ningpocang.service.AsynNingPoCangService;
import com.baiyang.oms.modular.ningpocang.service.NingPoCangService;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 产品信息表 服务实现类
 * </p>
 *
 * @author zhangjilong123
 * @since 2018-07-12
 */
@Service("mdProductService")
public class MdProductServiceImpl extends ServiceImpl<MdProductMapper, MdProduct> implements IMdProductService {

	@Autowired
	private MdProductMapper mdProductMapper;

	@Autowired
	private MdSkuMapper skuMapper;

	@Autowired
	private AsynNingPoCangService asynNingPoCangService;

	@Override
	public int getCountByProductCode(String productCode) {
		// TODO Auto-generated method stub
		return this.baseMapper.getCountByProductCode(productCode);
	}

	@Override
	public List<Map<String, Object>> selectListPage(Page<ProductPojo> page,MdProduct mdProduct,String keyword){

		return mdProductMapper.selectListPage(page,mdProduct,keyword);
	}

	@Override
	public List<MdProduct> getProductsByCode(String code) {
		// TODO Auto-generated method stub
		return mdProductMapper.getProductsByCode(code);
	}

	@Override
	public int addSku(MdProduct mdProduct) {
		// TODO Auto-generated method stub
		MdSku sku = new MdSku();
		return skuMapper.updateById(sku);
	}

	@Override
	public List<MdProduct> getProductsByCodes(MdProduct mdProduct) {
		// TODO Auto-generated method stub
		return mdProductMapper.getProductsByCodes(mdProduct);
	}

	@Override
	public String syncProToHouse(String productCode, String houseCode) {
		// TODO Auto-generated method stub
		if(houseCode.equals("by-nb-now")){//宁波仓同步
			MdProduct mp = mdProductMapper.getProductByProductCodeAndStoreCode(productCode, houseCode);
			if(mp == null){
				return "数据库宁波仓无此商品";
			}
			SynchronizeGoodsReqLog sy = new SynchronizeGoodsReqLog();
			  SynchronizeGoodsReq sgr = new SynchronizeGoodsReq();
			  if(mp.getIsSent() == 0){
				  sgr.setActionType("add"); 
			  }else if(mp.getIsSent() == 1){
				  sgr.setActionType("update"); 
			  }else{
				  sgr.setActionType("add");
			  }
			  
			  sgr.setWarehouseCode("17300");
			  sgr.setOwnerCode("4000023120");
			  
			  sgr.setSupplierCode("D-CAL");//供应商编码
			  sgr.setSupplierName("美国安士制药");//供应商名称
			  Goods goo = new Goods();
			  goo.setItemCode(mp.getProductCode());//商品编码,  string (50) ,  必填
			  goo.setItemId(mp.getProductCode());//仓储系统商品编码, string (50) , 条件必填
			  goo.setGoodsCode(mp.getOriginalCode());//货号
			  goo.setItemName(mp.getProductCname());//商品名称
			  goo.setShortName(mp.getProductSname());//商品简称
//			  goo.setEnglishName("英文名称");//英文名
			  goo.setBarCode(mp.getEan13());//条形码,
			  goo.setSkuProperty(mp.getColor());//商品属性
			  goo.setStockUnit("千克");//商品计量单位
			  goo.setLength(mp.getLength()+"");//长 (厘米) ,  double (18, 2) 
			  goo.setWidth(mp.getWeight()+"");//宽 (厘米) ,  double (18, 2) 
			  goo.setHeight(mp.getHeight()+"");//高 (厘米) ,  double (18, 2)
			  goo.setVolume(mp.getVolume()+"");//体积 (升) ,  double (18, 3)
			  goo.setGrossWeight(mp.getGrossWeight()+"");//毛重 (千克) ,  double (18, 3) 
			  goo.setNetWeight(mp.getWeight()+"");//净重 (千克) ,  double (18, 3)
			  goo.setColor(mp.getColor());//颜色,  string (50)
			  goo.setSize(mp.getProductSize());//尺寸,  string (50)
//			  goo.setTitle("");//渠道中的商品标题, string (200)
			 goo.setCategoryId("");//商品类别ID, string (50)
			 goo.setCategoryName("");//商品类别名称, 
			 goo.setSubcategoryId("");//商品子类别ID
			 goo.setSubcategoryName("");//商品子类别名称
			 goo.setPricingCategory("");//计价货类
//			 goo.setSafetyStock("90");//安全库存 
			 goo.setItemType("ZC");//商品类型 (ZC=正常商品, FX=分销商品, ZH=组合商品, ZP=赠品, BC=包材, HC=耗材, FL=辅料, XN=虚拟品, FS=附属品, CC=残次品, OTHER=其它) ,  string (10) , 必填,  (只传英文编码) 
			 goo.setTagPrice("");//吊牌价
			 goo.setRetailPrice("");//零售价
			 goo.setCostPrice(mp.getInPrice()+"");//成本价
			 goo.setPurchasePrice(mp.getInPrice()+"");//采购价
			 goo.setSeasonCode("");//季节编码
			 goo.setSeasonName("");//季节名称
			 goo.setBatchCode("");//品牌代码
			 goo.setBrandName("迪巧");//品牌名称,
			 goo.setIsSNMgmt("N");//是否需要串号管理
//			 goo.setProductDate("2019-01-10");//生产日期
//			 goo.setExpireDate("2020-01-10");//过期日期,
			 goo.setIsShelfLifeMgmt("Y");//是否需要保质期管理
//			 goo.setShelfLife("365");//保质期 
			 goo.setRejectLifecycle(mp.getProductForbiddenCollectDay()+"");//禁收天数,
			 goo.setAdventLifecycle("");//临期天数
			 goo.setLockupLifecycle("");//强制下架天数
			 goo.setIsBatchMgmt("N");//是否需要批次管理
//			 goo.setBarCode("1112");//批次代码
			 goo.setBatchRemark("");//批次备注
			 goo.setPackCode("");//包装代码
			 goo.setPcs("");//箱规,
			 goo.setOriginAddress(mp.getPlaceOfOrigin());//商品的原产地
			 goo.setApprovalNumber(mp.getRegisterNo());//批准文号
			 goo.setIsFragile("N");//是否易碎品
			 goo.setIsHazardous("N");//是否危险品
			 goo.setRemark("");//备注
			 SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");//
			 Date date = new Date();//
			 goo.setCreateTime(sdf.format(date));//创建时间
			 goo.setUpdateTime(sdf.format(date));//更新时间
			 goo.setIsValid("Y");//是否有效
			 goo.setIsSku("Y");//是否sku
			 goo.setPackageMaterial("");//商品包装材料类型
			 GoodsExtendProps gxp = new GoodsExtendProps();//扩展属性
			 gxp.setOriginCountry(mp.getPlaceOfOrigin());//原产地
			 gxp.setOriginCountryCode(mp.getOriginCountry());//原产地（产销国）编码
			 gxp.setItemRecordNo(mp.getOriginalCode());//海关备案号 原specprop3字段，必填
			 gxp.setCodeTs(mp.getCodeHs());//HS编码 原specprop4字段，必填
			 gxp.setStockUnitCode(mp.getItemUnit());//商品计量单位编码，对应海关要求的编码,string (50) 必填
			 goo.setExtendProps(gxp);//
			 sgr.setItem(goo);
			 sy.setPojo(sgr);
			 Integer status = asynNingPoCangService.goodsSynchronize(sy);
			 if(status == 200){
				 MdProduct newMP = new MdProduct();
				 newMP.setId(mp.getId());
				 newMP.setIsSent(1);
				 mdProductMapper.updateById(newMP);
				 return "同步成功";
			 }
				 return "同步失败 宁波仓接口同步失败";
		}else{
			return "同步失败 只能同步宁波仓商品";
		}
			
	}


}
