package com.tensquare.spit.service;

import com.tensquare.common.util.IdWorker;
import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * @author kaixuan
 * @version 1.0
 * @date 2020/1/18 15:01
 */
@Service
@Transactional(rollbackFor =Exception.class)
public class SpitService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private SpitDao spitDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MongoTemplate mongoTemplate;


    public List<Spit> findAll() {
        return spitDao.findAll();
    }


    public Spit findById(String id) {
        return spitDao.findById(id).get();
    }


    public void add(Spit spit) {
        spit.set_id(idWorker.nextId()+"");
        spitDao.save(spit);
    }


    public void update(Spit spit) {
        spitDao.save(spit);
    }

    public void deleteById(String id) {
        spitDao.deleteById(id);
    }


    public Page<Spit> findByParentid(String parentid, int page,int rows){
        Pageable pageable = PageRequest.of(page - 1, rows);
        return spitDao.findByParentid(parentid,pageable);
    }


    public String updateThumbup(String id,String userid) {
        String state = null;

        String useridFromDb = (String) redisTemplate.opsForValue().get("thumbup:" + id+userid + ":info");
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();

        if (StringUtils.isNotBlank(useridFromDb)) {
            state = "已取消点赞";
            update.inc("thumbup", -1);
            redisTemplate.delete("thumbup:" + id+userid + ":info");

        }else {
            state = "点赞成功";
            redisTemplate.opsForValue().set("thumbup:" +id+userid + ":info", id);
            update.inc("thumbup", 1);
        }
        mongoTemplate.updateFirst(query, update, "spit");
        return state;
    }
}
