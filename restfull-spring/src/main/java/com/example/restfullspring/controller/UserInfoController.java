package com.example.restfullspring.controller;

import com.example.restfullspring.exceptions.ValidationException;
import com.example.restfullspring.model.UserInfo;
import com.example.restfullspring.repository.UserInfoRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@RestController
public class UserInfoController {

    final
    private UserInfoRepository userInfoRepository;

//    private HashData hashData = new HashData();

    public UserInfoController(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @PostMapping(path = "/api/v1/user")
    public Boolean create(@RequestBody Map<String, String> body) throws NoSuchAlgorithmException {
        String username = body.get("username");
        if (userInfoRepository.existsByUsername(username)){

            throw new ValidationException("Username already existed");

        }

        String password = body.get("password");
        String encodedPassword = new BCryptPasswordEncoder().encode(password);
//        String hashedPassword = hashData.get_SHA_512_SecurePassword(password);
        String fullname = body.get("fullname");
        userInfoRepository.save(new UserInfo(username, encodedPassword, fullname));
        return true;
    }

}