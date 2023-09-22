package com.jsf.onlineshop.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pro_id")
    private int productId;

    @Column(name = "pro_name", nullable = false)
    private String productName;

    @Column(name = "pro_qty", nullable = false)
    private int productQty;

    @Column(name = "pro_price", nullable = false)
    private double productPrice;

    @Column(name = "pro_url", nullable = false)
    private String productUrl;

    @Column(name = "pro_desc")
    private String productDesc;

    @ManyToOne
    @JoinColumn(name = "sub_cat_id", nullable = false)
    private SubCategory subCategory;
}
