package com.jsf.onlineshop.demo.beans;

import com.jsf.onlineshop.demo.models.Category;
import com.jsf.onlineshop.demo.models.SubCategory;
import com.jsf.onlineshop.demo.services.CategoryService;
import com.jsf.onlineshop.demo.services.SubCategoryService;
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
public class SubCategoryBean implements Serializable {

    @Inject
    private SubCategoryService subCategoryService;

    @Inject
    private CategoryService categoryService;

    private Category category;

    private SubCategory subCategory;

    @PostConstruct
    public void initialize() {
        this.category = new Category();
        this.subCategory = new SubCategory();
    }

    public String addSubCategory() {
        try {
            List<Category> categoryList = categoryService.findCategoryListByName(category.getCatName());
            if (!categoryList.isEmpty()) {
                subCategory.setCategory(categoryList.get(0));
                subCategoryService.saveSubCategory(subCategory);
            }
        } catch (Exception e) {
            System.out.println("Error in SubCategoryBean :: addSubCategory() method : " + e.getMessage());
        }
        return "addProduct";
    }

    public List<String> getSubCategoryNames() {
        return subCategoryService.findSubCategoryNameList();
    }

    public List<String> getSubCategoryNamesByCategory() {
        return subCategoryService.findSubCategoryNameListByCategoryName(category.getCatName());
    }
}
