package com.medic.mediscreen.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Getter
@Setter
@Document(collection = "patHistory")
@NoArgsConstructor
public class PatHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String id;
    String note;
    Integer patId;

    public PatHistory(String note, Integer patId) {
        this.note = note;
        this.patId = patId;
    }
}
