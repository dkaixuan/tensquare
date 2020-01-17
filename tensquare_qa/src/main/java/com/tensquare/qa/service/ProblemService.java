package com.tensquare.qa.service;

import com.tensquare.qa.dao.ProblemDao;
import com.tensquare.qa.pojo.Problem;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author kaixuan
 * @version 1.0
 * @date 2020/1/17 15:46
 */
@Service
@Transactional(rollbackFor =Exception.class)
public class ProblemService {

    @Autowired
    private ProblemDao problemDao;


    public Page<Problem> findNewListByLabelId(String labelId, int page,int rows){
        Pageable pageable= PageRequest.of(page-1,rows);
        return problemDao.findNewListByLabelId(labelId, pageable);
    }

    public Page<Problem> findHotListByLabelId(String labelId, int page,int rows){
        Pageable pageable = PageRequest.of(page - 1, rows);
        return problemDao.findHotListByLabelId(labelId, pageable);
    }

    public Page<Problem> findWaitListByLabelId(String labelId, int page,int rows){
        Pageable pageable = PageRequest.of(page - 1, rows);
        return problemDao.findWaitListByLabelId(labelId, pageable);
    }

    public List<Problem> findAll() {
        return problemDao.findAll();
    }


    public Problem findById(String id) {
        return problemDao.findById(id).get();
    }

    public Page<Problem> findSearchPage(Map searchMap, int page, int size) {
        Specification<Problem> specification = createSpecification(searchMap);
        Pageable pageable = PageRequest.of(page - 1, size);
       return problemDao.findAll(specification, pageable);
    }



    public List<Problem> findSearchList(Map map) {
        Specification<Problem> specification = createSpecification(map);
        return problemDao.findAll(specification);
    }


    public void update(Problem problem) {
        problemDao.save(problem);
    }

    public void deleteById(String id) {
        problemDao.deleteById(id);
    }


    /**
     * 抽取条件查询
     * @param searchMap
     * @return
     */
    private Specification<Problem> createSpecification(Map searchMap) {
        return new Specification<Problem>() {
            @Override
            public Predicate toPredicate(Root<Problem> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<>();

                if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                    cb.like(root.get("id").as(String.class), "%" + (String) searchMap.get("id") + "%");
                }
                // 标题
                if (searchMap.get("title")!=null && !"".equals(searchMap.get("title"))) {
                    predicateList.add(cb.like(root.get("title").as(String.class), "%"+(String)searchMap.get("title")+"%"));
                }
                // 内容
                if (searchMap.get("content")!=null && !"".equals(searchMap.get("content"))) {
                    predicateList.add(cb.like(root.get("content").as(String.class), "%"+(String)searchMap.get("content")+"%"));
                }
                // 用户ID
                if (searchMap.get("userid")!=null && !"".equals(searchMap.get("userid"))) {
                    predicateList.add(cb.like(root.get("userid").as(String.class), "%"+(String)searchMap.get("userid")+"%"));
                }
                // 昵称
                if (searchMap.get("nickname")!=null && !"".equals(searchMap.get("nickname"))) {
                    predicateList.add(cb.like(root.get("nickname").as(String.class), "%"+(String)searchMap.get("nickname")+"%"));
                }
                // 是否解决
                if (searchMap.get("solve")!=null && !"".equals(searchMap.get("solve"))) {
                    predicateList.add(cb.like(root.get("solve").as(String.class), "%"+(String)searchMap.get("solve")+"%"));
                }
                // 回复人昵称
                if (searchMap.get("replyname")!=null && !"".equals(searchMap.get("replyname"))) {
                    predicateList.add(cb.like(root.get("replyname").as(String.class), "%"+(String)searchMap.get("replyname")+"%"));
                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }


}
