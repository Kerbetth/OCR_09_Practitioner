package com.medic.mediscreen.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medic.mediscreen.domain.PatHistory;
import com.medic.mediscreen.repositories.PatHistory_Repository;
import com.medic.mediscreen.service.PatHistoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
public class PatHistoryServiceTest {

    @Mock
    private PatHistory_Repository patHistory_repository;

    @InjectMocks
    private PatHistoryService PatHistoryService = new PatHistoryService();

    PatHistory patHistory = new PatHistory();
    List<PatHistory> patHistories = new ArrayList<>();
    PatHistory patHistory1 = new PatHistory();
    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        patHistory.setId(1);
        patHistory.setNote("a note");
        patHistories.add(new PatHistory());
    }

    @Test
    public void getAllPatHistorys() {
        when(patHistory_repository.findByPatId(anyInt())).thenReturn(patHistories);
        assertThat(PatHistoryService.getPatHistories(1)).hasSize(1);
    }


    @Test
    public void addingAPatHistory() {
        patHistory1.setId(2);
        patHistory1.setNote("another note");
        PatHistoryService.addAPatHistory(1, patHistory1);
    }
}
