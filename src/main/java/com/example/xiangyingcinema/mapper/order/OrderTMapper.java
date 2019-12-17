package com.example.xiangyingcinema.mapper.order;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.xiangyingcinema.model.order.OrderT;
import com.example.xiangyingcinema.vo.order.OrderVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 订单信息表 Mapper 接口
 * </p>
 *
 */
public interface OrderTMapper extends BaseMapper<OrderT> {

    String getSeatsByFieldId(@Param("fieldId") String fieldId);

    OrderVO getOrderInfoById(@Param("orderId") String orderId);

    List<OrderVO> getOrdersByUserId(@Param("userId") String userId, Page<OrderVO> page);

    String getSoldSeatsByFieldId(@Param("fieldId") Integer fieldId);

}
