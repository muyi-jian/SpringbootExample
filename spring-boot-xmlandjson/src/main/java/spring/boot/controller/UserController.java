package spring.boot.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.entity.User;

import java.util.Date;

/**
 * @author yangjian
 * @date 2022/11/25 18:25
 */
@RestController
public class UserController {

    @Autowired
    ObjectMapper mapper;

    @GetMapping("readjsonasobject")
    public String readJsonAsObject() {
        try {
            String json = "{\"id\":1,\"username\":\"mrbird\",\"age\":20,\"birthday\":\"2022-11-25 19:20:47\"}";
            User user = mapper.readValue(json, User.class);
            String name = user.getUserName();
            int age = user.getAge();
            return name + " " + age;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("readjsonstrings")
    public String readJsonStrings() {
        try {
            String json = "{\"id\":1,\"username\":\"mrbird\",\"age\":20,\"birthday\":\"2022-11-25 19:20:47\",\"hobby\":{\"first\":\"sleep\",\"second\":\"eat\"}}";
            JsonNode node = this.mapper.readTree(json);
            JsonNode hobby = node.get("hobby");
            String first = hobby.get("first").asText();
            String second = hobby.get("second").asText();

            return first + " " + second;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("readjsonstring")
    public String readJsonString() {
        try {
            String json = "{\"id\":1,\"username\":\"mrbird\",\"age\":22,\"birthday\":\"2022-11-25 19:21:16\"}";
            JsonNode node = this.mapper.readTree(json);
            String name = node.get("username").asText();
            int age = node.get("age").asInt();
            return name + " " + age;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @GetMapping("/serialization")
    public String serialization() {
        try {
            User user = new User();
            user.setId(1);
            user.setUserName("mrbird");
            user.setAge(22);
            user.setBirthday(new Date());
            String str = mapper.writeValueAsString(user);
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/getuser")
    public User getUser() {
        User user = new User();
        user.setId(1);
        user.setUserName("mrbird");
        user.setAge(20);
        user.setBirthday(new Date());
        System.out.println(user);
        return user;
    }

}
