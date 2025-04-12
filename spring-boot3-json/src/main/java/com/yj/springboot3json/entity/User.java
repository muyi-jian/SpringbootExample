package com.yj.springboot3json.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author YangJian
 * @version 1.0.0
 * @title User
 * @create 2025/4/12 21:14
 * @description <TODO description class purpose>
 */
@Data
@NoArgsConstructor
public class User {
    private Long id;
    @NotNull
    @JsonProperty(value = "username", required = true)
    @Size(min = 5, max = 10)
    private String name;
    @NotNull
    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
