package com.medic.mediscreen.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

public class Patient {
    @GeneratedValue
    Integer patId;

    String family;
    String given;
    Date dob;
    char sex;
    String address;
    String phone;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Set<PatHistory> patHistories;
}
