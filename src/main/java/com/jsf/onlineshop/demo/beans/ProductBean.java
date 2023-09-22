package com.jsf.onlineshop.demo.beans;

import com.jsf.onlineshop.demo.models.Category;
import com.jsf.onlineshop.demo.models.Product;
import com.jsf.onlineshop.demo.models.SubCategory;
import com.jsf.onlineshop.demo.services.ProductService;
import com.jsf.onlineshop.demo.services.SubCategoryService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.Part;
import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Named
@ViewScoped
public class ProductBean implements Serializable {

    @Inject
    private ProductService productService;

    @Inject
    private SubCategoryService subCategoryService;

    private Product product;
    private Category category;
    private SubCategory subCategory;

    private File savedFile;
    private Part uploadedFile;

    private List<String> selectedCategories;
    private List<String> selectedBrands;

    @PostConstruct
    public void initialize() {

        this.product = new Product();
        this.category = new Category();
        this.subCategory = new SubCategory();

        category.setCatName("Select Category");

        this.selectedCategories = new ArrayList<String>();
        this.selectedBrands = new ArrayList<String>();
    }

    public String addProduct() {
        try {
            List<SubCategory> subCategoryList = subCategoryService.findSubCategoryListByName(subCategory.getSubCatName());
            if (!subCategoryList.isEmpty()) {
                product.setSubCategory(subCategoryList.get(0));
                uploadImage();
                productService.saveProduct(product);
            }
        } catch (Exception e) {
            System.out.println("Error in ProductBean :: addProduct() method : " + e.getMessage());
        }
        return "viewProduct";
    }

    public void uploadImage() {
        String location = "/home/sarwar/Downloads/Online-shop-2/src/main/webapp/resources/images/";
        String fileName = Paths.get(uploadedFile.getSubmittedFileName()).getFileName().toString();
        savedFile = new File(location, fileName);

        try (InputStream input = uploadedFile.getInputStream()) {
            if (!savedFile.exists()) {
                Files.copy(input, savedFile.toPath());
            } else {
                System.out.println("Product :: uploadImage() method : " + fileName + " already exist!");
            }
            product.setProductUrl(fileName);
        } catch (IOException e) {
            System.out.println("Error in ProductBean :: uploadImage() method : " + e.getMessage());
        }
    }

    public List<Product> getAllProduct() {

        List<List<String>> selCat = Arrays.asList(selectedCategories);
        List<List<String>> selBrand = Arrays.asList(selectedBrands);

        return productService.getProducts(selCat.get(0), selBrand.get(0));
    }

    public void selectCategory(String category) {
        if (selectedCategories.contains(category)) {
            selectedCategories.remove(category);
        } else {
            selectedCategories.add(category);
        }
    }

    public void selectBrand(String brand) {
        if (selectedBrands.contains(brand)) {
            selectedBrands.remove(brand);
        } else {
            selectedBrands.add(brand);
        }
    }
}
