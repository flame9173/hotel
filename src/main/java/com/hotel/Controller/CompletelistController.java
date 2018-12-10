package com.hotel.Controller;

import com.google.gson.Gson;
import com.hotel.Entity.Completelist;
import com.hotel.Service.CompletelistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/Completelist")
public class CompletelistController {
    @Autowired
    CompletelistService completelistService;

    @RequestMapping(value = "/QueryAll",method = RequestMethod.GET,produces="text/html; charset=UTF-8")
    public String GetAllCompletelist(){
        List<Completelist> completelists = completelistService.getAllCompletelist();
        Gson gson = new Gson();
        String str = gson.toJson(completelists);
        return str;
    }
}
