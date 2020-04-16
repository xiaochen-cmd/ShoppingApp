package com.longIt.shoppingApp.bean;

/**
 * OrderItem 数据传输类
 * 
 * @author CHUNLONG.LUO
 * @email 584614151@qq.com
 * @date 2019-10-01 17:46:20
 * @version 1.0
 */
public class OrderItem implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int orderId;
	private int articleId;
	private int orderNum;

	private Article article;

	/** setter and getter method */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getOrderId() {
		return this.orderId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public int getArticleId() {
		return this.articleId;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public int getOrderNum() {
		return this.orderNum;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

}