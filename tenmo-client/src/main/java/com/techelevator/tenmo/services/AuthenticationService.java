package com.techelevator.tenmo.services;


import com.techelevator.tenmo.model.Balance;
import com.techelevator.tenmo.model.TransferAmount;
import com.techelevator.util.BasicLogger;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.techelevator.tenmo.services.ConsoleService;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.UserCredentials;

import java.math.BigDecimal;

import static com.techelevator.tenmo.App.API_BASE_URL;

public class AuthenticationService {

    private final String baseUrl;
    private final RestTemplate restTemplate = new RestTemplate();

    public AuthenticationService(String url) {
        this.baseUrl = url;
    }

    public AuthenticatedUser login(UserCredentials credentials) {
        HttpEntity<UserCredentials> entity = createCredentialsEntity(credentials);
        AuthenticatedUser user = null;
        try {
            ResponseEntity<AuthenticatedUser> response =
                    restTemplate.exchange(baseUrl + "login", HttpMethod.POST, entity, AuthenticatedUser.class);
            user = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return user;
    }

    public boolean register(UserCredentials credentials) {
        HttpEntity<UserCredentials> entity = createCredentialsEntity(credentials);
        boolean success = false;
        try {
            restTemplate.exchange(baseUrl + "register", HttpMethod.POST, entity, Void.class);
            success = true;
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return success;
    }

    private HttpEntity<UserCredentials> createCredentialsEntity(UserCredentials credentials) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(credentials, headers);
    }

    public double getCurrentBalance(int userId) {
        double balance = 0.0;
        try{
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<Balance> entity = new HttpEntity<>(headers);
            ResponseEntity<Balance> response = restTemplate.exchange(baseUrl+"/getBalance/"+userId, HttpMethod.GET,entity, Balance.class);
            Balance balanceDtoObj = response.getBody();
            if (balanceDtoObj != null){
                balance = balanceDtoObj.getBalance();
            }

        }catch(RestClientResponseException | ResourceAccessException e){
            BasicLogger.log(e.getMessage());
        }
        return balance;
    }



    public boolean sendTransfer(int id, int toUserId, double amount) {
        try{
        TransferAmount transferAmount = new TransferAmount();
        transferAmount.setFromUserId(id);
        transferAmount.setToUserId(toUserId);
        transferAmount.setTransferAmount(amount);
        ResponseEntity<Boolean> response = restTemplate.postForEntity(API_BASE_URL + "transfer", transferAmount, Boolean.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            Boolean transferSuccessful = response.getBody();
            return transferSuccessful != null && transferSuccessful;
        } else {
            System.err.println("Unexpected response: " + response.getStatusCodeValue());
            return false;
        }
    } catch (RestClientResponseException | ResourceAccessException e) {
        BasicLogger.log("Error sending transfer: " + e.getMessage());
        return false;
    }
    }
}
