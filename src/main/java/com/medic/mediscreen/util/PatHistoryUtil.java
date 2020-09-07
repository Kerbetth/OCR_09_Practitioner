package com.medic.mediscreen.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medic.mediscreen.domain.PatHistory;
import com.medic.mediscreen.dto.CreatePatHistory;
import com.medic.mediscreen.repositories.PatHistoryRepository;
import com.medic.mediscreen.service.PatHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * createAccount() method create a new user account with encrypted password and save it in database
 * getAccountInfo() method retrieve name and email from the user
 */

@Service
@Slf4j
public class PatHistoryUtil {

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Autowired
    protected PatHistoryService patHistoryService;

    List<CreatePatHistory> patHistoriesInit = new ArrayList<>();

    PatHistoryUtil(@Value("${jsonFileName}") String jsonfile) {
        try {
            FileReader fileString = new FileReader(getClass().getClassLoader().getResource(jsonfile).getFile(), StandardCharsets.UTF_8);
            patHistoriesInit = new ObjectMapper()
                    .readValue(fileString,
                            List.class
                    );
            fileString.close();
            log.info("initial json data read");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @PostConstruct
    public void injectData() {
        log.info("patHistory data cleared");
        System.out.println(patHistoriesInit);
        for (CreatePatHistory dto : patHistoriesInit) {
            patHistoryService.addAPatHistory(dto);
        }
        log.info("initial data injected in mongodb");
    }

}