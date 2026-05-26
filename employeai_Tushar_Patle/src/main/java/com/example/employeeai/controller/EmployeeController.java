package com.example.employeeai.controller;

import com.example.employeeai.entity.Employee;
import com.example.employeeai.service.EmployeeService;
import com.example.employeeai.service.OpenAIService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;
    private final OpenAIService openAIService;

    public EmployeeController(EmployeeService service,
                              OpenAIService openAIService) {

        this.service = service;
        this.openAIService = openAIService;
    }

    @PostMapping
    public Employee save(@RequestBody Employee employee) {
        return service.save(employee);
    }

    @GetMapping
    public List<Employee> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PostMapping("/{id}/summary")
    public Employee generateSummary(@PathVariable Long id) {

        return service.generateSummary(id);
    }

    @GetMapping("/ai")
    public String testAI() {

        return openAIService.askAI(
                "Explain Java Spring Boot in simple words");
    }
}