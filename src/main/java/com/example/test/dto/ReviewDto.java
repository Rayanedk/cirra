package com.example.test.dto;

public class ReviewDto {
    private Long productId;
    private int satisfactionLevel;
    private String productSearch;
    private String problemsEncountered;
    private String purchaseFor;
    private String additionalComment;

    // Getters et Setters

    public Long getProductId() {
        return productId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public int getSatisfactionLevel() {
        return satisfactionLevel;
    }
    public void setSatisfactionLevel(int satisfactionLevel) {
        this.satisfactionLevel = satisfactionLevel;
    }
    public String getProductSearch() {
        return productSearch;
    }
    public void setProductSearch(String productSearch) {
        this.productSearch = productSearch;
    }
    public String getProblemsEncountered() {
        return problemsEncountered;
    }
    public void setProblemsEncountered(String problemsEncountered) {
        this.problemsEncountered = problemsEncountered;
    }
    public String getPurchaseFor() {
        return purchaseFor;
    }
    public void setPurchaseFor(String purchaseFor) {
        this.purchaseFor = purchaseFor;
    }
    public String getAdditionalComment() {
        return additionalComment;
    }
    public void setAdditionalComment(String additionalComment) {
        this.additionalComment = additionalComment;
    }
}
