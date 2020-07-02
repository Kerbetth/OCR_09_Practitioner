package com.medic.mediscreen.repositories;

import com.medic.mediscreen.domain.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(name = "mediscreen_patient", url = "localhost:8081")
public interface MicroService_mediscreen_patient {

    @GetMapping(value = "/Patients")
    List<Patient> getPatientList();

    @GetMapping(value = "/Patient/{id}")
    Patient getPatient(@PathVariable("id") int id);
}
