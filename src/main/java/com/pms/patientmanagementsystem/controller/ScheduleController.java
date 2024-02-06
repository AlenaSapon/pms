package com.pms.patientmanagementsystem.controller;

import com.pms.patientmanagementsystem.dto.ScheduleDto;
import com.pms.patientmanagementsystem.helper.message.ControllerLogMessage;
import com.pms.patientmanagementsystem.request.schedule.CreateScheduleRequest;
import com.pms.patientmanagementsystem.request.schedule.UpdateScheduleRequest;
import com.pms.patientmanagementsystem.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/schedules")
@Slf4j
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ResponseEntity<Void> createSchedule(@RequestBody CreateScheduleRequest request){
        scheduleService.createSchedule(request);

        log.info(ControllerLogMessage.Schedule.SCHEDULE_CREATE);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSchedule(@PathVariable String id,
                                               @RequestBody UpdateScheduleRequest request){
        scheduleService.updateSchedule(id, request);

        log.info(ControllerLogMessage.Schedule.SCHEDULE_UPDATE);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable String id){
        scheduleService.deleteSchedule(id);

        log.info(ControllerLogMessage.Schedule.SCHEDULE_DELETE);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleDto> findScheduleById(@PathVariable String id){
        ScheduleDto schedule = scheduleService.findScheduleById(id);

        log.info(ControllerLogMessage.Schedule.SCHEDULE_FIND);
        return ResponseEntity.ok(schedule);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleDto>> findAllSchedules(){
        List<ScheduleDto> schedules = scheduleService.findAllSchedules();

        log.info(ControllerLogMessage.Schedule.SCHEDULE_FIND_ALL);
        return ResponseEntity.ok(schedules);
    }
}
