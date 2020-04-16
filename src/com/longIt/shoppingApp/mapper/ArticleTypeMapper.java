package com.longIt.shoppingApp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.longIt.shoppingApp.bean.Article;
import com.longIt.shoppingApp.bean.ArticleType;

/**
 * ArticleTypeMapper 数据访问类
 * 
 * @date 2019-10-01 17:46:20
 * @version 1.0
 */
public interface ArticleTypeMapper {
	/**
	 * 获取所有的一级物品类型
	 */
	@Select("select * from ec_article_type where length(code) = 4")
	List<ArticleType> findAllFirstArticleType();

	List<Article> findAllArticle(String typeCode);

	/**
	 * @param typeCode
	 * @return 根据一级物品类型获取二级物品类型信息
	 */
	@Select("select * from ec_article_type where code like #{typeCode} and length(code) = 8")
	List<ArticleType> findAllSecondArticleTypes(String typeCode);

}