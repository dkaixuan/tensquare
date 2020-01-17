package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tensquare.common.util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kaixuan
 * @version 1.0
 * @date 2020/1/13 20:38
 * 标签业务逻辑类
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LabelService {

    @Autowired
    private LabelDao labelDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询所有标签
     * @return
     */
    public List<Label> findAll() {
        return labelDao.findAll();
    }


    /**
     * 根据ID查询标签
     * @param id
     * @return
     */
    public Label findById(String id) {
        return labelDao.findById(id).get();
    }


    /**
     * 增加标签
     * @param label
     */
    public void add(Label label) {
        label.setId(idWorker.nextId() + "");
        labelDao.save(label);
    }

    /**
     * 删除标签
     * @param id
     */
    public void deleteById(String id) {
        labelDao.deleteById(id);
    }


    /**
     * 修改标签
     * @param label
     */
    public void update(Label label) {
        labelDao.save(label);
    }


    public List<Label> findSearch(Label label) {
       return labelDao.findAll(new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                if (StringUtils.isNotBlank(label.getLabelname())) {
                    Predicate predicate = cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    list.add(predicate);
                }

                if (StringUtils.isNotBlank(label.getState())) {
                    Predicate predicate = cb.equal(root.get("state").as(String.class), label.getState());
                    list.add(predicate);
                }
                //new 一个数组作为最终返回值的条件
                Predicate[] parr = new Predicate[list.size()];
                //把list直接转成数组
                list.toArray(parr);
                return cb.and(parr);
            }
        });

    }

    public Page<Label> pageQuery(Label label, int page, int size) {
        //封装分页对象
        Pageable pageable = PageRequest.of(page - 1, size);
       return labelDao.findAll(new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                if (StringUtils.isNotBlank(label.getLabelname())) {
                    Predicate predicate = cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    list.add(predicate);
                }

                if (StringUtils.isNotBlank(label.getState())) {
                    Predicate predicate = cb.equal(root.get("state").as(String.class), label.getState());
                    list.add(predicate);
                }
                //new 一个数组作为最终返回值的条件
                Predicate[] parr = new Predicate[list.size()];
                //把list直接转成数组
                list.toArray(parr);
                return cb.and(parr);
            }
        }, pageable);

    }

}
