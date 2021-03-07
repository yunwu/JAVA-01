package V1.write.service.impl;

import V1.mapper.OrderWriteMapper;
import V1.model.Order;
import V1.write.service.OrderWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wangdan
 * @date 2021/3/7
 */

@Service
public class OrderWriteServiceImpl implements OrderWriteService {

    @Autowired
    private OrderWriteMapper orderWriteMapper;

    @Override
    @Transactional(transactionManager = "masterTransactionManager")
    public void insertOrder(Order order) {
        orderWriteMapper.insert(order);
    }
}
