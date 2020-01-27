package com.tensquare.search.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

/**
 * @author kaixuan
 * @version 1.0
 * @date 2020/1/19 10:27
 */
@Data
@Document(indexName ="tensquare_article",type ="article")
public class Article implements Serializable {

    private static final long serializableUID=1L;

    @Id
    private String id;

    //是否索引，就是看该域是否能被搜索
    //是否分词，就是表示搜索的时候是整体匹配还是分词匹配
    //是否存储，就是是否再页面上显示
    @Field(index = true,analyzer ="ik_max_word",searchAnalyzer ="ik_max_word")
    private String title;

    @Field(index = true,analyzer ="ik_max_word",searchAnalyzer ="ik_max_word")
    private String content;

    /**
     * 审核状态
     */
    private String state;




}

