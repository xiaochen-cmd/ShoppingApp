/**
 * 
 */
package com.longIt.shoppingApp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.longIt.shoppingApp.bean.Article;
import com.longIt.shoppingApp.bean.ArticleType;
import com.longIt.shoppingApp.mapper.ArticleMapper;
import com.longIt.shoppingApp.mapper.ArticleTypeMapper;
import com.longIt.shoppingApp.service.ArticleServiceI;
import com.longIt.shoppingApp.util.pager.PageModel;

/**
 * @author Administrator
 *
 */
@Service("articleService")
@Transactional
public class ArticleServiceImpl implements ArticleServiceI {

	@Autowired
	private ArticleTypeMapper articleTypeMapper;

	@Autowired
	private ArticleMapper articleMapper;

	@Override
	public List<ArticleType> findAllFirstArticleType() {
		List<ArticleType> articleTypes = articleTypeMapper.findAllFirstArticleType();
		return articleTypes;
	}

	@Override
	public List<Article> findAllArticle(String typeCode, String keyword, PageModel pageModel) {
		List<Article> articles = articleMapper.findAllArticle(typeCode, keyword, pageModel);
		return articles;
	}

	@Override
	public List<ArticleType> findAllSecondArticleTypes(String typeCode) {
		List<ArticleType> articleTypes = articleTypeMapper.findAllSecondArticleTypes(typeCode);
		return articleTypes;
	}

	@Override
	public Article getArticleById(Integer id) {
		Article article = articleMapper.getArticleById(id);
		return article;
	}

	// 查询总记录数
	@Override
	public int findTotalNum(String typrCode, String keyword) {
		// TODO Auto-generated method stub
		return articleMapper.findTotalNum(typrCode, keyword);
	}

}
