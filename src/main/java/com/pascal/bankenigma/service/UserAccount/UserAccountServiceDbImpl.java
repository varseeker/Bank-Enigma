package com.pascal.bankenigma.service.UserAccount;

import com.pascal.bankenigma.dto.ProfileAccountDto;
import com.pascal.bankenigma.dto.UserCredentials;
import com.pascal.bankenigma.entity.UserAccount;
import com.pascal.bankenigma.entity.UserData;
import com.pascal.bankenigma.repo.UserAccountRepository;
import com.pascal.bankenigma.security.JwtTokenUtil;
import com.pascal.bankenigma.service.UserData.UserDataServiceDbImpl;
import com.pascal.bankenigma.service.UserDetailServiceDbImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserAccountServiceDbImpl implements UserAccountService{

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    UserDetailServiceDbImpl userDetailServiceDb;

    @Autowired
    UserDataServiceDbImpl userDataServiceDb;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserAccount getDataById(String id) {
        return null;
    }

    @Override
    public List<UserAccount> getAllData() {
        return userAccountRepository.findAll();
    }

    @Override
    public UserAccount createData(UserAccount userAccount) {
        return null;
    }

    @Override
    public void deleteData(String id) {

    }

    @Override
    public UserAccount updateData(UserAccount userAccount) {
        return null;
    }

    @Override
    public UserAccount createData(ProfileAccountDto profileAccountDto) {
        if (userAccountRepository.existsByUserName(profileAccountDto.getUserName())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Username already Used, Please use another Username");
        }
        if (userAccountRepository.existsByEmail(profileAccountDto.getEmail())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Email already Used, Please use another Email");
        }
        profileAccountDto.setPassword(passwordEncoder.encode(profileAccountDto.getPassword()));
        UserAccount userAccount = new UserAccount(profileAccountDto.getUserName(), profileAccountDto.getPassword(), profileAccountDto.getEmail());
        UserData userData = new UserData(profileAccountDto.getName(), profileAccountDto.getEmail(), profileAccountDto.getPhoneNumber(), profileAccountDto.getAddress(), profileAccountDto.getMotherName(), profileAccountDto.getAccountNumber());
        userDataServiceDb.createData(userData);
        return userAccountRepository.save(userAccount);

    }

    public Map<String, Object> signIn(UserCredentials userCredentials){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userCredentials.getUserName(), userCredentials.getPassword());

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        UserDetails userDetails = userDetailServiceDb.loadUserByUsername(userCredentials.getUserName());

        String token = jwtTokenUtil.generateToken(userDetails);

        Map<String, Object> tokenWrapper = new HashMap<>();
        tokenWrapper.put("token", token);

        return tokenWrapper;
    }
}
