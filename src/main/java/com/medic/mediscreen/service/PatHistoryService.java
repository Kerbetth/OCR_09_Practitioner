package com.medic.mediscreen.service;

import com.medic.mediscreen.domain.PatHistory;
import com.medic.mediscreen.repositories.PatHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * createAccount() method create a new user account with encrypted password and save it in database
 * getAccountInfo() method retrieve name and email from the user
 */

@Service
@Slf4j
public class PatHistoryService {

    @Autowired
    protected PatHistoryRepository patHystoryRepository;

    public void addAPatHistory(PatHistory patHistory) {
        patHystoryRepository.save(patHistory);
    }

}