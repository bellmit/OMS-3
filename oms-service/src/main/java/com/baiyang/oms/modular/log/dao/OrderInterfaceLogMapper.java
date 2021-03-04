package com.baiyang.oms.modular.log.dao;

import com.baiyang.oms.modular.log.model.OrderInterfaceLog;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderInterfaceLogMapper extends BaseMapper<OrderInterfaceLog> {

    /**
     * 根据id获取调用日志
     *
     * @param id
     * @return
     */
    public OrderInterfaceLog selectByPrimaryKey(Integer id);

    /**
     * 新增调用日志
     *
     * @param log
     * @return
     */
    public int insertLog(OrderInterfaceLog log);

    /**
     * 更新调用日志
     *
     * @param log
     * @return
     */
    public int updateLog(OrderInterfaceLog log);
}
