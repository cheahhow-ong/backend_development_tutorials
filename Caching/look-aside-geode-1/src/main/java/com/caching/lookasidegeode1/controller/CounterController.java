package com.caching.lookasidegeode1.controller;

import com.caching.lookasidegeode1.service.CounterService;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CounterController {
    private static final String HEADER_ONE = "<h1>%s</h1>";

    private final CounterService counterService;

    public CounterController(CounterService counterService) {
        Assert.notNull(counterService, "Counter Service is required");
        this.counterService = counterService;
    }

    @GetMapping("/ping")
    public String ping() {
        return String.format(HEADER_ONE, "PONG");
    }

    @GetMapping("counter/{name}")
    public String getCount(@PathVariable("name") String counterName) {
        return String.format(HEADER_ONE, this.counterService.getCount(counterName));
    }

    @GetMapping("counter/{name}/cached")
    public String getCachedCount(@PathVariable("name") String counterName) {
        return String.format(HEADER_ONE, this.counterService.getCachedCount(counterName));
    }

    @GetMapping("counter/{name}/reset")
    public String resetCounter(@PathVariable("name") String counterName) {
        this.counterService.resetCounter(counterName);
        return String.format(HEADER_ONE, "0");
    }
}
