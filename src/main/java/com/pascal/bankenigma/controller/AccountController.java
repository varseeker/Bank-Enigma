package com.pascal.bankenigma.controller;

import com.pascal.bankenigma.dto.ProfileAccountDto;
import com.pascal.bankenigma.dto.UserCredentials;
import com.pascal.bankenigma.entity.UserAccount;
import com.pascal.bankenigma.service.UserAccount.UserAccountServiceDbImpl;
import com.pascal.bankenigma.service.UserData.UserDataServiceDbImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class AccountController {
    @Autowired
    UserDataServiceDbImpl userDataServiceDb;

    @Autowired
    UserAccountServiceDbImpl userAccountServiceDb;


    @GetMapping("/accounts")
    public List<UserAccount> getAllAccount(){
        return userAccountServiceDb.getAllData();
    }

    @PostMapping("/register")
    public UserAccount registerAccount(@RequestBody ProfileAccountDto profileAccountDto){
        return userAccountServiceDb.createData(profileAccountDto);
    }

    @PostMapping("/signin")
    public Map<String, Object> signInAccount(@RequestBody UserCredentials userCredentials){
        return userAccountServiceDb.signIn(userCredentials);
    }

}
