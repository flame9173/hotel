package com.hotel.Dao;

import com.hotel.Entity.Completelist;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompletelistDao {
    void insertbill(@Param(value = "roomnumber") int roomnumber,@Param(value = "customername") String customername,@Param(value = "idcard")String idcard,@Param(value = "price")float price,@Param(value = "billtime") String billtime);
    List<Completelist> queryAll();
}
