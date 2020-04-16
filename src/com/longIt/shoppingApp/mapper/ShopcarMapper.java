package com.longIt.shoppingApp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.longIt.shoppingApp.bean.Shopcar;

/**
 * ShopcarMapper 数据访问类
 * 
 * @date 2019-10-01 17:46:20
 * @version 1.0
 */
public interface ShopcarMapper {

	@Select("select * from ec_shopcar where user_id = #{userId} and article_id = #{id}")
	Shopcar getShopCarByUserIdAndArticleId(@Param("userId") int userId, @Param("id") int id);

	@Update("update ec_shopcar set buynum = #{number} + buynum where user_id = #{userId} and article_id = #{id}")
	void updateShopCar(@Param("userId") int userId, @Param("id") int id, @Param("number") int number);

	@Insert("insert into ec_shopcar(buynum,user_id,article_id) values (#{number},#{userId},#{id})")
	void addShopCar(@Param("userId") int userId, @Param("id") int id, @Param("number") int number);

	// 获取用户购物详情信息
	List<Shopcar> getAllShopCarByUserId(int userId);

	// 更新购物车信息
	@Update("update ec_shopcar set buynum = #{buyNum} where user_id = #{userId} and article_id = #{id}")
	void updateShopcar(@Param("userId") int userId, @Param("id") int id, @Param("buyNum") int buyNum);

	// 删除购物车中的信息
	@Delete("delete from ec_shopcar where user_id = #{userId} and article_id = #{id}")
	void deleteShopCar(@Param("userId") int userId, @Param("id") int id);

}