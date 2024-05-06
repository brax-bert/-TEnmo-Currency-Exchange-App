
package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


    public class UserService {
        private static final String API_BASE_URL = "http://localhost:8080/";
        private final RestTemplate restTemplate = new RestTemplate();

        public List<User> getAllUsers() {
            String url = API_BASE_URL + "users";
            User[] users = restTemplate.getForObject(url, User[].class);
            return Arrays.asList(users);
        }
}
