package com.pascal.bankenigma.repo;

import com.pascal.bankenigma.entity.LogHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogHistoryRepository extends JpaRepository<LogHistory, String> {
}
