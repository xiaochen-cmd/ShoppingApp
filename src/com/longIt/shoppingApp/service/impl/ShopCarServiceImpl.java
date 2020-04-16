/**
 * 
 */
package com.longIt.shoppingApp.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.longIt.shoppingApp.bean.Shopcar;
import com.longIt.shoppingApp.bean.User;
import com.longIt.shoppingApp.mapper.ShopcarMapper;
import com.longIt.shoppingApp.service.ShopCarServiceI;

/**
 * @author Administrator
 *
 */
@Service("shopCarServiceI")
@Transactional
public class ShopCarServiceImpl implements ShopCarServiceI {

	@Autowired
	private ShopcarMapper shopcarMapper;

	// 将商品信息加入至购物车
	@Override
	public void addArticleToShopCar(HttpSession session, int id, int number) {

		// 从session中获取用户信息
		User user = (User) session.getAttribute("session_user");
		// 获取用户ID
		int userId = user.getId();
		// 根据用户Id以及商品ID从购物车表中获取相关信息
		Shopcar shopCar = shopcarMapper.getShopCarByUserIdAndArticleId(userId, id);

		if (shopCar != null) {
			// 该商品已经存在于当前用户的购物车中，需要进行商品数量的叠加
			shopcarMapper.updateShopCar(userId, id, number);
		} else {
			// 否则该商品不存在该用户的购物车中
			shopcarMapper.addShopCar(userId, id, number);
		}
	}

	@Override
	public List<Shopcar> getAllShopCarByUserId(HttpSession session) {
		// 从session中获取用户信息
		User user = (User) session.getAttribute("session_user");
		// 获取用户ID
		int userId = user.getId();
		// 获取用户购物详情信息
		List<Shopcar> shopcars = shopcarMapper.getAllShopCarByUserId(userId);
		return shopcars;
	}

	// 更新购物车购买数量
	@Override
	public void updateShopcar(HttpSession session, int id, int number) {
		// 从session中获取用户信息
		User user = (User) session.getAttribute("session_user");
		// 获取用户ID
		int userId = user.getId();

		shopcarMapper.updateShopcar(userId, id, number);

	}

	// 删除购物车中的信息
	@Override
	public void deleteShopCar(HttpSession session, int id) {
		// 从session中获取用户信息
		User user = (User) session.getAttribute("session_user");
		// 获取用户ID
		int userId = user.getId();

		shopcarMapper.deleteShopCar(userId, id);
	}

}
