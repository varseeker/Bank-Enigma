package com.pascal.bankenigma.repo;

import com.pascal.bankenigma.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, String> {
    public boolean existsByName(String name);
}
