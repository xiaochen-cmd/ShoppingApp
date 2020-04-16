/**
 * 
 */
package com.longIt.shoppingApp.service;

import java.util.List;

import com.longIt.shoppingApp.bean.Order;

/**
 * @author Administrator
 *
 */
public interface OrderServiceI {

	// 提交订单
	void orderSubmit(String orderInfo);

	// 根据当前用户的Id查询，该用户所有的订单信息
	List<Order> getOrdersByUserId();

}
