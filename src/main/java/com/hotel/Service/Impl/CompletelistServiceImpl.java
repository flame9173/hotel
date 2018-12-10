package com.hotel.Service.Impl;

import com.hotel.Dao.CompletelistDao;
import com.hotel.Entity.Completelist;
import com.hotel.Service.CompletelistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CompletelistService")
public class CompletelistServiceImpl implements CompletelistService {
    @Autowired
    CompletelistDao completelistDao;

    @Override
    public List<Completelist> getAllCompletelist() {
        return completelistDao.queryAll();
    }

}
