package com.medic.mediscreen.controllers;


import com.medic.mediscreen.domain.PatHistory;
import com.medic.mediscreen.dto.CreatePatHistory;
import com.medic.mediscreen.service.PatHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * -the root of the url give link to login or create an account
 * -userHome url is the main page of connected users
 */

@RestController
public class Controllers {

    @Autowired
    private PatHistoryService patHistoryService;

    @PostMapping("/patHistory/add")
    public void addAPatHistory(@RequestBody CreatePatHistory patHistory) {
        patHistoryService.addAPatHistory(patHistory);
    }

    @RequestMapping("/patHistory/getNotes")
    public List<String> getPatHistories(@RequestParam int id) {
        return patHistoryService.getPatHistories(id);
    }

}
