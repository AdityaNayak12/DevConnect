package com.devconnect.DevConnect.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class HealthController {

    // URL GET /health
    @GetMapping("/health")
    public String getHealth(){
        return "SpringBoot application running healthy";
    }

    // URL GET /api/v1/status
    @GetMapping("/api/v1/status")
    public Map<String, Object> getStatus(){
        Map<String, Object> map = new HashMap<>();
        map.put("status", "OK");
        map.put("Service", "DevConnect");
        map.put("Version", "1.0.0");
        map.put("Time", LocalDateTime.now().toString());

        return map;
    }
}
