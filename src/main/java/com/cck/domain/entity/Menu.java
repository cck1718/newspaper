package com.cck.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu implements Serializable {
    private static final long serialVersionUID = -16347760816347789L;
    
    private Integer id;
    
    private String name;
    
    private String permission;

}

