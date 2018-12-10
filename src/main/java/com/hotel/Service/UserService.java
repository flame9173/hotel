package com.hotel.Service;

import com.hotel.Dto.Exposer;

public interface UserService {
    Exposer login(String account, String password);
}
