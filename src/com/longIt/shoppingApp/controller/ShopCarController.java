/**
 * 
 */
package com.longIt.shoppingApp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.longIt.shoppingApp.bean.Shopcar;
import com.longIt.shoppingApp.service.ShopCarServiceI;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/shopCar")
public class ShopCarController {

	@Autowired
	private ShopCarServiceI shopCarService;

	@RequestMapping("/addToCar.action")
	public String addShopCar(HttpSession session, @Param("id") int id, @Param("number") int number) {
		shopCarService.addArticleToShopCar(session, id, number);
		// 商品加入购物车成功，立马展示购物车的商品信息
		return "redirect:/shopCar/showShopCar.action";
	}

	// 展示购物车的商品信息
	@RequestMapping("/showShopCar.action")
	public String showShopCar(HttpSession session, Model model) {
		// 根据用户ID获取购物车详情
		List<Shopcar> shopCars = shopCarService.getAllShopCarByUserId(session);
		model.addAttribute("shopCars", shopCars);

		// 定义总金额
		double totalPrice = 0.0;

		// 遍历集合，计算购物车中商品的总金额
		for (Shopcar shopcar : shopCars) {
			totalPrice += shopcar.getArticle().getDiscountPrice() * shopcar.getBuynum();
		}

		model.addAttribute("totalPrice", totalPrice);

		// 跳转至购物车展示页面
		return "shopCar";
	}

	// 更新购物车中的信息
	@RequestMapping("/updateShopcar.action")
	public String updateShopcar(HttpSession session, @Param("id") int id, @Param("number") int number) {

		// 更新购物车购买数量
		shopCarService.updateShopcar(session, id, number);

		// 商品加入购物车成功，立马展示购物车的商品信息
		return "redirect:/shopCar/showShopCar.action";
	}

	// 删除购物车中的信息
	@RequestMapping("/deleteShopCar.action")
	public String deleteShopCar(HttpSession session, @Param("id") int id) {

		// 删除购物车购买数量
		shopCarService.deleteShopCar(session, id);

		// 商品加入购物车成功，立马展示购物车的商品信息
		return "redirect:/shopCar/showShopCar.action";
	}
}
