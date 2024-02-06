package com.pms.patientmanagementsystem.request.patientcase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasePatientCaseRequest {
    private Double amountPaid;
    private String patientId;
}
