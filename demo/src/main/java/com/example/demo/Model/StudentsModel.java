package com.example.demo.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.annotation.Generated;
import lombok.Data;

@Data
@Document("students")
public class StudentsModel{
    @Id
    String id;
    String firstName;
    String lastName;
}
