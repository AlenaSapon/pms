package com.pms.patientmanagementsystem.service;

import com.pms.patientmanagementsystem.dto.PatientDto;
import com.pms.patientmanagementsystem.dto.converter.PatientDtoConverter;
import com.pms.patientmanagementsystem.exception.PatientAlreadyExistException;
import com.pms.patientmanagementsystem.exception.PatientNotFoundException;
import com.pms.patientmanagementsystem.helper.message.BusinessLogMessage;
import com.pms.patientmanagementsystem.helper.message.BusinessMessage;
import com.pms.patientmanagementsystem.model.Patient;
import com.pms.patientmanagementsystem.repository.PatientRepository;
import com.pms.patientmanagementsystem.request.patient.CreatePatientRequest;
import com.pms.patientmanagementsystem.request.patient.UpdatePatientRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientDtoConverter converter;

    public PatientService(PatientRepository patientRepository,
                          PatientDtoConverter converter) {
        this.patientRepository = patientRepository;
        this.converter = converter;
    }

    public void createPatient(final CreatePatientRequest request) {
        Patient patient = new Patient();

        checkIfPatientExists(request.getFirstName(), request.getLastName());

        patient.setFirstName(request.getFirstName());
        patient.setLastName(request.getLastName());

        patientRepository.save(patient);
        log.info(BusinessLogMessage.Patient.PATIENT_CREATED);
    }

    public void updatePatient(final String id, UpdatePatientRequest request) {
        Patient patient = findPatientByPatientId(id);

        if (!patient.getFirstName().equals(request.getFirstName()) || !patient.getLastName().equals(request.getLastName())) {
            checkIfPatientExists(request.getFirstName(), request.getLastName());
        }

        patient.setFirstName(request.getFirstName());
        patient.setLastName(request.getLastName());

        patientRepository.save(patient);
        log.info(BusinessLogMessage.Patient.PATIENT_UPDATED + id);
    }

    public void deletePatient(final String id) {
        patientRepository.delete(findPatientByPatientId(id));
        log.info(BusinessLogMessage.Patient.PATIENT_DELETED + id);
    }

    public PatientDto findPatientById(final String id) {
        Patient patient = findPatientByPatientId(id);

        log.info(BusinessLogMessage.Patient.PATIENT_FOUND + id);
        return converter.convert(patient);
    }

    public List<PatientDto> findAllPatients() {
        List<Patient> patients = patientRepository.findAll();

        if (patients.isEmpty()) {
            log.info(BusinessLogMessage.Patient.PATIENT_LIST_EMPTY);
            throw new PatientNotFoundException(BusinessMessage.Patient.PATIENT_LIST_EMPTY);
        }

        log.info(BusinessLogMessage.Patient.PATIENT_LIST_FOUND);
        return converter.convert(patients);
    }

    protected Patient findPatientByPatientId(final String id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(BusinessLogMessage.Patient.PATIENT_NOT_FOUND + id);
                    return new PatientAlreadyExistException(BusinessMessage.Patient.PATIENT_NOT_FOUND);
                });
    }

    private void checkIfPatientExists(final String firstName, final String latName) {
        if (patientRepository.existsByFirstNameIgnoreCaseAndLastNameIgnoreCase(firstName, latName)) {
            log.error(BusinessLogMessage.Patient.PATIENT_ALREADY_EXISTS + firstName + " " + latName);
            throw new PatientAlreadyExistException(BusinessMessage.Patient.PATIENT_ALREADY_EXISTS);
        }
    }
}
