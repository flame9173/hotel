package com.hotel.Dao;

import com.hotel.Entity.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerDao {
    void Register(@Param(value = "cname") String cname,@Param(value = "phone") String phone,@Param(value = "idcard") String idcard,@Param(value = "sex") String sex,@Param(value = "age") int age,@Param(value = "roomnumber") int roomnumber);
    void Leave(int roomnumber);
    void ChangeRoom(@Param(value = "cname") String cname,@Param(value = "roomnumber") int roomnumer);
    Customer queryByIdandName(@Param(value = "cname") String canme, @Param(value = "idcard") String idcard);
    List<Customer> queryAll();
}
