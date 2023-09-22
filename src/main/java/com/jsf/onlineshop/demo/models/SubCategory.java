package com.jsf.onlineshop.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sub_categories")
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_cat_id")
    private int subCatId;

    @Column(name = "sub_cat_name", nullable = false)
    private String subCatName;

    @Column(name = "sub_cat_desc")
    private String subCatDesc;

    @ManyToOne
    @JoinColumn(name = "cat_id", nullable = false)
    private Category category;
}
