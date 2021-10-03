package com.pascal.bankenigma.controller;

import com.pascal.bankenigma.dto.TransactionDto;
import com.pascal.bankenigma.entity.LogHistory;
import com.pascal.bankenigma.entity.Transaction;
import com.pascal.bankenigma.service.Transaction.TransactionService;
import com.pascal.bankenigma.service.Transaction.TransactionServiceDbImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {
    @Autowired
    TransactionServiceDbImpl transactionServiceDb;

    @GetMapping("/transactions")
    public List<Transaction> getAllTransactions(){
        return transactionServiceDb.getAllData();
    }

    @PostMapping("/transaction")
    public Transaction createTransaction(@RequestBody TransactionDto transactionDto){
        return transactionServiceDb.createData(transactionDto);
    }

    @DeleteMapping("/transaction")
    public LogHistory delete(@RequestParam(name = "id") String id){
        return transactionServiceDb.deleteDataAndReturnLogHistory(id);
    }
}
