package com.medic.mediscreen.unit;


import com.medic.mediscreen.domain.PatHistory;
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

    PatHistory patHistory = new PatHistory("a note",1);
    List<com.medic.mediscreen.domain.PatHistory> patHistories = new ArrayList<>();
    com.medic.mediscreen.domain.PatHistory patHistory1 = new com.medic.mediscreen.domain.PatHistory("a note",1);

    @BeforeEach
    void setup() {
        patHistories.add(patHistory1);
    }

    @Test
    public void getAllPatHistory() {
        when(patHistory_repository.findByPatId(anyInt())).thenReturn(patHistories);
        assertThat(PatHistoryService.getNotes(1)).hasSize(1);
    }


    @Test
    public void addingAPatHistory() {
        PatHistoryService.addAPatHistory(patHistory);
    }
}
