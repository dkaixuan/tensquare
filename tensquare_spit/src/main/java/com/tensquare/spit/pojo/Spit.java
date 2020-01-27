package com.tensquare.spit.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * @author kaixuan
 * @version 1.0
 * @date 2020/1/18 14:58
 */
@Data
public class Spit implements Serializable {
    private static final long serializableUID=1L;


    @Id
    private String _id;
    private String content;
    private String publishtime;
    private String userid;
    private String nickname;
    private Integer visits;
    private Integer thumbup;
    private Integer share;
    private Integer comment;
    private String state;
    private String parentid;

}
