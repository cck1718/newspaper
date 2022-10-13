package com.cck.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = -80297575851957437L;
    
    private Integer id;
    
    private String username;
    
    private String password;
    
    private String phone;
    
    private Date createTime;
    
    private Date updateTime;

}

