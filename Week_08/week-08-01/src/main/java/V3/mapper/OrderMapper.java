package V3.mapper;

import V3.model.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<Order> getTop100Order(@Param("userId") int userId);

    void updateGoodsName(@Param("goodsName") String goodsName, @Param("orderId") long orderId, @Param("userId") long userId);

    void deleteByUserIdAndOrderId(@Param("userId") long userId, @Param("orderId") long orderId);
}