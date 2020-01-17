package com.tensquare.base.controller;


import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import com.tensquare.common.entity.PageResult;
import com.tensquare.common.entity.Result;
import com.tensquare.common.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author kaixuan
 * @version 1.0
 * @date 2020/1/13 20:46
 */
@RestController
@RequestMapping("/label")
@CrossOrigin
public class LabelController {

    @Autowired
    private LabelService labelService;


    /**
     * 查询全部列表
     * @return
     */
    @GetMapping
    public Result findAll() {
        return new Result(true, StatusCode.OK,"查询成功",
                labelService.findAll());
    }


    /**
     * 根据id查询标签
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public Result findById(@PathVariable String id) {
        return new Result(true,StatusCode.OK,"查询成功",labelService.findById(id));
    }

    /**
     * 添加标签
     * @param label
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Label label) {
        labelService.add(label);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /**
     * 修改标签
     * @param label
     * @param Id
     * @return
     */
    @PutMapping("{id}")
    public Result update(@RequestBody Label label,
                         @PathVariable String Id) {
        label.setId(Id);
        labelService.update(label);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除标签
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public Result delete(@PathVariable String id) {
        labelService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }


    /**
     * 条件查询
     * @return
     */
    @PostMapping("/search")
    public Result findSearch(@RequestBody Label label) {

        List<Label> list=labelService.findSearch(label);

        return new Result(true,StatusCode.OK,"查询成功",list);
    }

    @PostMapping("/search/{page}/{size}")
    public Result pageQuery(@RequestBody Label label,
                            @PathVariable int page,
                            @PathVariable int size) {
        Page<Label> pageData = labelService.pageQuery(label, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Label>(pageData.getTotalElements(), pageData.getContent()));
    }




}
