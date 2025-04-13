package com.dipak.graal.graal_native.controller_layer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GraalNativeController {
    //say hello for entry point /hello

    @GetMapping("/hello")
    public String hello() {
        return "Hello, GraalVM Native Image!";
    }
}
