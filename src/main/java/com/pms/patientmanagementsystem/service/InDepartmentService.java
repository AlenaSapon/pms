package com.pms.patientmanagementsystem.service;

import com.pms.patientmanagementsystem.dto.InDepartmentDto;
import com.pms.patientmanagementsystem.dto.converter.InDepartmentDtoConverter;
import com.pms.patientmanagementsystem.exception.InDepartmentNotFoundException;
import com.pms.patientmanagementsystem.helper.message.BusinessLogMessage;
import com.pms.patientmanagementsystem.helper.message.BusinessMessage;
import com.pms.patientmanagementsystem.model.InDepartment;
import com.pms.patientmanagementsystem.repository.InDepartmentRepository;
import com.pms.patientmanagementsystem.request.indepartment.CreateInDepartmentRequest;
import com.pms.patientmanagementsystem.request.indepartment.UpdateInDepartmentRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class InDepartmentService {
    private final InDepartmentRepository inDepartmentRepository;
    private final DepartmentService departmentService;
    private final EmployeeService employeeService;
    private final InDepartmentDtoConverter converter;

    public InDepartmentService(InDepartmentRepository inDepartmentRepository,
                               DepartmentService departmentService,
                               EmployeeService employeeService,
                               InDepartmentDtoConverter converter) {
        this.inDepartmentRepository = inDepartmentRepository;
        this.departmentService = departmentService;
        this.employeeService = employeeService;
        this.converter = converter;
    }

    public void createInDepartment(final CreateInDepartmentRequest request) {
        InDepartment inDepartment = new InDepartment();

        inDepartment.setTimeFrom(request.getTimeFrom());
        inDepartment.setTimeTo(request.getTimeTo());
        inDepartment.setIsActive(true);
        inDepartment.setDepartment(departmentService.findDepartmentByDepartmentId(request.getDepartmentId()));
        inDepartment.setEmployee(employeeService.findEmployeeByEmployeeId(request.getEmployeeId()));

        inDepartmentRepository.save(inDepartment);
        log.info(BusinessLogMessage.InDepartment.IN_DEPARTMENT_CREATED);
    }

    public void updateInDepartment(final String id, final UpdateInDepartmentRequest request) {
        InDepartment inDepartment = findInDepartmentByInDepartmentId(id);

        inDepartment.setTimeFrom(request.getTimeFrom());
        inDepartment.setTimeTo(request.getTimeTo());
        inDepartment.setDepartment(departmentService.findDepartmentByDepartmentId(request.getDepartmentId()));
        inDepartment.setEmployee(employeeService.findEmployeeByEmployeeId(request.getEmployeeId()));

        inDepartmentRepository.save(inDepartment);
        log.info(BusinessLogMessage.InDepartment.IN_DEPARTMENT_UPDATED + id);
    }

    public void deleteInDepartment(final String id) {
        InDepartment inDepartment = findInDepartmentByInDepartmentId(id);

        inDepartment.setIsActive(false);

        inDepartmentRepository.save(inDepartment);
        log.info(BusinessLogMessage.InDepartment.IN_DEPARTMENT_DELETED + id);
    }

    public InDepartmentDto findInDepartmentById(final String id) {
        InDepartment inDepartment = findInDepartmentByInDepartmentId(id);

        log.info(BusinessLogMessage.InDepartment.IN_DEPARTMENT_FOUND + id);
        return converter.convert(inDepartment);
    }

    public List<InDepartmentDto> findAllInDepartments() {
        List<InDepartment> inDepartments = inDepartmentRepository.findAll().stream()
                .filter(x -> x.getIsActive().equals(true))
                .toList();

        if (inDepartments.isEmpty()) {
            log.info(BusinessLogMessage.InDepartment.IN_DEPARTMENT_LIST_EMPTY);
            throw new InDepartmentNotFoundException(BusinessMessage.InDepartment.IN_DEPARTMENT_LIST_EMPTY);
        }

        log.info(BusinessLogMessage.InDepartment.IN_DEPARTMENT_LIST_FOUND);
        return converter.convert(inDepartments);
    }

    protected InDepartment findInDepartmentByInDepartmentId(final String id) {
        return inDepartmentRepository.findById(id)
                .filter(x -> x.getIsActive().equals(true))
                .orElseThrow(() -> {
                    log.error(BusinessLogMessage.InDepartment.IN_DEPARTMENT_NOT_FOUND + id);
                    throw new InDepartmentNotFoundException(BusinessMessage.InDepartment.IN_DEPARTMENT_NOT_FOUND);
                });
    }
}
