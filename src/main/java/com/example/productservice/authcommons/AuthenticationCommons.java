package com.example.productservice.authcommons;


import com.example.productservice.dto.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthenticationCommons {

    private RestTemplate restTemplate;

    public AuthenticationCommons(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDto validateToken(String token){
        UserDto userDto = restTemplate.getForObject("http://localhost:4142/users/validate/"+token, UserDto.class);
        return userDto;
    }
}
