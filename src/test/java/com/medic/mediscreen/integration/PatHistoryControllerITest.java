package com.medic.mediscreen.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.medic.mediscreen.domain.PatHistory;
import com.medic.mediscreen.dto.CreatePatHistory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PatHistoryControllerITest {


	@Autowired
	MockMvc mockMvc;
    @Autowired
    MongoTemplate mongoTemplate;


    ObjectMapper objectMapper = new ObjectMapper();
    CreatePatHistory patHistory = new CreatePatHistory();

    @BeforeEach
    void setup() {
        patHistory.setId(1);
        patHistory.setNote("a note");
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mongoTemplate.getDb().drop();
    }

    @Test
    public void getAllPatHistory() throws Exception {
       String result =mockMvc.perform(get("/patHistory/getNotes")
               .param("id", "1")
        )
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        ArrayList<PatHistory> patients = objectMapper.readValue(result, ArrayList.class);
        assertThat(patients).hasSize(0);
    }

	@Test
	public void addingAPatient() throws Exception {
        String json = objectMapper.writeValueAsString(patHistory);
        System.out.println(json);
        mockMvc.perform(post("/patHistory/add")
                .param("id","1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
		)
				.andExpect(status().isOk());
	}

    @Test
    public void getAllPatHistoryAdterAdd() throws Exception {
        String json = objectMapper.writeValueAsString(patHistory);
        System.out.println(json);
        mockMvc.perform(post("/patHistory/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        )
                .andExpect(status().isOk());

        String result =mockMvc.perform(get("/patHistory/getNotes")
                .param("id", "1")
        )
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        ArrayList<PatHistory> patients = objectMapper.readValue(result, ArrayList.class);
        assertThat(patients).hasSize(1);
    }
}
