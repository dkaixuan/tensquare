package com.tensquare.article.controller;
import com.tensquare.article.pojo.Article;
import com.tensquare.article.service.ArticleService;
import com.tensquare.common.entity.PageResult;
import com.tensquare.common.entity.Result;
import com.tensquare.common.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
/**
 * 控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleController {

	@Autowired
	private ArticleService articleService;


	/**
	 * 点赞
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/thumbup/{id}",method=RequestMethod.PUT)
	public Result updateThumbup(@PathVariable String id){
		articleService.updateThumbup(id);
		return new Result(true, StatusCode.OK,"点赞成功");
	}

	/**
	 * 文章审核
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/examine/{id}",method=RequestMethod.PUT)
	public Result examine(@PathVariable String id){
		articleService.examine(id);
		return new Result(true, StatusCode.OK, "审核成功！");
	}

	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true,StatusCode.OK,"查询成功",articleService.findAll());
	}
	


	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
	public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
		Page<Article> pageList = articleService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Article>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",articleService.findSearch(searchMap));
    }
	

	
}
