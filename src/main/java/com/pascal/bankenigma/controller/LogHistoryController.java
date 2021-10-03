package com.pascal.bankenigma.controller;

import com.pascal.bankenigma.entity.LogHistory;
import com.pascal.bankenigma.service.LogHistory.LogHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LogHistoryController {
    @Autowired
    LogHistoryService logHistoryService;

    @GetMapping("/logs")
    public List<LogHistory> getAllLogHistories(){
        return logHistoryService.getAllData();
    }
}
