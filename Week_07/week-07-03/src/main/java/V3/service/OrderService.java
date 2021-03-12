package V3.service;


import V3.model.Order;

import java.util.List;

/**
 * @author wangdan
 * @date 2021/3/7
 */
public interface OrderService {

    List<Order> getTop100Order();

    void insertOrder(Order order);
}
