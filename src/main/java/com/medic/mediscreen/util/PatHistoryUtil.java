package com.medic.mediscreen.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.medic.mediscreen.domain.PatHistory;
import com.medic.mediscreen.repositories.PatHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * this service inject defaut data define in the test_data_init.json file at the beginning of the application
 */

@Service
@Slf4j
public class PatHistoryUtil {

    @Autowired
    private PatHistoryRepository patHistoryRepository;
    private List<PatHistory> patHistoriesInit = new ArrayList<>();
    @Value("${executiveTest}")
    private boolean executiveTest;

    PatHistoryUtil(@Value("${jsonFileName}") String jsonfile) {
            String content = null;
        JsonNode jsonNode = null;
        ObjectMapper mapper = new ObjectMapper();
        BufferedReader bufferedReader = null;

        bufferedReader =  new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/"+jsonfile)));

        try {
                jsonNode = mapper.readTree(bufferedReader.lines().collect(Collectors.joining()));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        log.info("initial json data read");

        patHistoriesInit = mapper.convertValue(
                jsonNode,
                new TypeReference<List<PatHistory>>(){}
        );
    }



    @PostConstruct
    public void injectData() {
        if (executiveTest) {
            log.info("patHistory data from MongoDb cleared");
            patHistoryRepository.deleteAll();
            for (PatHistory dto : patHistoriesInit) {
                patHistoryRepository.save(new com.medic.mediscreen.domain.PatHistory(dto.getNote(), dto.getPatId()));
            }
            log.info("initial data injected in mongoDb");
        }
    }

}