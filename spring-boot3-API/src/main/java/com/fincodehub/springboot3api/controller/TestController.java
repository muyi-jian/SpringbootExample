package com.fincodehub.springboot3api.controller;


import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

/**
 * @author YangJian
 * @version 1.0.0
 * @title TestController
 * @create 2025/7/6 12:43
 * @description <TODO description class purpose>
 */
@RestController
public class TestController {
    /**
     * http://localhost:8080/user?name=tom
     * @param name 
     * @return
     */
    @GetMapping("/user")
    @ResponseBody()
    public String findUserByName(@RequestParam("name") String name){
        return "这是RequestParam";
    }

    /**
     * http://localhost:8080/user1/tom
     * @param name
     * @return
     */
    @GetMapping("/user1/{name}")
    @ResponseBody()
    public String findUserById(@PathVariable("name") String name){
        return "这是PathVariable";
    }
    /**
     *
     *   http://localhost:8080/books/anyvalue;isbn=1234567890;topN=3/reviews
     *
     * @param isbn
     * @param topN
     * @return java.lang.String
     */
    @GetMapping("/books/{bookId}/reviews")
    @ResponseBody
    public String getBookReviews(
            @MatrixVariable(name = "isbn", required = true) String isbn,
            @MatrixVariable(name = "topN", required = false, defaultValue = "5") Integer topN) {
        System.out.println("ISBN: " + isbn);
        System.out.println("Top N Reviews: " + topN);
        return "这是一个MatrixVariable示例，ISBN: " + isbn + ", TopN: " + topN;
    }
    @PostMapping("/add")
    public String addAccounts(@RequestBody List<String> accounts) throws SQLException {
        System.out.println( accounts);
     return "这是一个RequestBody";
    }
    @GetMapping("/user2")
    @ResponseBody()
    public String getUserList(@RequestHeader("Authorization") String authToken) {
     return "这是RequestHeader";
    }

    @GetMapping("/user3")
    @ResponseBody()
    public String getUserList1(@CookieValue(name = "SessionId") String sessionId) {
     return "这是CookieValue--";
    }
}
