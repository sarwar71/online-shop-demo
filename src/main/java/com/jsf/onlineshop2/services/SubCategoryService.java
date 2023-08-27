package com.jsf.onlineshop2.services;

import com.jsf.onlineshop2.models.SubCategory;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.List;

@Stateless
public class SubCategoryService implements Serializable {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void saveSubCategory(SubCategory subCategory) {
        subCategory.setSubCatName(subCategory.getSubCatName().toUpperCase());
        entityManager.persist(subCategory);
    }

    public List<String> findSubCategoryNameList() {
        String SUB_CAT_NAMES_QUERY = "SELECT DISTINCT subCat.subCatName FROM SubCategory subCat";
        Query catQuery = entityManager.createQuery(SUB_CAT_NAMES_QUERY);
        return catQuery.getResultList();
    }

    public List<String> findSubCategoryNameListByCategoryName(String catName) {
        String SUB_CAT_NAMES_QUERY = "SELECT subCat.subCatName FROM Category cat, SubCategory subCat " +
                "WHERE cat.catId=subCat.category.catId AND cat.catName=:cat_name";
        Query catQuery = entityManager.createQuery(SUB_CAT_NAMES_QUERY);
        catQuery.setParameter("cat_name", catName);
        return catQuery.getResultList();
    }

    public List<SubCategory> findSubCategoryListByName(String subCategoryName) {
        String CAT_LIST_QUERY = "SELECT subCat FROM SubCategory subCat WHERE subCat.subCatName=:sub_category_name";
        Query catQuery = entityManager.createQuery(CAT_LIST_QUERY);
        catQuery.setParameter("sub_category_name", subCategoryName);
        return catQuery.getResultList();
    }
}
