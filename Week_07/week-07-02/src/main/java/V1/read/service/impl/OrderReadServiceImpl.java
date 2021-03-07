package V1.read.service.impl;

import V1.mapper.read.OrderReadMapper;
import V1.model.Order;
import V1.read.service.OrderReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wangdan
 * @date 2021/3/7
 */
@Service
public class OrderReadServiceImpl implements OrderReadService {

    @Autowired
    private OrderReadMapper orderReadMapper;

    @Override
    @Transactional(transactionManager = "slaveTransactionManager")
    public List<Order> getTop100Order() {
        return orderReadMapper.getTop100Order();
    }
}
