package com.pascal.bankenigma.service.Wallet;

import com.pascal.bankenigma.dto.WalletDto;
import com.pascal.bankenigma.entity.UserData;
import com.pascal.bankenigma.entity.Wallet;
import com.pascal.bankenigma.repo.WalletRepository;
import com.pascal.bankenigma.service.UserData.UserDataServiceDbImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class WalletServiceDbImpl implements WalletService{
    @Autowired
    WalletRepository walletRepository;

    @Autowired
    UserDataServiceDbImpl userDataServiceDb;

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
        return null;
    }


    public Wallet walletTrancsaction(WalletDto walletDto) {
        Wallet wallet = getDataById(walletDto.getWalletId());
        Integer balance = 0;
        switch (walletDto.getMethod()){
            case "TopUp":
                balance = wallet.getBalance() + walletDto.getBalance();
                wallet.setBalance(balance);
                return walletRepository.save(wallet);
            case "Withdraw":
                balance = wallet.getBalance() - walletDto.getBalance();
                wallet.setBalance(balance);
                return walletRepository.save(wallet);
            default:
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fitur tersebut kini belum tersedia, coba lagi nanti");
        }
    }
}
