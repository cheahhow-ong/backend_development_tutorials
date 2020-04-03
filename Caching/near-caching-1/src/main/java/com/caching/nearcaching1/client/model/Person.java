package com.caching.nearcaching1.client.model;

import lombok.*;

@Getter
@EqualsAndHashCode(of = "name")
@ToString(of = { "name", "email", "phoneNumber" })
@RequiredArgsConstructor(staticName = "newPerson")
public class Person {

    @NonNull
    private String name;

    private String email;
    private String phoneNumber;

    public Person withEmail(String email) {
        this.email = email;
        return this;
    }

    public Person withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}