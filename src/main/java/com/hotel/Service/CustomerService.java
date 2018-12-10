package com.hotel.Service;

import com.hotel.Entity.Customer;

import java.util.List;

public interface CustomerService {
    String findone(String idcard,String name);
    List<Customer> getCustomerList();
}
