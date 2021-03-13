package V3.service;


import V3.model.Order;

import java.util.List;

/**
 * @author wangdan
 * @date 2021/3/7
 */
public interface OrderService {

    List<Order> getTop100OrderByUserId(int userId);

    void insertOrder(Order order);

    void updateOrderById(long userId, long orderId);

    void delete(long userId, long orderId);
}
