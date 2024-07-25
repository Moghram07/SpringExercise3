package com.example.day3exquestion2.Controller;

import com.example.day3exquestion2.ApiResponse.ApiResponse;
import com.example.day3exquestion2.Model.Customers;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/customer")
public class BankController {
    ArrayList<Customers> customers = new ArrayList();

    @GetMapping("/get")
    public java.util.ArrayList<Customers> getCustomers(){
        return customers;
    }

    @PostMapping("/add")
    public ApiResponse addCustomer(@RequestBody Customers customer){
        customers.add(customer);
        return new ApiResponse("Customer added", "200");
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateCustomer(@PathVariable int index, @RequestBody Customers customer){
        customers.set(index, customer);
        return new ApiResponse("Customer updated", "200");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteCustomer(@PathVariable int index){
        customers.remove(index);
        return new ApiResponse("Customer deleted", "200");
    }
    @PutMapping("/deposit/{index}/money")
    public ApiResponse depositCustomer(@PathVariable int index, @RequestBody double money) {
        if (index >= 0 && index < customers.size()) {
            Customers customer = customers.get(index);
            customer.setBalance(customer.getBalance() + money);
            return new ApiResponse("Money deposited", "200");
        } else {
            return new ApiResponse("Customer not found", "404");
        }
    }
    @PutMapping("/withdraw/{index}/money")
    public ApiResponse withdrawCustomer(@PathVariable int index, @RequestBody double money) {
        if (index >= 0 && index < customers.size() && money <= customers.get(index).getBalance()) {
            Customers customer = customers.get(index);
            customer.setBalance(customer.getBalance() - money);
            return new ApiResponse("Money deposited", "200");
        } else {
            return new ApiResponse("Customer not found", "404");
        }
    }

}
