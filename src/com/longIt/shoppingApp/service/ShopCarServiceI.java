/**
 * 
 */
package com.longIt.shoppingApp.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.longIt.shoppingApp.bean.Shopcar;

/**
 * @author Administrator
 *
 */
public interface ShopCarServiceI {

	// 将商品信息加入至购物车
	void addArticleToShopCar(HttpSession session, int id, int number);

	// 展示购物车的商品信息
	List<Shopcar> getAllShopCarByUserId(HttpSession session);

	// 更新购物车购买数量
	void updateShopcar(HttpSession session, int id, int number);

	// 删除购物车中的信息
	void deleteShopCar(HttpSession session, int id);

}
