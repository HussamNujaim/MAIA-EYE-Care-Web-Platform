package com.maiacare.serverside.web.controller;

import com.maiacare.serverside.web.serviceImpl.RetinaDiagnosisService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping(value = "/api/retina", produces = MediaType.APPLICATION_JSON_VALUE)
public class RetinaDiagnosisController {
    private final RetinaDiagnosisService retinaDiagnosisService;
    public RetinaDiagnosisController(RetinaDiagnosisService retinaDiagnosisService) {
        this.retinaDiagnosisService = retinaDiagnosisService;
    }
    @GetMapping("/diagnose")
    public ResponseEntity diagnose(@RequestParam String imageUrl) {
        String answer = retinaDiagnosisService.diagnose(imageUrl);
        return ResponseEntity.ok(Collections.singletonMap("result", answer));
    }
}
