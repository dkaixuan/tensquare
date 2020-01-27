package com.tensquare.search.service;

import com.tensquare.common.util.IdWorker;
import com.tensquare.search.dao.ArticleDao;
import com.tensquare.search.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author kaixuan
 * @version 1.0
 * @date 2020/1/19 21:27
 */
@Service
public class ArticleService {


    @Autowired
    private ArticleDao articleDao;



    public void save(Article article) {
        articleDao.save(article);
    }


    public Page<Article> findByTitleLike(String keywords, int page, int size) {

        Pageable pageable = PageRequest.of(page - 1, size);
        return articleDao.findByTitleOrContentLike(keywords, keywords, pageable);
    }
}
