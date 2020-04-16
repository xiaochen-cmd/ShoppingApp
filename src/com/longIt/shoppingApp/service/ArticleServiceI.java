/**
 * 
 */
package com.longIt.shoppingApp.service;

import java.util.List;

import com.longIt.shoppingApp.bean.Article;
import com.longIt.shoppingApp.bean.ArticleType;
import com.longIt.shoppingApp.util.pager.PageModel;

/**
 * @author Administrator
 *
 */
public interface ArticleServiceI {
	// 获取所有的一级物品类型
	List<ArticleType> findAllFirstArticleType();

	// 根据商品类型获取商品信息
	List<Article> findAllArticle(String typrCode, String keyword, PageModel pageModel);

	List<ArticleType> findAllSecondArticleTypes(String string);

	// 根据商品ID获取商品详情
	Article getArticleById(Integer id);

	// 查询总记录数
	int findTotalNum(String typrCode, String keyword);

}
