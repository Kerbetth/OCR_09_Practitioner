package com.medic.mediscreen.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "PATHISTORY")
@EqualsAndHashCode(of = "id")
public class PatHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String note;
    Integer pat_id;
}
