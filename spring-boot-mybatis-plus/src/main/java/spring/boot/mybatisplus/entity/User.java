package spring.boot.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
@TableName("user")
public class User {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField(insertStrategy = FieldStrategy.NOT_NULL)
    private String username;

    @TableField(insertStrategy = FieldStrategy.NOT_NULL)
    private Integer age;
}
