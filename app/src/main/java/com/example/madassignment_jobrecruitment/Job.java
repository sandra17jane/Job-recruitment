package com.example.madassignment_jobrecruitment;

public class Job {
    private String title;
    private String description;
    private double salary;
    private String company;

    // Default constructor required for calls to DataSnapshot.getValue(Job.class)
    public Job() {
    }

    public Job(String title, String description, double salary, String company) {
        this.title = title;
        this.description = description;
        this.salary = salary;
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getSalary() {
        return salary;
    }

    public String getCompany() {
        return company;
    }
}
