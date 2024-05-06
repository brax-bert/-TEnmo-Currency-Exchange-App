package com.techelevator.tenmo.service;

import com.techelevator.tenmo.dao.JdbcUserDao;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferService {
    @Autowired
    JdbcUserDao userDao;
    public boolean transferAmount(int fromUserId, int toUserId, double transferAmount){
        //input validation
        if(transferAmount<0 || transferAmount==0){
            return false;
        }

        //validate sender and receiver shouldn't be same
        if(fromUserId == toUserId){
            //return error by saying both cannot be same
            return false;
        }
        //get the current balance of fromUserId i.e. sender
        double senderCurrBal= userDao.getBalanceByUserId(fromUserId);
        if(senderCurrBal<0 || senderCurrBal ==0 || senderCurrBal<transferAmount){
            // return error saying insufficient fund
            return false;
        }

        //deduct the payment from sender account
        boolean result =userDao.updateBalanceForGivenUserId(senderCurrBal-transferAmount , fromUserId);
        if(result==false){
            //transaction failed for sender while deducting the money
            return false;
        }

        //credit the payment to receiver account
        double receiverCurrBal= userDao.getBalanceByUserId(toUserId);
        result = userDao.updateBalanceForGivenUserId(receiverCurrBal+transferAmount, toUserId);
        if(result==false){
            //reverse the sender transaction
            userDao.updateBalanceForGivenUserId(senderCurrBal+transferAmount , fromUserId);
            //failed the transfer
            return false;
        }



        //Record the transfer
        Transfer transfer = new Transfer(fromUserId,toUserId,transferAmount, Constants.TRANSFER_TYPE_REQUEST_ID,Constants.TRANSFER_STATUS_APPROVED_ID);
        userDao.saveTransfer(transfer);

        return true;
    }
}
