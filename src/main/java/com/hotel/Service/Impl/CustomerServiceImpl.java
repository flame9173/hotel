package com.hotel.Service.Impl;

import com.hotel.Dao.CustomerDao;
import com.hotel.Entity.Customer;
import com.hotel.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CustomerService")
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerDao customerDao;


    @Override
    public String findone(String idcard, String name) {
        Customer customer = customerDao.queryByIdandName(name,idcard);
        String jsonResult;
        if (customer==null) {
            return "未找到客户";
        }else {
            jsonResult = com.alibaba.fastjson.JSON.toJSONString(customer);
            return jsonResult;
        }
    }

    @Override
    public List<Customer> getCustomerList() {
        return customerDao.queryAll();
    }
}
