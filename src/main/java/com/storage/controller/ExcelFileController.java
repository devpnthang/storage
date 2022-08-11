package com.storage.controller;

import com.storage.dto.response.MessageResponse;
import com.storage.repository.FileDBRepository;
import com.storage.service.EmployeeService;
import com.storage.service.FileDBService;
import com.storage.utils.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/excel")
@CrossOrigin(origins = "*", maxAge = 3600)
@Transactional
public class ExcelFileController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/upload")
    public ResponseEntity<MessageResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                employeeService.saveExcelEmployee(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse(message));
            }
        }
        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(message));
    }
}