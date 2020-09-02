package com.medic.mediscreen.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medic.mediscreen.domain.PatHistory;
import com.medic.mediscreen.dto.CreatePatHistory;
import com.medic.mediscreen.repositories.PatHistoryRepository;
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
    private PatHistoryRepository patHistory_repository;

    @InjectMocks
    private PatHistoryService PatHistoryService = new PatHistoryService();

    CreatePatHistory patHistory = new CreatePatHistory();
    List<PatHistory> patHistories = new ArrayList<>();
    PatHistory patHistory1 = new PatHistory("a note",1);

    @BeforeEach
    void setup() {
        patHistory.setId(1);
        patHistory.setNote("a note");
        patHistories.add(patHistory1);
    }

    @Test
    public void getAllPatHistorys() {
        when(patHistory_repository.findByPatId(anyInt())).thenReturn(patHistories);
        assertThat(PatHistoryService.getPatHistories(1)).hasSize(1);
    }


    @Test
    public void addingAPatHistory() {
        PatHistoryService.addAPatHistory(patHistory);
    }
}
