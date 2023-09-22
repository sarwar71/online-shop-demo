package com.jsf.onlineshop.demo.services;

import com.jsf.onlineshop.demo.models.Product;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.List;

@Stateless
public class ProductService implements Serializable {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void saveProduct(Product product) {
        entityManager.persist(product);
    }

    public List<Product> getProducts(List<String> categories, List<String> brands) {

        String GET_PRODUCT_QUERY = getString(categories, brands);

        TypedQuery<Product> products = entityManager.createQuery(GET_PRODUCT_QUERY, Product.class);

        if (!categories.isEmpty()) {
            products.setParameter("categoryName", categories);
        }

        if (!brands.isEmpty()) {
            products.setParameter("brandName", brands);
        }

        return products.getResultList();
    }

    private static String getString(List<String> categories, List<String> brands) {
        String GET_PRODUCT_QUERY = "SELECT pr FROM Product pr";

        if (!categories.isEmpty() || !brands.isEmpty()) {

            GET_PRODUCT_QUERY += " join pr.subCategory sc join sc.category c";

            if (!categories.isEmpty()) {
                GET_PRODUCT_QUERY += " WHERE c.catName IN :categoryName";
            }

            if (!brands.isEmpty()) {
                if (!categories.isEmpty()) {
                    GET_PRODUCT_QUERY += " and sc.subCatName IN :brandName";
                } else {
                    GET_PRODUCT_QUERY += " WHERE sc.subCatName IN :brandName";
                }
            }
        }
        return GET_PRODUCT_QUERY;
    }
}
