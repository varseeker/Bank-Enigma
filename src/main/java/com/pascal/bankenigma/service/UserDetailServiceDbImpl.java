package com.pascal.bankenigma.service;

import com.pascal.bankenigma.dto.UserDetailImpl;
import com.pascal.bankenigma.entity.UserAccount;
import com.pascal.bankenigma.repo.UserAccountRepository;
import com.pascal.bankenigma.service.UserAccount.UserAccountServiceDbImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceDbImpl implements UserDetailsService {

    @Autowired
    UserAccountRepository userAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        if (!userAccountRepository.findAccountByUserName(userName).isPresent()){
            throw new UsernameNotFoundException("KAGA ADA!!!");
        }
        //Ambil dari akun
        UserAccount account = userAccountRepository.findAccountByUserName(userName).get();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));

        UserDetails userDetails = new UserDetailImpl(account.getUserName(), account.getPassword(), grantedAuthorities);
        return userDetails;
    }
}
