package com.example.test.model;

import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private int satisfactionLevel;
    private String productSearch;
    private String problemsEncountered;
    private String purchaseFor;
    private String additionalComment;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


    // Getters & Setters
    public Long getId() { return id; }
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public int getSatisfactionLevel() { return satisfactionLevel; }
    public void setSatisfactionLevel(int satisfactionLevel) { this.satisfactionLevel = satisfactionLevel; }

    public String getProductSearch() { return productSearch; }
    public void setProductSearch(String productSearch) { this.productSearch = productSearch; }

    public String getProblemsEncountered() { return problemsEncountered; }
    public void setProblemsEncountered(String problemsEncountered) { this.problemsEncountered = problemsEncountered; }

    public String getPurchaseFor() { return purchaseFor; }
    public void setPurchaseFor(String purchaseFor) { this.purchaseFor = purchaseFor; }

    public String getAdditionalComment() { return additionalComment; }
    public void setAdditionalComment(String additionalComment) { this.additionalComment = additionalComment; }
}
