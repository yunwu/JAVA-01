package V3.cntroller;
import V3.model.Order;
import V3.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author wangdan
 * @date 2021/3/7
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public List<Order> getOrders(){
        return orderService.getTop100Order();
    }

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public List<Order> insertOrder() {
        Order order = Order.builder()
                .id(System.currentTimeMillis())
                .goodsName("苹果")
                .goodsSnapShotId(2L)
                .price(219.34)
                .status(1)
                .deleted(0)
                .createTime(new Date())
                .updateTime(new Date())
                .build();
        return orderService.insertOrder(order);
    }

}
