package com.scaler.greivance.service;

import com.scaler.greivance.model.Category;
import com.scaler.greivance.model.Department;
import com.scaler.greivance.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CategoryServiceTest {

    @InjectMocks
    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    private List<Category> getMockCategories() {
        Department department = new Department();
        department.setId(1l);
        department.setName("Dept1");
        department.setDescription("Department Description 1");
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Cat1","desc1",department,new ArrayList<>()));
        categories.add(new Category("Cat2","desc2",department,new ArrayList<>()));
        categories.add(new Category("Cat3","desc3",department,new ArrayList<>()));
        return categories;
    }

    @Test
    void getAllCategories() {
        when(categoryRepository.findAll()).thenReturn(getMockCategories());
        List<Category> result = categoryService.getAllCategories();
        assertEquals(3, result.size());
    }

    @Test
    void getCategoryById() {
        Category category = getMockCategories().get(0);
        category.setId(1l);
        when(categoryRepository.findById(1l)).thenReturn(Optional.of(category));
        Category result = categoryService.getCategoryById(1l);
        assertEquals(1l, result.getId());

        when(categoryRepository.findById(200l)).thenReturn(Optional.ofNullable(null));
        result = categoryService.getCategoryById(200l);
        assertNull(result);
        
    }

    @Test
    void saveCategory() {
    }

    @Test
    void deleteCategoryById() {
    }

    @Test
    void updateCategoryById() {
    }

    @Test
    void getAllCategoriesByDepartmentId() {
    }

    @Test
    void saveCategoryByDepartmentId() {
    }
}