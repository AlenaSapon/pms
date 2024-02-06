package com.pms.patientmanagementsystem.controller;

import com.pms.patientmanagementsystem.dto.DepartmentDto;
import com.pms.patientmanagementsystem.helper.message.ControllerLogMessage;
import com.pms.patientmanagementsystem.request.department.AddClinicToDepartmentRequest;
import com.pms.patientmanagementsystem.request.department.CreateDepartmentRequest;
import com.pms.patientmanagementsystem.request.department.UpdateClinicToDepartmentRequest;
import com.pms.patientmanagementsystem.request.department.UpdateDepartmentRequest;
import com.pms.patientmanagementsystem.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/departments")
@Slf4j
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<Void> createDepartment(@RequestBody CreateDepartmentRequest request){
        departmentService.createDepartment(request);

        log.info(ControllerLogMessage.Department.DEPARTMENT_CREATE);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDepartment(@PathVariable String id, @RequestBody UpdateDepartmentRequest request){
        departmentService.updateDepartment(id, request);

        log.info(ControllerLogMessage.Department.DEPARTMENT_UPDATE + id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/clinic")
    public ResponseEntity<Void> addClinicToDepartment(@PathVariable String id, @RequestBody AddClinicToDepartmentRequest request){
        departmentService.addClinicToDepartment(id, request);

        log.info(ControllerLogMessage.Department.DEPARTMENT_ADD_CLINIC);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/clinic/update")
    public ResponseEntity<Void> updateClinicToDepartment(@PathVariable String id,
                                                         @RequestBody UpdateClinicToDepartmentRequest request){
        departmentService.updateClinicInDepartment(id, request);

        log.info(ControllerLogMessage.Department.DEPARTMENT_UPDATE_CLINIC);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable String id){
        departmentService.deleteDepartment(id);

        log.info(ControllerLogMessage.Department.DEPARTMENT_DELETE + id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/clinic")
    public ResponseEntity<Void> removeClinicFromDepartment(@PathVariable String id){
        departmentService.removeClinicFromDepartment(id);

        log.info(ControllerLogMessage.Department.DEPARTMENT_REMOVE_CLINIC);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> findDepartmentById(@PathVariable String id){
        DepartmentDto department = departmentService.findDepartmentById(id);

        log.info(ControllerLogMessage.Department.DEPARTMENT_FIND + id);
        return ResponseEntity.ok(department);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> findAllDepartments(){
        List<DepartmentDto> departments = departmentService.findAllDepartments();

        log.info(ControllerLogMessage.Department.DEPARTMENT_FIND_ALL);
        return ResponseEntity.ok(departments);
    }
}
