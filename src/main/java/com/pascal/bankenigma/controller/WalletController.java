package com.pascal.bankenigma.controller;

import com.pascal.bankenigma.dto.WalletDto;
import com.pascal.bankenigma.entity.Wallet;
import com.pascal.bankenigma.service.Wallet.WalletService;
import com.pascal.bankenigma.service.Wallet.WalletServiceDbImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WalletController {
    @Autowired
    WalletServiceDbImpl walletServiceDb;


    @GetMapping("/wallets")
    public List<Wallet> getAllWallet(){
        return walletServiceDb.getAllData();
    }

    @PostMapping("/wallet")
    public Wallet createWallet(@RequestBody Wallet wallet){
        return walletServiceDb.createData(wallet);
    }

    @PostMapping("/menu")
    public Wallet topUpWallet(@RequestBody WalletDto walletDto){
        return walletServiceDb.walletTrancsaction(walletDto);
    }
}
