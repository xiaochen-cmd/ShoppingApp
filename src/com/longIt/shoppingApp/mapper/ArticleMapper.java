package com.longIt.shoppingApp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.longIt.shoppingApp.bean.Article;
import com.longIt.shoppingApp.util.pager.PageModel;

/**
 * ArticleMapper 数据访问类
 * 
 * @date 2019-10-01 17:46:20
 * @version 1.0
 */
public interface ArticleMapper {

	List<Article> findAllArticle(@Param("typeCode") String typeCode, @Param("keyword") String keyword,
			@Param("pageModel") PageModel pageModel);

	@Select("select * from ec_article where id = #{id}")
	Article getArticleById(Integer id);

	int findTotalNum(@Param("typeCode") String typeCode, @Param("keyword") String keyword);

}