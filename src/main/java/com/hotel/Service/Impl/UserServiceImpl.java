package com.hotel.Service.Impl;

import com.hotel.Dao.UserDao;
import com.hotel.Dto.Exposer;
import com.hotel.Entity.User;
import com.hotel.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service("UserService")
public class UserServiceImpl implements UserService {
    private final String salt = "eqowjheruiqw#214&^&^&JEkfew.26";

    @Autowired
    private UserDao userDao;

    private String getMD5(String password){
        String base = password + "*" + salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    @Override
    public Exposer login(String account, String password) {
        User user = userDao.queryByAccount(account);
        if (user!=null) {
            if (getMD5(password).equals(user.getPassword())) {
                return new Exposer(true,account,user.getName());
            } else {
                return new Exposer(false,account,"null");
            }
        }
        else return new Exposer(false,account,"null");
    }
}
