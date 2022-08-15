package com.clineg21.basicapp.controllers.api;

import com.clineg21.basicapp.model.Employee;
import com.clineg21.basicapp.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(path="/api/v1", produces = "application/json")
@CrossOrigin(origins="*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value="/", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> getEmployees() {
        log.info("Returning First Employee");
        return new ResponseEntity<>(getAllEmployees(), HttpStatus.CREATED);
    }

    private List<Employee> getAllEmployees() {
        if(employeeService.getAllEmployees().isEmpty()) {
            log.info("No Employees! Creating An Employee");
            Employee employee = Employee.builder()
                    .firstName("Fox")
                    .last_name("Mulder")
                    .email("fox.mulder@fbi.com")
                    .build();
            log.info("Saving Employee");
            employeeService.saveEmployee(employee);
        }
        return employeeService.getAllEmployees();
    }
}
