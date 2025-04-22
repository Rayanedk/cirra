package com.example.test.model;

import jakarta.persistence.*;

@Entity
@Table(name = "footer_info")
public class FooterInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String companyName;
    private String contactEmail;
    private String copyright;

    // Constructeur
    public FooterInfo(String companyName, String contactEmail, String copyright) {
        this.companyName = companyName;
        this.contactEmail = contactEmail;
        this.copyright = copyright;
    }

    public FooterInfo() {

    }

    // Getters
    public String getCompanyName() { return companyName; }
    public String getContactEmail() { return contactEmail; }
    public String getCopyright() { return copyright; }
}
