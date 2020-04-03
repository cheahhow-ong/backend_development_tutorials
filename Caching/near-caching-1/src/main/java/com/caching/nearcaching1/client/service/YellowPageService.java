package com.caching.nearcaching1.client.service;

import com.caching.nearcaching1.client.model.Person;
import com.caching.nearcaching1.client.service.support.AbstractCacheableService;
import com.caching.nearcaching1.client.service.support.EmailGenerator;
import com.caching.nearcaching1.client.service.support.PhoneNumberGenerator;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class YellowPageService extends AbstractCacheableService {

    @Cacheable("YellowPages")
    public Person find(String name) {

        this.cacheMiss.set(true);

        Person person = Person.newPerson(name)
                .withEmail(EmailGenerator.generate(name, null))
                .withPhoneNumber(PhoneNumberGenerator.generate(null));

        simulateLatency();

        return person;
    }

    @CachePut(cacheNames = "YellowPages", key = "#person.name")
    public Person save(Person person, String email, String phoneNumber) {

        if (StringUtils.hasText(email)) {
            person.withEmail(email);
        }

        if (StringUtils.hasText(phoneNumber)) {
            person.withPhoneNumber(phoneNumber);
        }

        return person;
    }

    @CacheEvict(cacheNames = "YellowPages")
    public boolean evict(String name) {
        return true;
    }
}