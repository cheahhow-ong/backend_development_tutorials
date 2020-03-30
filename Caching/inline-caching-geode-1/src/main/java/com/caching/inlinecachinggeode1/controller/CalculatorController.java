package com.caching.inlinecachinggeode1.controller;

import com.caching.inlinecachinggeode1.model.ResultHolder;
import com.caching.inlinecachinggeode1.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {
    @Autowired
    private CalculatorService calculatorService;

    @GetMapping("/")
    public String home() {
        return format("Inline Caching Example");
    }

    @GetMapping("/ping")
    public String ping() {
        return format("PONG");
    }

    @GetMapping("/calculator/factorial/{number}")
    public String factorial(@PathVariable("number") int number) {

        long t0 = System.currentTimeMillis();

        ResultHolder result = this.calculatorService.factorial(number);

        return toJson(result, System.currentTimeMillis() - t0, this.calculatorService.isCacheMiss());
    }

    @GetMapping("/calculator/sqrt/{number}")
    public String sqrt(@PathVariable("number") int number) {

        long t0 = System.currentTimeMillis();

        ResultHolder result = this.calculatorService.sqrt(number);

        return toJson(result, System.currentTimeMillis() - t0, this.calculatorService.isCacheMiss());
    }

    private String format(Object output) {
        return String.format("<h1>%s</h1>", output);
    }

    private String toJson(ResultHolder result, long latency, boolean cacheMiss) {
        return format(String.format("{ math: %s, latency: %d ms, cacheMiss: %s }", result, latency, cacheMiss));
    }
}
