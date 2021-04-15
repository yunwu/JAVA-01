package V3.service;

import V3.mapper.OrderMapper;
import V3.model.Order;
import org.apache.shardingsphere.infra.hint.HintManager;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author wangdan
 * @date 2021/3/7
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override

    public List<Order> getTop100Order() {
        return orderMapper.getTop100Order();
    }


    @Override
    @Transactional
    @ShardingTransactionType(TransactionType.XA)
    public List<Order> insertOrder(Order order) {
        orderMapper.insert(order);
        return orderMapper.getTop100Order();
    }
}
