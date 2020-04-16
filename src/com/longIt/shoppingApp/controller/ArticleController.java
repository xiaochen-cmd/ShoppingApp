/**
 * 
 */
package com.longIt.shoppingApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.longIt.shoppingApp.bean.Article;
import com.longIt.shoppingApp.bean.ArticleType;
import com.longIt.shoppingApp.service.ArticleServiceI;
import com.longIt.shoppingApp.util.pager.PageModel;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

	@Autowired
	private ArticleServiceI articleService;

	@RequestMapping("/index")
	public String articleIndex(Model model, String typeCode, String keyword, PageModel pageModel) {
		// 获取所有的一级物品类型
		List<ArticleType> articleTypes = articleService.findAllFirstArticleType();
		model.addAttribute("articleTypes", articleTypes);

		// 如果typeCode不为空，则根据typeCode查询二级物品类型
		if (typeCode != null && !typeCode.equals("")) {
			// 一级物品类信息
			String code = typeCode.substring(0, 4);
			// 根据一级物品类型获取对应的二级物品类信息
			List<ArticleType> searticleTypes = articleService.findAllSecondArticleTypes(code + "%");
			model.addAttribute("secondArticleTypes", searticleTypes);
		}

		// 根据用户指定的商品类型查询数据库，获取相应的商品信息
		List<Article> articles = articleService.findAllArticle(typeCode == null ? null : typeCode + "%",
				keyword == null ? null : "%" + keyword + "%", pageModel);
		model.addAttribute("articles", articles);

		// 查询总记录数
		int totalNum = articleService.findTotalNum(typeCode == null ? null : typeCode + "%",
				keyword == null ? null : "%" + keyword + "%");
		pageModel.setTotalNum(totalNum);

		/// WEB-INF/jsp/index.jsp
		return "articleIndex";
	}

	// 根据商品Id获取商品详情
	@RequestMapping("/detail")
	public String articleDetail(Integer id, Model model) {
		// 根据商品ID获取商品详情
		Article article = articleService.getArticleById(id);
		model.addAttribute("article", article);

		// WEB-INF/jsp/articleDetail
		return "articleDetail";
	}
}
