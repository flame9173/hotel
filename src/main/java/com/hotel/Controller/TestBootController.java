package com.hotel.Controller;

import com.hotel.Entity.User;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@EnableAutoConfiguration
@RequestMapping("/testboot")
public class TestBootController {
    @RequestMapping("getuser")
    public User getUser(){
        User user = new User();
        user.setId(1);
        user.setAccount("asd");
        user.setPassword("aaa");
        user.setName("李");
        return user;
    }
}
