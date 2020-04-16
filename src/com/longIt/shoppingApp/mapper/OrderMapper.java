package com.longIt.shoppingApp.mapper;

import java.util.List;

import com.longIt.shoppingApp.bean.Order;

/**
 * OrderMapper 数据访问类
 * 
 * @date 2019-10-01 17:46:20
 * @version 1.0
 */
public interface OrderMapper {

	// 保持订单信息
	void saveOrder(Order order);

	// 根据当前用户的Id查询，该用户所有的订单信息
	List<Order> getOrdersByUserId(int userId);

}