package com.storage.service;

import com.storage.entity.Employee;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface EmployeeService {
    List<Employee> saveExcelEmployee(MultipartFile file);
}
