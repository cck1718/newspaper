package com.cck.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article implements Serializable {
    private static final long serialVersionUID = -41512786704400720L;
    
    private Long id;
    
    private String title;
    
    private String content;
    
    private String summary;
    
    private Integer categoryId;
    
    private String isTop;
    
    private String status;
    
    private Integer createBy;
    
    private Date createTime;
    
    private Integer updateBy;
    
    private Date updateTime;

}

