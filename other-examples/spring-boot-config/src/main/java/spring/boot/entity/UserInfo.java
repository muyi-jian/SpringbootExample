package spring.boot.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author yangjian
 * @date 2022/11/22 18:30
 */

@Component
public class UserInfo {

    @Value("${userinfo.name}")
    private String name;
    @Value("${userinfo.address}")
    private String address;
    @Value("${userinfo.age}")
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
