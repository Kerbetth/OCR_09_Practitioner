package com.medic.mediscreen.controllers;


import com.medic.mediscreen.domain.PatHistory;
import com.medic.mediscreen.service.PatHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * -the root of the url give link to login or create an account
 * -userHome url is the main page of connected users
 */

@Controller
public class HomeControllers {

    @Autowired
    private PatHistoryService patientRepository;

    @RequestMapping("/")
    public String getLog(Model model) {
        return "LogPage";
    }

    @GetMapping("/patHistory/get")
    public String userPage(Model model) {
            return "UserPage";
    }

    @PostMapping("/patHistory/add")
    public String userPage(PatHistory patHistory) {
        patientRepository.addAPatHistory(patHistory);
        return "UserPage";
    }

}
