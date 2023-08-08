package com.shopping.order.dao.dataobject;

//import com.baomidou.mybatisplus.annotation.TableId;
//import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

//import javax.persistence.Id;
//import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Table(name = "t_order")
//@TableName("t_order")
public class OrderDO {

    //    @TableId("ID")
    @Id
    private Long id;
    private BigDecimal totalCost;
    private Long createTime;
    private Long modifyTime;
    private Byte state;
    private Long userId;
}
