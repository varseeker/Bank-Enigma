package com.pascal.bankenigma.repo;

import com.pascal.bankenigma.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
    public Optional<UserAccount> findAccountByUserName(String userName);
    public boolean existsByUserName(String userName);
    public boolean existsByEmail(String email);
}
