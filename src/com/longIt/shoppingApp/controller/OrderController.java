/**
 * 
 */
package com.longIt.shoppingApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.longIt.shoppingApp.bean.Order;
import com.longIt.shoppingApp.service.OrderServiceI;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderServiceI orderServiceI;

	// 提交订单
	@RequestMapping(value = "/orderSubmit")
	public String orderSubmit(String orderInfo) {

		try {
			System.out.println("orderInfo:" + orderInfo);
			orderServiceI.orderSubmit(orderInfo);
		} catch (Exception e) {
			//
			e.printStackTrace();
		}
		// 重定向至订单列表
		return "redirect:/order/showOrder.action";

	}

	// 查询当前用户所有的订单信息
	@RequestMapping(value = "/showOrder")
	public String showOrder(Model model) {

		try {
			// 根据当前用户的Id查询，该用户所有的订单信息
			List<Order> orders = orderServiceI.getOrdersByUserId();
			model.addAttribute("orders", orders);
		} catch (Exception e) {
			//
			e.printStackTrace();
		}
		// 跳转至订单列表页面
		return "order";

	}
}
