package spring.boot.mongodb;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import spring.boot.mongodb.entity.User;

import java.util.List;

@SpringBootTest
@RequiredArgsConstructor
class SpringBootMongoDbApplicationTests {


    private final MongoTemplate mongoTemplate;

    @Test
    void contextLoads() {
        User user = new User();
        user.setId(1);
        user.setName("TOM");
        user.setAge(20);
        mongoTemplate.save(user);
    }

    @Test
    void find(){
        List<User> all = mongoTemplate.findAll(User.class);
        System.out.println(all);
    }
}
