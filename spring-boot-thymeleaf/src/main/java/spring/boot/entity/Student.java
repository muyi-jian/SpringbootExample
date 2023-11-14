package spring.boot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author yangjian
 * @date 2022/11/23 18:17
 */
@Data
@AllArgsConstructor
public class Student {
    private String name;
    private int age;
    private String email;
    private String phone;
}