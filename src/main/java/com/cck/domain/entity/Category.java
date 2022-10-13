package com.cck.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {
    private static final long serialVersionUID = -35362928837821818L;
    
    private Integer id;
    
    private String name;

}

