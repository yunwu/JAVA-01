package V1.cntroller;

import V1.model.Order;
import V1.read.service.OrderReadService;
import V1.write.service.OrderWriteService;
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
    private OrderReadService orderReadService;
    @Autowired
    private OrderWriteService orderWriteService;

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public List<Order> getOrders(){
        return orderReadService.getTop100Order();
    }


    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public void addOrder(){
        Order order = Order.builder()
                .id(System.currentTimeMillis())
                .goodsName("六神沐浴液")
                .goodsSnapShotId(2L)
                .price(219.34)
                .status(1)
                .deleted(0)
                .createTime(new Date())
                .updateTime(new Date())
                .build();
        orderWriteService.insertOrder(order);
    }
}
