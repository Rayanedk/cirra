package com.example.test.service;

import com.example.test.model.FooterInfo;
import com.example.test.repository.FooterInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FooterInfoService {

    @Autowired
    private FooterInfoRepository footerInfoRepository;

    public FooterInfo getFooterInfo() {
        return footerInfoRepository.findById(1L).orElse(null); // Exemple pour récupérer l'information avec id = 1
    }
}
