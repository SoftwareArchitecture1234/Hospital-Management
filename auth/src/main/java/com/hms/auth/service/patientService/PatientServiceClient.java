package com.hms.auth.service.patientService;

import org.springframework.cloud.openfeign.FeignClient;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hms.auth.dto.PatientDto;

@FeignClient(name = "patient-service", url = "${patient-service.url:http://localhost:9091}")
public interface PatientServiceClient {

    @PostMapping("/api/patient/create")
    void createPatient(@RequestBody PatientDto patientDto);

    // @DeleteMapping("/api/{id}")
    // void deletePatient(@PathVariable("id") Integer id);
    
}