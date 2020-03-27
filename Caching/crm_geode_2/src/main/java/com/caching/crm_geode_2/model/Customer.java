package com.caching.crm_geode_2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.Region;

@Region("Customers")
@Data
@ToString(of = "name")
@NoArgsConstructor
@AllArgsConstructor(staticName = "newCustomer")
public class Customer {
    @Id
    private Long id;

    private String name;
}
