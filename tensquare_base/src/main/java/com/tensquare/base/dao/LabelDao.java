package com.tensquare.base.dao;

import com.tensquare.base.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author kaixuan
 * @version 1.0
 * @date 2020/1/13 20:36
 * 标签访问数据接口 JpaRepository提供了基本的增删改查 ，JpaSpecificationExecutor用于做复杂的条件查询
 */
public interface LabelDao extends JpaRepository<Label,String>, JpaSpecificationExecutor<Label> {


}
