package com.caching.crm_geode_2.repositories;

import com.caching.crm_geode_2.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Customer findByNameLike(String name);
}
