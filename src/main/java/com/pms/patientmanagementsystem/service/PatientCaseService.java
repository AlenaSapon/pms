package com.pms.patientmanagementsystem.service;

import com.pms.patientmanagementsystem.dto.PatientCaseDto;
import com.pms.patientmanagementsystem.dto.converter.PatientCaseDtoConverter;
import com.pms.patientmanagementsystem.exception.PatientCaseNotFoundException;
import com.pms.patientmanagementsystem.helper.DateHelper;
import com.pms.patientmanagementsystem.helper.message.BusinessLogMessage;
import com.pms.patientmanagementsystem.helper.message.BusinessMessage;
import com.pms.patientmanagementsystem.model.PatientCase;
import com.pms.patientmanagementsystem.repository.PatientCaseRepository;
import com.pms.patientmanagementsystem.request.patientcase.CreatePatientCaseRequest;
import com.pms.patientmanagementsystem.request.patientcase.UpdatePatientCaseRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
@Slf4j
public class PatientCaseService {
    private final PatientCaseRepository patientCaseRepository;
    private final PatientService patientService;
    private final PdfService pdfService;
    private final PatientCaseDtoConverter converter;

    public PatientCaseService(PatientCaseRepository patientCaseRepository,
                              PatientService patientService,
                              PdfService pdfService, PatientCaseDtoConverter converter) {
        this.patientCaseRepository = patientCaseRepository;
        this.patientService = patientService;
        this.pdfService = pdfService;
        this.converter = converter;
    }

    public void createPatientCase(final CreatePatientCaseRequest request) {
        PatientCase patientCase = new PatientCase();

        patientCase.setStartTime(DateHelper.getCurrentDateTime());
        patientCase.setAmountPaid(request.getAmountPaid());
        patientCase.setPatient(patientService.findPatientByPatientId(request.getPatientId()));
        patientCase.setInProgress(true);
        patientCase.setTotalCost(0.0);

        patientCaseRepository.save(patientCase);
        log.info(BusinessLogMessage.PatientCase.PATIENT_CASE_CREATED);
    }

    public void updatePatientCase(final String id, final UpdatePatientCaseRequest request) {
        PatientCase patientCase = findPatientCaseByPatientCaseId(id);

        patientCase.setAmountPaid(request.getAmountPaid());
        patientCase.setPatient(patientService.findPatientByPatientId(request.getPatientId()));

        patientCaseRepository.save(patientCase);
        log.info(BusinessLogMessage.PatientCase.PATIENT_CASE_UPDATED + id);
    }

    public void completePatientCase(final String id) {
        PatientCase patientCase = findPatientCaseByPatientCaseId(id);

        patientCase.setEndTime(DateHelper.getCurrentDateTime());
        patientCase.setInProgress(false);
        patientCase.setTotalCost(calculateTotalCost(patientCase));
        patientCaseRepository.save(patientCase);
        log.info(BusinessLogMessage.PatientCase.PATIENT_CASE_COMPLETED + id);
    }

    public ByteArrayInputStream createPatientCaseDocument(final String id) {
        log.info(BusinessLogMessage.PatientCase.PATIENT_CASE_DOCUMENT_CREATED);
        return pdfService.generatePdf(findPatientCaseByPatientCaseId(id));
    }

    public PatientCaseDto findPatientCaseById(final String id) {
        PatientCase patientCase = findPatientCaseByPatientCaseId(id);

        log.info(BusinessLogMessage.PatientCase.PATIENT_CASE_FOUND + id);
        return converter.convert(patientCase);
    }

    public List<PatientCaseDto> findAllPatientCases() {
        List<PatientCase> patientCases = patientCaseRepository.findAll();

        if (patientCases.isEmpty()) {
            log.info(BusinessLogMessage.PatientCase.PATIENT_CASE_LIST_EMPTY);
            throw new PatientCaseNotFoundException(BusinessMessage.PatientCase.PATIENT_CASE_LIST_EMPTY);
        }

        log.info(BusinessLogMessage.PatientCase.PATIENT_CASE_LIST_FOUND);
        return converter.convert(patientCases);
    }

    protected PatientCase findPatientCaseByPatientCaseId(final String id) {
        return patientCaseRepository.findById(id).orElseThrow(() -> {
            log.error(BusinessLogMessage.PatientCase.PATIENT_CASE_NOT_FOUND + id);
            throw new PatientCaseNotFoundException(BusinessMessage.PatientCase.PATIENT_CASE_NOT_FOUND);
        });
    }

    private Double calculateTotalCost(final PatientCase patientCase) {
        final Integer hours = DateHelper.getDifferenceInHours(patientCase.getStartTime(), patientCase.getEndTime());

        return hours * patientCase.getAmountPaid();
    }
}
