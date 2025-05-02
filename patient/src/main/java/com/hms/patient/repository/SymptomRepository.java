package com.hms.patient.repository;

import com.hms.patient.dtos.treatment.symptom.SymptomDto;
import com.hms.patient.entity.treatment.symptom.SymptomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SymptomRepository extends JpaRepository<SymptomEntity, Integer> {

    @Query("SELECT new com.hms.patient.dtos.treatment.symptom.SymptomDto(" +
            "s.symptomId, s.symptomName, s.symptomDetails) " +
            "FROM SymptomEntity s " +
            "WHERE s.symptomName LIKE %:name%")
    List<SymptomDto> findBySymptomNameContaining(@Param("name") String symptomName);

    @Query("SELECT new com.hms.patient.dtos.treatment.symptom.SymptomDto(" +
            "s.symptomId, s.symptomName, s.symptomDetails) " +
            "FROM SymptomEntity s " +
            "WHERE s.symptomId = :id")
    Optional<SymptomDto> findDtoById(@Param("id") int symptomId);
}