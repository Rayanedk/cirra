package com.example.test.controller;

import com.example.test.model.FooterInfo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/footer")

public class FooterController {

    @GetMapping
    public FooterInfo getFooterInfo() {
        return new FooterInfo(
                "Ma Société Inc.",
                "contact@masociete.com",
                "© " + java.time.Year.now().getValue() + " Ma Société Inc. Tous droits réservés."
        );
    }
}
