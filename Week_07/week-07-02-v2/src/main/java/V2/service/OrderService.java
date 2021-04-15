package V2.service;

import V2.model.Order;

import java.util.List;

/**
 * @author wangdan
 * @date 2021/3/7
 */
public interface OrderService {

    List<Order> getTop100Order();


    void insert(Order order);
}
