package V1.mapper;

import V1.model.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderWriteMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    int insertSelective(Order record);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}