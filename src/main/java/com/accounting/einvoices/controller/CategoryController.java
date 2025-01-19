package com.accounting.einvoices.controller;

import com.accounting.einvoices.dto.CategoryDTO;
import com.accounting.einvoices.dto.response.wrapper.ResponseWrapper;
import com.accounting.einvoices.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseWrapper> getAllCategory() {
        List<CategoryDTO> categories = categoryService.findAll();
        return ResponseEntity.ok(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Category list is successfully retrieved.")
                .data(categories).build());
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseWrapper> createCategory(@RequestBody CategoryDTO category) {
        CategoryDTO saved = categoryService.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.builder()
                .code(HttpStatus.CREATED.value())
                .success(true)
                .message("Category is successfully created.")
                .data(saved).build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseWrapper> updateCategory(@PathVariable("id") Long id, @RequestBody CategoryDTO category) {
        CategoryDTO updated = categoryService.update(id, category);
        return ResponseEntity.ok(ResponseWrapper.builder()
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Category is successfully updated.")
                .data(updated).build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseWrapper> deleteCategory(@PathVariable("id") Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
