package com.devsu.accountmovement.accountmovement.infrastructure.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.accountmovement.accountmovement.infrastructure.repositories.MovementRepository;
import com.devsu.accountmovement.accountmovement.infrastructure.repositories.dto.ReportDTO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    @Autowired
    MovementRepository movementRepository;

    @GetMapping
    public ResponseEntity<List<ReportDTO>> getMethodName(@RequestParam LocalDate start, @RequestParam LocalDate end, @RequestParam String identification) {        
        List<Map<String, Object>> results = movementRepository.getReports(start, end, identification);
        List<ReportDTO> reports = new ArrayList<>();
        int index=0;        
        for (Map<String, Object> row : results) {
            index++;
            LocalDate date = ((Date) row.get("date")).toLocalDate();
            String clientName = (String) row.get("clientName");
            String accountNumber = (String) row.get("accountNumber");
            String accountType = (String) row.get("accountType");
            Long initialBalance = (Long) row.get("initialBalance");
            boolean state = (boolean) row.get("state");
            Long movementDetail = (Long) row.get("movementDetail");
            Long finalBalance = (Long) row.get("finalBalance");            
            ReportDTO report = new ReportDTO(index,date, clientName, accountNumber, accountType, initialBalance, state, movementDetail, finalBalance);
            reports.add(report);
        }        
        return ResponseEntity.ok(reports);
    }
}
