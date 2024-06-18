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
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors; 
@RestController
@RequestMapping("/api/reports")
public class ReportController {
    @Autowired
    MovementRepository movementRepository;

    @GetMapping
    public ResponseEntity<List<ReportDTO>> getMethodName(@RequestParam LocalDate start, @RequestParam LocalDate end, @RequestParam String identification) {        
        List<Map<String, Object>> results = movementRepository.getReports(start, end, identification);
        List<ReportDTO> reports = mapResultsToReportDTOs(results);
        return ResponseEntity.ok(reports);
    }
    private List<ReportDTO> mapResultsToReportDTOs(List<Map<String, Object>> results) {
        AtomicInteger index = new AtomicInteger(0);
        return results.stream()
                .map(row -> mapRowToReportDTO(row, index.incrementAndGet()))
                .collect(Collectors.toList());
    }
    private ReportDTO mapRowToReportDTO(Map<String, Object> row, int index) {
        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setIndex(index);
        reportDTO.setDate(((Date) row.get("date")).toLocalDate());
        reportDTO.setClientName((String) row.get("clientName"));
        reportDTO.setAccountNumber((String) row.get("accountNumber"));
        reportDTO.setAccountType((String) row.get("accountType"));
        reportDTO.setInitialBalance((Long) row.get("initialBalance"));
        reportDTO.setState((boolean) row.get("state"));
        reportDTO.setMovementDetail((Long) row.get("movementDetail"));
        reportDTO.setFinalBalance((Long) row.get("finalBalance"));
        return reportDTO;
    }
}
