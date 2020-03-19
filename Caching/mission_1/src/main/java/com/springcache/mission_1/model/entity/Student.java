package com.springcache.mission_1.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue
    String id;
    String name;
    String clz;
}
