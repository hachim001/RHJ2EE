package org.example.employeeservice.service;



import org.example.employeeservice.model.Employee;
import org.example.employeeservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;



    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Employee existingEmployee = repository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        existingEmployee.setFirstName(updatedEmployee.getFirstName());
        existingEmployee.setLastName(updatedEmployee.getLastName());
        existingEmployee.setPosition(updatedEmployee.getPosition());
        existingEmployee.setDepartment(updatedEmployee.getDepartment());
        existingEmployee.setSalary(updatedEmployee.getSalary());
        return repository.save(existingEmployee);
    }

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }

    public void deactivateEmployee(Long id) {
        Employee employee = repository.findById(id).orElseThrow();
        employee.setActive(false);
        repository.save(employee);
    }

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "employee-events";

    public Employee addEmployee(Employee employee) {
        employee.setActive(true);
        Employee saved = repository.save(employee);

        // Envoi d'un message Kafka informant qu'un nouvel employé est créé
        kafkaTemplate.send(TOPIC, "Nouvel employé créé: " + saved.getId() + " - " + saved.getFirstName() + " " + saved.getLastName());

        return saved;
    }
}

