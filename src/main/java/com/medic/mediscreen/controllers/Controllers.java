package com.medic.mediscreen.controllers;


import com.medic.mediscreen.domain.PatHistory;
import com.medic.mediscreen.service.PatHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * -All the following endpoints execute CRUD request for the PatHistory Object
 */

@RestController
public class Controllers {

    @Autowired
    private PatHistoryService patHistoryService;


    @RequestMapping("/patHistory/getPatHistories")
    public List<com.medic.mediscreen.domain.PatHistory> getPatHistories(@RequestParam int id) {
        return patHistoryService.getPatHistories(id);
    }

    @RequestMapping("/patHistory/getNotes")
    public List<String> getNotes(@RequestParam int id) {
        return patHistoryService.getNotes(id);
    }

    @PostMapping("/patHistory/add")
    public void addAPatHistory(@RequestBody PatHistory patHistory) {
        patHistoryService.addAPatHistory(patHistory);
    }

    @RequestMapping(value = "/patHistory/set")
    void setAPatient(@RequestBody @Valid com.medic.mediscreen.domain.PatHistory patHistory) {
        patHistoryService.setAPatHistory(patHistory);
    }

    @RequestMapping(value = "/patHistory/del")
    void deleteAPatient(@RequestParam String noteId) {
        patHistoryService.delAPatHistory(noteId);
    }
}
