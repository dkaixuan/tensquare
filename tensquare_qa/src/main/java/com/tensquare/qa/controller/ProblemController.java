package com.tensquare.qa.controller;

import com.tensquare.common.entity.PageResult;
import com.tensquare.common.entity.Result;
import com.tensquare.common.entity.StatusCode;
import com.tensquare.qa.pojo.Problem;
import com.tensquare.qa.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 控制器层
 *
 * @author Administrator
 */
@RestController
@CrossOrigin
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @Autowired
    private HttpServletRequest request;


    /**
     * 根据标签ID查询等待回答列表
     * @param labelid
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/waitlist/{labelid}/{page}/{size}", method = RequestMethod.GET)
    public Result findWaitListByLabelId(@PathVariable String labelid, @PathVariable int page, @PathVariable int rows) {
        Page<Problem> pageList = problemService.findWaitListByLabelId(labelid, page, rows);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<>(pageList.getTotalElements(), pageList.getContent()));
    }


    /**
     * 根据标签ID查询热门问题列表
     * @param labelid
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/hotlist/{labelid}/{page}/{size}", method = RequestMethod.GET)
    public Result findHotListByLabelId(@PathVariable String labelid, @PathVariable int page, @PathVariable int rows) {
        Page<Problem> pageList = problemService.findHotListByLabelId(labelid, page, rows);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<>(pageList.getTotalElements(), pageList.getContent()));
    }


    /**
     * 根据标签ID查询最新问题列表
     * @param labelid
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/newlist/{labelid}/{page}/{size}", method = RequestMethod.GET)
    public Result findNewListByLabelId(@PathVariable String labelid, @PathVariable int page, @PathVariable int rows) {
        Page<Problem> pageList = problemService.findNewListByLabelId(labelid, page, rows);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 查询全部数据
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", problemService.findAll());
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK, "查询成功", problemService.findById(id));
    }


    /**
     * 分页+多条件查询
     *
     * @param searchMap 查询条件封装
     * @param page      页码
     * @param size      页大小
     * @return 分页结果
     */
    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap, @PathVariable int page, @PathVariable int size) {
        Page<Problem> pageList = problemService.findSearchPage(searchMap, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Problem>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap) {
        return new Result(true, StatusCode.OK, "查询成功", problemService.findSearchList(searchMap));
    }



    /**
     * 修改
     *
     * @param problem
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@RequestBody Problem problem, @PathVariable String id) {
        problem.setId(id);
        problemService.update(problem);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        problemService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

}
