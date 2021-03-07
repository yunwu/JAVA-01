package V1.mapper;

import V1.model.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderReadMapper {

    Order selectByPrimaryKey(Long id);

    List<Order> getTop100Order();
}