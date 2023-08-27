package com.jsf.onlineshop2.beans;

import com.jsf.onlineshop2.models.Category;
import com.jsf.onlineshop2.services.CategoryService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Named
@ViewScoped
public class CategoryBean implements Serializable {

    @Inject
    private CategoryService categoryService;

    private Category category;
    private String[] categorySelected;

    @PostConstruct
    public void initialize() {
        this.category = new Category();
    }

    public String addCategory() {
        try {
            categoryService.saveCategory(category);
        } catch (Exception e) {
            System.out.println("Error in CategoryBean :: addCategory() method : " + e.getMessage());
        }
        return "addSubCategory";
    }

    public List<Category> getAllCategory() {
        return categoryService.getCategories();
    }

    public List<String> getCategoryNames() {
        return categoryService.findCategoryNameList();
    }
}
