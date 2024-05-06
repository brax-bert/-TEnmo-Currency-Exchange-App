package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Balance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceController {
    @Autowired
    private final UserDao userDao;

    public BalanceController(UserDao userDao) {
        this.userDao = userDao;
    }


    @RequestMapping(value = "/getBalance/{userId}", method = RequestMethod.GET)
    public Balance getBalance(@PathVariable int userId){
        double balanceOfUser = userDao.getBalanceByUserId(userId) ;
        Balance balance = new Balance();
        balance.setBalance(balanceOfUser);
        return balance;
    }


}
