package com.medic.mediscreen.service;

import com.medic.mediscreen.domain.PatHistory;
import com.medic.mediscreen.dto.CreatePatHistory;
import com.medic.mediscreen.repositories.PatHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * createAccount() method create a new user account with encrypted password and save it in database
 * getAccountInfo() method retrieve name and email from the user
 */

@Service
@Slf4j
public class PatHistoryService {


    @Autowired
    protected PatHistoryRepository patHistoryRepository;

    public List<String> getPatHistories(int id) {
        List<String> notes = new ArrayList<>();
        for(PatHistory patHistory: patHistoryRepository.findByPatId(id)){
            notes.add(patHistory.getNote());
        }
        return notes;
    }

    public void addAPatHistory(CreatePatHistory dto) {
        System.out.println(dto.getNote());
        patHistoryRepository.save(new PatHistory(dto.getNote(), dto.getId()));
    }
}