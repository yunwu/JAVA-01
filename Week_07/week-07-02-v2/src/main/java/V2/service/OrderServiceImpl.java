package V2.service;

import V2.mapper.OrderMapper;
import V2.model.Order;
import lombok.SneakyThrows;
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

    @SneakyThrows
    @Override
    @Transactional(value = "master", rollbackFor = Exception.class)
    public void insert(Order order) {
        orderMapper.insertSelective(order);
        throw new Exception();
    }
}
