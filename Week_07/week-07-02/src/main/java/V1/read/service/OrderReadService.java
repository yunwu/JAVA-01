package V1.read.service;

import V1.model.Order;

import java.util.List;

/**
 * @author wangdan
 * @date 2021/3/7
 */
public interface OrderReadService {


    List<Order> getTop100Order();
}
