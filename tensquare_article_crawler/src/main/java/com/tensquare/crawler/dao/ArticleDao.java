package com.tensquare.crawler.dao;

import com.tensquare.crawler.pojo.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ArticleDao extends JpaRepository<Article,String>, JpaSpecificationExecutor<Article> {

    /**
     * 所有非查询操作都要加 @Modifying注解
     * 文章审核
     * @param id
     */
    @Modifying
    @Query(value = "update tb_article set state =1 where id=?",nativeQuery = true)
    public void examine(String id);


    /**
     * 点赞
     * @param id
     * @return
     */
    @Modifying
    @Query(value = "update tb_article set thumbup=thumbup+1 where id=?",nativeQuery = true)
    public int updateThumbup(String id);

}
