package com.example.test.service;

import com.example.test.model.*;
import com.example.test.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeService {

    @Autowired private RingSizeRepository ringRepo;


    public List<RingSize> getRingSizes() {
        return ringRepo.findAll();
    }

}
