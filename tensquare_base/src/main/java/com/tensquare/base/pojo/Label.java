package com.tensquare.base.pojo;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * @author kaixuan
 * @version 1.0
 * @date 2020/1/13 20:17
 */
@Data
@Entity
@Table(name = "tb_label")
public class Label implements Serializable {


    public static final long serialVersionUID=1L;

    @Id
    private String id;
    private String labelname;//标签名称
    private String state;//状态
    private Long count;//使用数量
    private Long fans;//关注数
    private String recommend;//是否推荐



}
