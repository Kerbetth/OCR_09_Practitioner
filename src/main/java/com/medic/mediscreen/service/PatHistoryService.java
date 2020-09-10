package com.medic.mediscreen.service;

import com.medic.mediscreen.domain.PatHistory;
import com.medic.mediscreen.exceptions.PatHistoryNotFoundException;
import com.medic.mediscreen.repositories.PatHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * getPatHistories() retrieve all notes from a specific patient according to the id of the patient
 * addAPatHistory() add a new note for a patient
 * setAPatHistory() retrieve a patHistory object according to his id, set the note attribute and save the modification
 */

@Service
@Slf4j
public class PatHistoryService {


    @Autowired
    protected PatHistoryRepository patHistoryRepository;


    public List<PatHistory> getPatHistories(int id) {
        return patHistoryRepository.findByPatId(id);
    }

    public List<String> getNotes(int id) {
        List<String> notes = new ArrayList<>();
        for(PatHistory patHistory: patHistoryRepository.findByPatId(id)){
            notes.add(patHistory.getNote());
        }
        return notes;
    }

    public void addAPatHistory(PatHistory dto) {
        patHistoryRepository.save(dto);
    }

    public void setAPatHistory(PatHistory dto) {
        Optional<PatHistory> patientOptional = patHistoryRepository.findById(dto.getId());
        if (patientOptional.isPresent()) {
            patHistoryRepository.save(dto);
        } else {
            log.info("No patient note found with the id: "+dto.getId());
            throw new PatHistoryNotFoundException("No patient note found with this id, setting canceled");
        }
    }
    public void delAPatHistory(String id) {
        Optional<PatHistory> patientOptional = patHistoryRepository.findById(id);
        if (patientOptional.isPresent()) {
            patHistoryRepository.delete(patientOptional.get());
        } else {
            log.info("No patient note found with the id: "+id);
            throw new PatHistoryNotFoundException("No patient note found with this id, delete canceled");
        }
    }
}