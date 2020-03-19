package com.springcache.mission_1.service;

import com.springcache.mission_1.model.entity.Student;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Cacheable("Student")
    public Student getStudentByID(String id) {
        try {
            System.out.println("Going to sleep for 5 secs.. to simulate backend call.");
            Thread.sleep(5000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Student(id, "Sajal", "V");
    }
}
