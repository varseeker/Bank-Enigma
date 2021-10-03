package com.pascal.bankenigma.service.Wallet;

import com.pascal.bankenigma.dto.WalletDto;
import com.pascal.bankenigma.entity.LogHistory;
import com.pascal.bankenigma.entity.UserData;
import com.pascal.bankenigma.entity.Wallet;
import com.pascal.bankenigma.repo.WalletRepository;
import com.pascal.bankenigma.service.LogHistory.LogHistoryService;
import com.pascal.bankenigma.service.UserData.UserDataServiceDbImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class WalletServiceDbImpl implements WalletService{
    @Autowired
    WalletRepository walletRepository;

    @Autowired
    UserDataServiceDbImpl userDataServiceDb;

    @Autowired
    LogHistoryService logHistoryService;

    @Override
    public Wallet getDataById(String id) {
        Optional<Wallet> customerOptional = isWalletExist(id);
        return customerOptional.get();
    }

    private Optional<Wallet> isWalletExist(String id) {
        Optional<Wallet> customerOptional = walletRepository.findById(id);
        if (!customerOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found for this wallet id, please check and try again");
        }
        return customerOptional;
    }

    @Override
    public List<Wallet> getAllData() {
        return walletRepository.findAll();
    }

    @Override
    public Wallet createData(Wallet wallet) {
        UserData userData = userDataServiceDb.getDataById(wallet.getUserDataId());
        wallet.setUserData(userData);
        return walletRepository.save(wallet);
    }

    @Override
    public void deleteData(String id) {

    }

    @Override
    public Wallet updateData(Wallet wallet) {
        return walletRepository.save(wallet);
    }


    public Wallet walletTrancsaction(WalletDto walletDto) {
        Wallet wallet = getDataById(walletDto.getWalletId());
        LogHistory logHistory = new LogHistory();
        Integer balance = 0;

        switch (walletDto.getMethod()){
            case "TopUp":

                logHistory.setActivity("TOP UP");
                setLogHistory(walletDto, wallet, logHistory);

                balance = wallet.getBalance() + walletDto.getBalance();
                setFinalBalance(wallet, logHistory, balance);
                return walletRepository.save(wallet);
            case "Withdraw":
                logHistory.setActivity("WITHDRAW");
                balance = wallet.getBalance() - walletDto.getBalance();
                if (balance < 0){
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Your balance is less than ur request, pls try again");
                }
                setFinalBalance(wallet, logHistory, balance);
                return walletRepository.save(wallet);
            default:
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fitur tersebut kini belum tersedia, coba lagi nanti");
        }
    }

    private void setFinalBalance(Wallet wallet, LogHistory logHistory, Integer balance) {
        logHistory.setFinalBalance(balance);
        logHistoryService.createData(logHistory);
        wallet.setBalance(balance);
    }

    private void setLogHistory(WalletDto walletDto, Wallet wallet, LogHistory logHistory) {
        logHistory.setProviderName(wallet.getProviderName());
        logHistory.setUserName(wallet.getUserData().getName());
        logHistory.setInitialBalance(wallet.getBalance());
        logHistory.setUsage(walletDto.getBalance());
        logHistory.setDate(new Timestamp(System.currentTimeMillis()));
    }
}
