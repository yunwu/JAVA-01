package V3.cntroller;
import V3.model.Order;
import V3.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    public List<Order> getOrders(@RequestParam int userId){
        return orderService.getTop100OrderByUserId(userId);
    }

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public void insertOrder(@RequestParam long userId){
        Order order = Order.builder()
                .id(System.currentTimeMillis())
                .userId(userId)
                .goodsName("高露洁")
                .goodsSnapShotId(2L)
                .price(219.34)
                .status(1)
                .deleted(0)
                .createTime(new Date())
                .updateTime(new Date())
                .build();
        orderService.insertOrder(order);
    }

    @RequestMapping(value = "/orders/edit", method = RequestMethod.POST)
    public void upateOrder(@RequestParam long userId, @RequestParam long orderId){
        orderService.updateOrderById(userId, orderId);
    }

    @RequestMapping(value = "/orders/delete", method = RequestMethod.DELETE)
    public void deleteOrder(@RequestParam long userId, @RequestParam long orderId){
        orderService.delete(userId, orderId);
    }

}
