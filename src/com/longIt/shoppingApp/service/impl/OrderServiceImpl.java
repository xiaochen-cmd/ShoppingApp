/**
 * 
 */
package com.longIt.shoppingApp.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.longIt.shoppingApp.bean.Order;
import com.longIt.shoppingApp.bean.OrderItem;
import com.longIt.shoppingApp.bean.User;
import com.longIt.shoppingApp.mapper.OrderItemMapper;
import com.longIt.shoppingApp.mapper.OrderMapper;
import com.longIt.shoppingApp.service.OrderServiceI;

/**
 * @author Administrator
 *
 */
@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderServiceI {

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private OrderItemMapper orderItemMapper;

	// 提交订单
	@Override
	public void orderSubmit(String orderInfo) {
		// TODO Auto-generated method stub
		String[] orderInfos = orderInfo.substring(1).split("#");
		// 创建订单对象
		Order order = new Order();
		// 指定下单时间
		order.setCreateDate(new Date());
		// 获取session
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()
				.getSession();
		// 从SESSION中获取用户
		int userId = ((User) session.getAttribute("session_user")).getId();
		// 指定订单属于哪个用户
		order.setUserId(userId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		// 设置订单编号
		order.setOrderCode("HC-" + sdf.format(new Date()) + userId);

		// 定义订单总金额
		double totalPrice = 0.0;
		// 创建集合拥有存放订单详情信息
		List<OrderItem> items = new ArrayList<>();
		for (String info : orderInfos) {
			String[] infos = info.split("_");
			// 获取商品ID
			int articleId = Integer.valueOf(infos[0]);
			// 购买数量
			int buyNum = Integer.valueOf(infos[1]);

			OrderItem item = new OrderItem();
			item.setArticleId(articleId);
			item.setOrderNum(buyNum);
			// 将订单详细记录放入集合中
			items.add(item);

			totalPrice += Double.valueOf(infos[2]);
		}
		// 指定订单的总金额
		order.setAmount(totalPrice);

		// 保持订单信息,保持完订单后，需要获取订单ID ，因为需要将订单id存放在订单详情中
		orderMapper.saveOrder(order);

		// 获取订单主键的值
		int orderId = order.getId();
		for (OrderItem item : items) {
			// 保持订单明细
			item.setOrderId(orderId);
			orderItemMapper.saveItem(item);
		}

	}

	// 根据当前用户的Id查询，该用户所有的订单信息
	@Override
	public List<Order> getOrdersByUserId() {
		// 获取当前用户的信息
		// 获取session
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()
				.getSession();
		// 从SESSION中获取用户
		int userId = ((User) session.getAttribute("session_user")).getId();
		return orderMapper.getOrdersByUserId(userId);
	}

}
