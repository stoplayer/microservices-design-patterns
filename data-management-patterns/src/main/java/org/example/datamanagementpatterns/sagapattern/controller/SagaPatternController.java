package org.example.datamanagementpatterns.sagapattern.controller;

import org.example.datamanagementpatterns.sagapattern.service.SagaPatternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/saga")
public class SagaPatternController {

    @Autowired
    private SagaPatternService sagaPatternService;

    // Endpoint to handle code modification based on user input and pattern selection
    @PostMapping("/apply")
    public String applySagaPattern(@RequestBody String userCode) {
        return sagaPatternService.modifyCodeWithPattern(userCode);
    }
}
