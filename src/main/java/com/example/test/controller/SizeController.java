package com.example.test.controller;

import com.example.test.model.*;
import com.example.test.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sizes")
public class SizeController {

    @Autowired private SizeService sizeService;

    @GetMapping("/rings")
    public List<RingSize> getRingSizes() {
        return sizeService.getRingSizes();
    }
}
