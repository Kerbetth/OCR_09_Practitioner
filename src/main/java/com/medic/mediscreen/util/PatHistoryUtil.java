package com.medic.mediscreen.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
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
import java.nio.file.Files;
import java.nio.file.Paths;
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

    @Autowired
    private PatHistoryRepository patHistoryRepository;
    private List<CreatePatHistory> patHistoriesInit = new ArrayList<>();
    @Value("${executiveTest}")
    private boolean executiveTest;

    PatHistoryUtil(@Value("${jsonFileName}") String jsonfile) {
            String content = null;
        JsonNode jsonNode = null;
        ObjectMapper mapper = new ObjectMapper();
            try {
                content = new String(Files.readAllBytes(Paths.get("src/main/resources/"+jsonfile)));
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                jsonNode = mapper.readTree(content);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            log.info("initial json data read");

        patHistoriesInit = mapper.convertValue(
                jsonNode,
                new TypeReference<List<CreatePatHistory>>(){}
        );
    }


    @PostConstruct
    public void injectData() {
        if (executiveTest) {
            log.info("patHistory data from MongoDb cleared");
            patHistoryRepository.deleteAll();
            for (CreatePatHistory dto : patHistoriesInit) {
                patHistoryRepository.save(new PatHistory(dto.getNote(), dto.getId()));
            }
            log.info("initial data injected in mongoDb");
        }
    }

}