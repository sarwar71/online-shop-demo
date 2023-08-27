package com.jsf.onlineshop2.services;

import com.jsf.onlineshop2.models.Category;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.List;

@Stateless
public class CategoryService implements Serializable {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void saveCategory(Category category) {
        category.setCatName(category.getCatName().toUpperCase());
        entityManager.persist(category);
    }

    public List<Category> getCategories() {
        String GET_ALL_QUERY = "SELECT cat FROM Category cat ORDER BY cat.catId";
        TypedQuery<Category> catQuery = entityManager.createQuery(GET_ALL_QUERY, Category.class);
        return catQuery.getResultList();
    }

    public List<String> findCategoryNameList() {
        String CAT_NAMES_QUERY = "SELECT cat.catName FROM Category cat";
        Query catQuery = entityManager.createQuery(CAT_NAMES_QUERY);
        return catQuery.getResultList();
    }

    public List<Category> findCategoryListByName(String categoryName) {
        String CAT_LIST_QUERY = "SELECT cat FROM Category cat WHERE cat.catName=:category_name";
        Query catQuery = entityManager.createQuery(CAT_LIST_QUERY);
        catQuery.setParameter("category_name", categoryName);
        return catQuery.getResultList();
    }
}
