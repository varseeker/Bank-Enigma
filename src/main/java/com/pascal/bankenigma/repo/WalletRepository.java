package com.pascal.bankenigma.repo;

import com.pascal.bankenigma.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, String> {

}
