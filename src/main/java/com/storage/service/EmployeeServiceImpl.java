package com.storage.service;

import com.storage.entity.Employee;
import com.storage.repository.EmployeeRepository;
import com.storage.utils.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<Employee> saveExcelEmployee(MultipartFile file) {
        try {
            List<Employee> employees = ExcelHelper.excelToTutorials(file.getInputStream());
            employeeRepository.saveAll(employees);
            return employees;
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
}
