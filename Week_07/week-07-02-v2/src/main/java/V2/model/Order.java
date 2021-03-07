package V2.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 * 订单表
 */
@Data
public class Order implements Serializable {
    private Long id;

    private Long goodsSnapShotId;

    private Double price;

    private String goodsName;

    private Byte status;

    private Date createTime;

    private Date updateTime;

    private Byte deleted;

    private static final long serialVersionUID = 1L;
}