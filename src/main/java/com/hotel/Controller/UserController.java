package com.hotel.Controller;

import com.hotel.Dto.Exposer;
import com.hotel.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/User")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/Login/{account}/{password}",method = RequestMethod.GET,produces="text/html; charset=UTF-8")
    public String Login(@PathVariable("account") String account, @PathVariable("password") String password){
        Exposer exposer = userService.login(account,password);
        String jsonResult = com.alibaba.fastjson.JSON.toJSONString(exposer);
        return jsonResult;
    }
}
