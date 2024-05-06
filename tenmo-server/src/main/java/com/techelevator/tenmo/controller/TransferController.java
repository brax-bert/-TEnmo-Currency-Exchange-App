package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.model.TransferAmount;
import com.techelevator.tenmo.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferController {

    @Autowired
    TransferService transferService;

    @RequestMapping(value = "/transferAmount", method = RequestMethod.POST)
    public boolean transferAmount(@RequestBody TransferAmount transferAmount) {
        boolean result =transferService.transferAmount(transferAmount.getFromUserId(), transferAmount.getToUserId(), transferAmount.getTransferAmount());
        return result;

    }

}
