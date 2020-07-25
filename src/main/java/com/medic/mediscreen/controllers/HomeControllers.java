package com.medic.mediscreen.controllers;


import com.medic.mediscreen.domain.PatHistory;
import com.medic.mediscreen.service.PatHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * -the root of the url give link to login or create an account
 * -userHome url is the main page of connected users
 */

@Controller
public class HomeControllers {

    @Autowired
    private PatHistoryService patHistoryService;


    @GetMapping("/patHistory/add/{patId}")
    public void addAPatHistory(@PathVariable("patId") int patId, @RequestParam String e) {
        patHistoryService.addAPatHistory(patId, e);
    }

    @PostMapping("/patHistory/get/{patId}")
    public List<PatHistory> userPage(@PathVariable("patId") int patId) {
        return patHistoryService.getPatHistories(patId);
    }

}
