package com.pascal.bankenigma.repo;

import com.pascal.bankenigma.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, String> {
    public boolean existsByAccountNumber(String accountNumber);
}
