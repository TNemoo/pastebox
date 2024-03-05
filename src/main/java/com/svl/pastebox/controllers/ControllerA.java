package com.svl.pastebox.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ControllerA {

    @GetMapping("/")
    public List<String> getAllPublic() {
        return new ArrayList<>();
    }

    @GetMapping("/{hash}")
    public String index(@PathVariable("hash") String s) {
        return s;
    }

    @PostMapping("/")
    public String getLink() {
        return "";
    }
}
