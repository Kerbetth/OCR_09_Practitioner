package com.medic.mediscreen.repositories;

import com.medic.mediscreen.domain.PatHistory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@FeignClient()
public interface PatHistoryRepository extends CrudRepository<PatHistory, Long> {
    Optional<PatHistory> findById(Integer id);

}
