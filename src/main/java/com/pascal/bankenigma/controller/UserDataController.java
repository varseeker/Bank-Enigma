package com.pascal.bankenigma.controller;

import com.pascal.bankenigma.entity.UserData;
import com.pascal.bankenigma.service.StorageService;
import com.pascal.bankenigma.service.UserData.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class UserDataController {

    @Autowired
    UserDataService userDataService;

    @Autowired
    StorageService storageService;


    @GetMapping("/users")
    public List<UserData> findAll(){
        return userDataService.getAllData();
    }

    @PostMapping("/user_profile")
    public void upateCustomer(@RequestPart(name = "profilePicture") MultipartFile multipartFile, @RequestPart(name = "id") String id) throws IOException {
        storageService.saveFileTo(multipartFile, id);
    }

}
