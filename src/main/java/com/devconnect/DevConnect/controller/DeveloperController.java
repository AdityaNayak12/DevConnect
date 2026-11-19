package com.devconnect.DevConnect.controller;

import com.devconnect.DevConnect.model.Developer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/developers")
@RestController
public class DeveloperController {

    // In-Memory storage
    // Concurrent HashMap - thread safe hashmap, safe for concurrent requests.

    private final Map <Long, Developer> developers = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    @GetMapping("")
    public List<Developer> getDevelopers(){
        return new ArrayList<>(developers.values());
    }

    //Path Variable
    @GetMapping("/{id}")
    public Developer getDeveloperById(@PathVariable Long id){
        return developers.get(id);
    }

    //Query Params
    @GetMapping("/search")
    public List<Developer> getDevelopersBySkills(
        @RequestParam(required = false) String skill,
        @RequestParam(required = false) String name,
        @RequestParam(defaultValue = "0") int count
    ){
        List<Developer> developerWithSkills = new ArrayList<>();
        for(Developer developer: developers.values()){
            if(developer.getSkills().contains(skill)){
                developerWithSkills.add(developer);
            }
        }

        return developerWithSkills;
    }

    @PostMapping
    public ResponseEntity<Developer> addDeveloper(@RequestBody Developer developer){
        for(Developer existingDeveloper: developers.values()){
            if(existingDeveloper.getEmail().equals(developer.getEmail()) || existingDeveloper.getPhoneNumber().equals(developer.getPhoneNumber())){
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        }
        developer.setId(idCounter.getAndIncrement());
        developers.put(developer.getId(), developer);
        return ResponseEntity.status(HttpStatus.CREATED).body(developer);
    }

    public ResponseEntity<Developer> createDeveloper(@RequestBody Developer developer){
        developer.setId(idCounter.getAndIncrement());
        developers.put(developer.getId(), developer);
        return ResponseEntity.status(HttpStatus.CREATED).body(developer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Developer> replaceDeveloper(@PathVariable Long id, @RequestBody Developer developer){
        if(!developers.containsKey(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        developers.remove(id);
        developer.setId(id);
        developers.put(id, developer);

        return ResponseEntity.status(HttpStatus.OK).body(developer);
    }
}