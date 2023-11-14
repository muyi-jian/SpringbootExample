package com.yj.springboot.entity;

import lombok.*;

import java.io.Serializable;

/**
 * @author yangjian
 * @date 2022/12/6 19:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Student implements Serializable {
    private static final long serialVersionUID = -6819329604028408964L;
    private String sno;
    private String sname;
    private String ssex;
}

