package com.pascal.bankenigma.service.UserData;

import com.pascal.bankenigma.dto.ProfileAccountDto;
import com.pascal.bankenigma.entity.UserData;
import com.pascal.bankenigma.entity.Wallet;
import com.pascal.bankenigma.repo.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserDataServiceDbImpl implements UserDataService{

    @Autowired
    UserDataRepository userDataRepository;


    @Override
    public UserData getDataById(String id) {
        Optional<UserData> customerOptional = isUserDataExist(id);
        return customerOptional.get();
    }

    private Optional<UserData> isUserDataExist(String id) {
        Optional<UserData> customerOptional = userDataRepository.findById(id);
        if (!customerOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found for this user id, please check and try again");
        }
        return customerOptional;
    }

    @Override
    public List<UserData> getAllData() {
        return userDataRepository.findAll();
    }

    @Override
    public UserData createData(UserData userData) {
        if (userDataRepository.existsByAccountNumber(userData.getAccountNumber())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Account Number Already used, Please try another Account Number");
        }
        return userDataRepository.save(userData);
    }

    @Override
    public void deleteData(String id) {

    }

    @Override
    public UserData updateData(UserData userData) {
        return null;
    }

    @Override
    public ProfileAccountDto createData(ProfileAccountDto profileAccountDto) {
        return null;
    }
}
