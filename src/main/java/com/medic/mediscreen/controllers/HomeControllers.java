package com.medic.mediscreen.controllers;


import com.medic.mediscreen.domain.PatHistory;
import com.medic.mediscreen.service.PatHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * -the root of the url give link to login or create an account
 * -userHome url is the main page of connected users
 */

@RestController
public class HomeControllers {

    @Autowired
    private PatHistoryService patHistoryService;

    @PostMapping("/patHistory/add")
    public void addAPatHistory(@RequestParam int id, @RequestBody PatHistory patHistory) {
        patHistoryService.addAPatHistory(id, patHistory);
    }

    @RequestMapping("/patHistory")
    public List<PatHistory> getPatHistories(@RequestParam int id) {
        return patHistoryService.getPatHistories(id);
    }

}
