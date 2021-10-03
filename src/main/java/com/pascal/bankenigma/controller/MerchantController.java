package com.pascal.bankenigma.controller;

import com.pascal.bankenigma.entity.Merchant;
import com.pascal.bankenigma.service.Merchant.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MerchantController {
    @Autowired
    MerchantService merchantService;

    @GetMapping("/merchants")
    public List<Merchant> getAllMerchant(){
        return merchantService.getAllData();
    }

    @PostMapping("/merchant")
    public Merchant createMerchant(@RequestBody Merchant merchant){
        return merchantService.createData(merchant);
    }
}
