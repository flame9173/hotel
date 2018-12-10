package com.hotel.Controller;

import com.google.gson.Gson;
import com.hotel.Entity.Customer;
import com.hotel.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/Customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/Query/{idcard}/{name}",method = RequestMethod.GET,produces="text/html; charset=UTF-8")
    public String FindCustomer(@PathVariable("idcard") String idcard,@PathVariable("name") String name){
        return customerService.findone(idcard,name);
    }

    @RequestMapping(value = "/QueryAll",method = RequestMethod.GET,produces="text/html; charset=UTF-8")
    public String GetAllCustomers(){
        List<Customer> customers = customerService.getCustomerList();
        Gson gson = new Gson();
        String str = gson.toJson(customers);
        return str;
    }
}
