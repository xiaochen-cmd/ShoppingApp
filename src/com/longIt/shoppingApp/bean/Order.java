package com.longIt.shoppingApp.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Order 数据传输类
 * 
 * @author CHUNLONG.LUO
 * @email 584614151@qq.com
 * @date 2019-10-01 17:46:20
 * @version 1.0
 */
public class Order implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String orderCode;
	private java.util.Date createDate;
	private java.util.Date sendDate;
	private String status;
	private double amount;
	private int userId;

	// 定义集合拥有封装订单详情信息
	private List<OrderItem> items = new ArrayList<>();

	/** setter and getter method */
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getOrderCode() {
		return this.orderCode;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	public java.util.Date getCreateDate() {
		return this.createDate;
	}

	public void setSendDate(java.util.Date sendDate) {
		this.sendDate = sendDate;
	}

	public java.util.Date getSendDate() {
		return this.sendDate;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getUserId() {
		return this.userId;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

}