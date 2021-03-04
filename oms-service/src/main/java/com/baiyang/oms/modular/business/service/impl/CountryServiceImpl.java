package com.baiyang.oms.modular.business.service.impl;

import com.baiyang.oms.modular.business.dao.CountryMapper;
import com.baiyang.oms.modular.business.model.Country;
import com.baiyang.oms.modular.business.service.ICountryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 国家代码表 服务实现类
 * </p>
 *
 * @author zhangjilong123
 * @since 2018-08-14
 */
@Service
public class CountryServiceImpl extends ServiceImpl<CountryMapper, Country> implements ICountryService {

}
