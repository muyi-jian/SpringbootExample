package spring.boot.mongodb.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import spring.boot.mongodb.entity.User;

import java.util.List;

public interface UserRepository extends MongoRepository<User, Long> {

    List<User> findByName(String name);
}
