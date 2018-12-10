package com.hotel.Dao;

import com.hotel.Entity.User;

public interface UserDao {
    User queryByAccount(String account);
}
