package com.example.employeeai.service;

import com.example.employeeai.entity.Employee;
import com.example.employeeai.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;
    private final OpenAIService openAIService;

    public EmployeeService(EmployeeRepository repository,
                           OpenAIService openAIService) {

        this.repository = repository;
        this.openAIService = openAIService;
    }

    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    public List<Employee> getAll() {
        return repository.findAll();
    }

    public Employee getById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Employee not found"));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Employee generateSummary(Long id) {

        Employee employee = getById(id);

        String prompt =
                "Generate professional employee summary. " +
                        "Name: " + employee.getName() +
                        ", Department: " + employee.getDepartment() +
                        ", Skills: " + employee.getSkills() +
                        ", Experience: " + employee.getExperience() +
                        ", Project: " + employee.getProjectName();

        String aiSummary =
                openAIService.askAI(prompt);

        employee.setAiSummary(aiSummary);

        return repository.save(employee);
    }
}