package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transaction;
import com.techelevator.tenmo.model.User;
import com.techelevator.util.BasicLogger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

public class UserService {

    public static final String API_BASE_URL = "http://localhost:8080/user/";
    private RestTemplate restTemplate = new RestTemplate();
    private String authToken = null;


    public User[] getAllUsers() {
        User[] user = null;

        try {
            ResponseEntity<User[]> response = restTemplate.exchange(API_BASE_URL, HttpMethod.GET, makeAuthEntity(), User[].class);
            user = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return user;
    }

    public User getUserById(int id) {
        User user = null;
        try {
            ResponseEntity<User> response = restTemplate.exchange(API_BASE_URL, HttpMethod.GET, makeAuthEntity(), User.class);
            user = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return user;
    }

    public int getCurrentUserId() {
        Integer id = null;
        try {
            id = restTemplate.getForObject(API_BASE_URL, Integer.class);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return id;
    }

    public void updateBalance(User updatedUser) {
        try{
            restTemplate.put(API_BASE_URL +, Account.class);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
    }

    private HttpEntity<Void> makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(headers);
    }
}
