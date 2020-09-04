package com.medic.mediscreen.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medic.mediscreen.domain.PatHistory;
import com.medic.mediscreen.service.PatHistoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
public class PatHistoryControllerTest {

    @MockBean
    private PatHistoryService patHistoryService;

	@Autowired
	MockMvc mockMvc;


    List<String> patHistories =new ArrayList<>();
    PatHistory patHistory = new PatHistory("a note",1);
    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
	patHistories.add(patHistory.getNote());
    }

    @Test
    public void getAllPatHistories() throws Exception {
        when(patHistoryService.getPatHistories(anyInt())).thenReturn(patHistories);
        mockMvc.perform(get("/patHistory/getNotes")
                .contentType(MediaType.APPLICATION_JSON)
                .param("id","1")
        )
                .andExpect(status().isOk());
    }


	@Test
	public void addingAPatient() throws Exception {
        String json = objectMapper.writeValueAsString(patHistory);
		mockMvc.perform(post("/patHistory/add")
                .param("id","1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
		)
				.andExpect(status().isOk());
	}
}
