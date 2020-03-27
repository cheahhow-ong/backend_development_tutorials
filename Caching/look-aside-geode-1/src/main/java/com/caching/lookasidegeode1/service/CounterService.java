package com.caching.lookasidegeode1.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CounterService {
    private ConcurrentMap<String, AtomicLong> namedCounterMap = new ConcurrentHashMap<>();

    @Cacheable("Counters")
    public long getCachedCount(String counterName) {
        return getCount(counterName);
    }

    @CachePut("Counters")
    public long getCount(String counterName) {

        AtomicLong counter = this.namedCounterMap.get(counterName);

        if (counter == null) {

            counter = new AtomicLong(0L);

            AtomicLong existingCounter = this.namedCounterMap.putIfAbsent(counterName, counter);

            counter = existingCounter != null ? existingCounter : counter;
        }

        return counter.incrementAndGet();
    }

    @CacheEvict("Counters")
    public void resetCounter(String counterName) {
        this.namedCounterMap.remove(counterName);
    }
}
