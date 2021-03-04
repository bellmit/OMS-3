package com.baiyang.oms.modular.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baiyang.oms.modular.system.dao.RelationMapper;
import com.baiyang.oms.modular.system.model.Relation;
import com.baiyang.oms.modular.system.service.IRelationService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色和菜单关联表 服务实现类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-02-22
 */
@Service
public class RelationServiceImpl extends ServiceImpl<RelationMapper, Relation> implements IRelationService {

}
