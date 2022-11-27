package spring.boot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yangjian
 * @date 2022/11/25 18:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = -4558452511469027511L;
    private int id;
    private String userName;
    @JsonIgnore
    private int age;


    @JsonProperty("birth")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date birthday;

}
