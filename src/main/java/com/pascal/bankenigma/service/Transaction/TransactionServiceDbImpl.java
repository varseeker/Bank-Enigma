package com.pascal.bankenigma.service.Transaction;

import com.pascal.bankenigma.dto.TransactionDto;
import com.pascal.bankenigma.entity.*;
import com.pascal.bankenigma.repo.TransactionRepository;
import com.pascal.bankenigma.service.LogHistory.LogHistoryService;
import com.pascal.bankenigma.service.Merchant.MerchantService;
import com.pascal.bankenigma.service.UserData.UserDataService;
import com.pascal.bankenigma.service.Wallet.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceDbImpl implements TransactionService{
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    LogHistoryService logHistoryService;

    @Autowired
    UserDataService userDataService;

    @Autowired
    WalletService walletService;

    @Autowired
    MerchantService merchantService;

    @Override
    public Transaction getDataById(String id) {
        Optional<Transaction> customerOptional = isTransactionExist(id);
        return customerOptional.get();
    }

    private Optional<Transaction> isTransactionExist(String id) {
        Optional<Transaction> customerOptional = transactionRepository.findById(id);
        if (!customerOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found for this transaction id, please check and try again");
        }
        return customerOptional;
    }

    @Override
    public List<Transaction> getAllData() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction createData(Transaction transaction) {
        return null;
    }

    public Transaction createData(TransactionDto transactionDto) {
        Transaction transaction = new Transaction();
        UserData userData = userDataService.getDataById(transactionDto.getUserDataId());
        Wallet wallet = walletService.getDataById(transactionDto.getWalletId());
        Merchant merchant = merchantService.getDataById(transactionDto.getMerchantId());

        transaction.setUserName(userData.getName());
        transaction.setMerchantName(merchant.getName());
        transaction.setDate(new Timestamp(System.currentTimeMillis()));
        transaction.setBill(transactionDto.getBill());

        transaction.setWallet(wallet);
        transaction.setMerchant(merchant);
        transaction.setUserData(userData);
        return transactionRepository.save(transaction);
    }

    @Override
    public void deleteData(String id) {

    }

    public LogHistory deleteDataAndReturnLogHistory(String id) {
        Transaction transaction = getDataById(id);
        Wallet wallet = transaction.getWallet();
        UserData userData = transaction.getUserData();
        Merchant merchant = transaction.getMerchant();

        LogHistory logHistory = new LogHistory();
        logHistory.setActivity("PAYMENT");
        logHistory.setUserName(userData.getName());
        logHistory.setProviderName(merchant.getName());
        logHistory.setInitialBalance(wallet.getBalance());
        logHistory.setUsage(transaction.getBill());

        Integer balance = wallet.getBalance() - transaction.getBill();
        if (balance < 0){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Ur Balance is less than bill, pls top up and try again");
        }
        wallet.setBalance(balance);
        walletService.updateData(wallet);

        logHistory.setFinalBalance(balance);
        logHistory.setDate(new Timestamp(System.currentTimeMillis()));
        transactionRepository.deleteById(id);
        return logHistoryService.createData(logHistory);
    }

    @Override
    public Transaction updateData(Transaction transaction) {
        return null;
    }
}
