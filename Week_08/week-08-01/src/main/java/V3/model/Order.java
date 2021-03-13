package V3.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 * 订单表
 */
@Data
@Builder
public class Order implements Serializable {
    private Long id;

    private Long userId;

    private Long goodsSnapShotId;

    private Double price;

    private String goodsName;

    private int status;

    private Date createTime;

    private Date updateTime;

    private int deleted;

    private static final long serialVersionUID = 1L;
}