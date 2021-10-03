package com.pascal.bankenigma.service.LogHistory;

import com.pascal.bankenigma.entity.LogHistory;
import com.pascal.bankenigma.repo.LogHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class LogHistoryServiceDbImpl implements LogHistoryService{
    @Autowired
    LogHistoryRepository logHistoryRepository;

    @Override
    public LogHistory getDataById(String id) {
        Optional<LogHistory> customerOptional = isLogHistoryExist(id);
        return customerOptional.get();
    }

    private Optional<LogHistory> isLogHistoryExist(String id) {
        Optional<LogHistory> customerOptional = logHistoryRepository.findById(id);
        if (!customerOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found for this logHistory id, please check and try again");
        }
        return customerOptional;
    }

    @Override
    public List<LogHistory> getAllData() {
        return logHistoryRepository.findAll();
    }

    @Override
    public LogHistory createData(LogHistory logHistory) {
        return logHistoryRepository.save(logHistory);
    }

    @Override
    public void deleteData(String id) {

    }

    @Override
    public LogHistory updateData(LogHistory logHistory) {
        return null;
    }
}
