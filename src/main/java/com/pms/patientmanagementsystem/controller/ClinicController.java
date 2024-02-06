package com.pms.patientmanagementsystem.controller;

import com.pms.patientmanagementsystem.dto.ClinicDto;
import com.pms.patientmanagementsystem.helper.message.ControllerLogMessage;
import com.pms.patientmanagementsystem.request.clinic.CreateClinicRequest;
import com.pms.patientmanagementsystem.request.clinic.UpdateClinicRequest;
import com.pms.patientmanagementsystem.service.ClinicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clinics")
@Slf4j
public class ClinicController {
    private final ClinicService clinicService;

    public ClinicController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @PostMapping
    public ResponseEntity<Void> createClinic(@RequestBody CreateClinicRequest request){
        clinicService.createClinic(request);

        log.info(ControllerLogMessage.Clinic.CLINIC_CREATE);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateClinic(@PathVariable String id, @RequestBody UpdateClinicRequest request){
        clinicService.updateClinic(id, request);

        log.info(ControllerLogMessage.Clinic.CLINIC_UPDATE + id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClinic(@PathVariable String id){
        clinicService.deleteClinic(id);

        log.info(ControllerLogMessage.Clinic.CLINIC_DELETE + id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicDto> findClinicById(@PathVariable String id){
        ClinicDto clinic = clinicService.findClinicById(id);

        log.info(ControllerLogMessage.Clinic.CLINIC_FIND + id);
        return ResponseEntity.ok(clinic);
    }

    @GetMapping
    public ResponseEntity<List<ClinicDto>> findAllClinics(){
        List<ClinicDto> clinics = clinicService.findAllClinics();

        log.info(ControllerLogMessage.Clinic.CLINIC_FIND_ALL);
        return ResponseEntity.ok(clinics);
    }
}
