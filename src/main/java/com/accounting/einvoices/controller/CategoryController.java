package com.accounting.einvoices.controller;

import com.accounting.einvoices.dto.CategoryDTO;
import com.accounting.einvoices.dto.response.ResponseWrapper;
import com.accounting.einvoices.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseWrapper> getAllCategory(){
        List<CategoryDTO> categories = categoryService.findAll();
        return ResponseEntity.ok(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Category list is successfully retrieved.")
                .data(categories).build());
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseWrapper> createCategory(@RequestBody CategoryDTO category){
        CategoryDTO saved = categoryService.create(category);
        return ResponseEntity.ok(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Category list is successfully retrieved.")
                .data(saved).build());
    }
}
