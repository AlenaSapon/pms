package com.pms.patientmanagementsystem.service;

import com.pms.patientmanagementsystem.dto.EmployeeDto;
import com.pms.patientmanagementsystem.dto.converter.EmployeeDtoConverter;
import com.pms.patientmanagementsystem.exception.EmployeeAlreadyExistException;
import com.pms.patientmanagementsystem.exception.EmployeeNotFoundException;
import com.pms.patientmanagementsystem.helper.message.BusinessLogMessage;
import com.pms.patientmanagementsystem.helper.message.BusinessMessage;
import com.pms.patientmanagementsystem.model.Employee;
import com.pms.patientmanagementsystem.repository.EmployeeRepository;
import com.pms.patientmanagementsystem.request.employee.CreateEmployeeRequest;
import com.pms.patientmanagementsystem.request.employee.UpdateEmployeeRequest;
import com.pms.patientmanagementsystem.request.employee.UpdateUserEmployeeRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeDtoConverter converter;

    public EmployeeService(EmployeeRepository employeeRepository,
                           EmployeeDtoConverter converter) {
        this.employeeRepository = employeeRepository;
        this.converter = converter;
    }

    public void createEmployee(final CreateEmployeeRequest request) {
        Employee employee = new Employee();

        checkIfEmployeeExists(request.getUsername());

        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setUsername(request.getUsername());
        employee.setPassword(request.getPassword());
        employee.setEmail(request.getEmail());
        employee.setPhone(request.getPhone());
        employee.setIsActive(true);

        employeeRepository.save(employee);
        log.info(BusinessLogMessage.Employee.EMPLOYEE_CREATED);
    }

    public void updateEmployee(final String id, final UpdateEmployeeRequest request) {
        Employee employee = findEmployeeByEmployeeId(id);

        if (!employee.getUsername().equals(request.getUsername())) {
            checkIfEmployeeExists(request.getUsername());
        }

        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setUsername(request.getUsername());
        employee.setPassword(request.getPassword());
        employee.setEmail(request.getEmail());
        employee.setPhone(request.getPhone());

        employeeRepository.save(employee);
        log.info(BusinessLogMessage.Employee.EMPLOYEE_UPDATED + id);
    }

    public void updateUsername(final String id, final UpdateUserEmployeeRequest request) {
        Employee employee = findEmployeeByEmployeeId(id);

        if (!employee.getUsername().equals(request.getUsername())) {
            checkIfEmployeeExists(request.getUsername());
        }

        employee.setUsername(request.getUsername());
        employee.setPassword(request.getPassword());
        employee.setEmail(request.getEmail());

        employeeRepository.save(employee);
        log.info(BusinessLogMessage.Employee.EMPLOYEE_UPDATED + id);
    }

    public void deleteEmployee(final String id) {
        Employee employee = findEmployeeByEmployeeId(id);

        employee.setIsActive(false);
        employeeRepository.save(employee);
        log.info(BusinessLogMessage.Employee.EMPLOYEE_DELETED + id);
    }

    public EmployeeDto findEmployeeById(final String id) {
        Employee employee = findEmployeeByEmployeeId(id);

        log.info(BusinessLogMessage.Employee.EMPLOYEE_FOUND + id);
        return converter.convert(employee);
    }

    public List<EmployeeDto> findAllEmployees() {
        List<Employee> employees = employeeRepository.findAll().stream()
                .filter(x -> x.getIsActive().equals(true))
                .toList();

        if (employees.isEmpty()) {
            log.error(BusinessLogMessage.Employee.EMPLOYEE_LIST_EMPTY);
            throw new EmployeeNotFoundException(BusinessMessage.Employee.EMPLOYEE_LIST_EMPTY);
        }

        log.info(BusinessLogMessage.Employee.EMPLOYEE_LIST_FOUND);
        return converter.convert(employees);
    }

    protected Employee findEmployeeByEmployeeId(final String id) {
        return employeeRepository.findById(id)
                .filter(x -> x.getIsActive().equals(true))
                .orElseThrow(() -> {
                    log.error(BusinessLogMessage.Employee.EMPLOYEE_NOT_FOUND + id);
                    return new EmployeeAlreadyExistException(BusinessMessage.Employee.EMPLOYEE_NOT_FOUND);
                });
    }

    private void checkIfEmployeeExists(final String username) {
        if (employeeRepository.existsByUsername(username)) {
            log.error(BusinessLogMessage.Employee.EMPLOYEE_ALREADY_EXISTS + username);
            throw new EmployeeAlreadyExistException(BusinessMessage.Employee.EMPLOYEE_ALREADY_EXISTS);
        }
    }
}
