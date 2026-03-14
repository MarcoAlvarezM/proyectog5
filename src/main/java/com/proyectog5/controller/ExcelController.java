package com.proyectog5.controller;

import com.proyectog5.service.ProductService;
import com.proyectog5.model.Product;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class ExcelController {

    @Autowired
    private ProductService productService;

    @GetMapping("/export/excel")
    public void exportExcel(HttpServletResponse response) throws IOException {

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition","attachment; filename=inventario.xlsx");

        List<Product> products = productService.getAllProducts();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Inventario");

        Row header = sheet.createRow(0);

        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Nombre");
        header.createCell(2).setCellValue("Cantidad");
        header.createCell(3).setCellValue("Precio");

        int rowCount = 1;

        for(Product p : products){

            Row row = sheet.createRow(rowCount++);

            row.createCell(0).setCellValue(p.getId());
            row.createCell(1).setCellValue(p.getName());
            row.createCell(2).setCellValue(p.getQuantity());
            row.createCell(3).setCellValue(p.getPrice());

        }

        workbook.write(response.getOutputStream());
        workbook.close();
    }
}