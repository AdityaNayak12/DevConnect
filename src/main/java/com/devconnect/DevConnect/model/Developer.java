package com.devconnect.DevConnect.model;

import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Developer {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String bio;
    private String city;
    private String linkedInUrl;
    private String githubUrl;
    private List<String> skills;
    private String organization;
    private final LocalDateTime createdAt;

    public Developer() {
            createdAt = LocalDateTime.now();
        }

    public Developer(Long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        createdAt = LocalDateTime.now(); 
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<String> getSkills() {
        return skills;
    }

    // Is this method safe?
    public void setSkills(List<String> skills) {
        if (skills == null) {
            this.skills = skills;
        }
    }

    public void addSkill(String skill) {
        if (skills == null) {
            skills = new ArrayList<>();
        }
        this.skills.add(skill);
    }

    public void addSkills(List<String> skills) {
        if (skills == null) {
            this.skills = skills;
        } else {
            this.skills.addAll(skills);
        }
    }

    public String getLinkedInUrl() {
        return linkedInUrl;
    }

    public void setLinkedInUrl(String linkedInUrl) {
        this.linkedInUrl = linkedInUrl;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubInURL(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
