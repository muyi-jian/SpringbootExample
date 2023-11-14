package spring.boot.mongodb.controller;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.mongodb.dao.UserRepository;
import spring.boot.mongodb.entity.User;

@RequiredArgsConstructor
@RestController
public class MongoController {

    private final MongoTemplate mongoTemplate;

    private final UserRepository userRepository;

    @RequestMapping("/mongo/insert")
    public User insert(@RequestParam("name") String name, @RequestParam("age") int age) {
        // 新增
        User user = new User(RandomUtils.nextInt(), name, age);
        mongoTemplate.insert(user, "YJ");

        // 查询
        Query query = new Query(Criteria.where("name").is(name));
        return mongoTemplate.findOne(query, User.class, "YJ");
    }

    @RequestMapping("/mongo/save")
    public User repoInsert(@RequestParam("name") String name, @RequestParam("age") int age) {
        // 新增
        User user = new User(RandomUtils.nextInt(), name, age);
        userRepository.save(user);

        // 查询
        return userRepository.findByName(name).get(0);
    }
}
