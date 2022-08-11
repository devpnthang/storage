package com.storage.utils;

import com.storage.entity.Employee;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
//    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.worksheet+xml";
    static String[] HEADERs = { "Name", "Salary" };
    static String SHEET = "Employees";

    public static boolean hasExcelFormat(MultipartFile file) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        return extension.equals("xls") || extension.equals("xlsx");
//        return TYPE.equals(file.getContentType());
    }

    public static List<Employee> excelToTutorials(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();
            List<Employee> employees = new ArrayList<>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                Employee employee = new Employee();
                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {
                        case 0:
                            employee.setName(currentCell.getStringCellValue());
                            break;
                        case 1:
                            employee.setSalary(currentCell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                employees.add(employee);
            }
            workbook.close();
            return employees;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
