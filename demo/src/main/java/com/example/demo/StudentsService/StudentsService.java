package com.example.demo.StudentsService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Model.StudentsModel;
import com.example.demo.Repository.Studentsrepository;
@Service
public class StudentsService 
{
    @Autowired
    public Studentsrepository studentsrepository;
    public StudentsModel save(StudentsModel studentsModel){
        return studentsrepository.save(studentsModel);  
    }
    public String delete_by(@RequestParam String id)
    {
        studentsrepository.deleteById(id);
        return "done";
    }
    public List<StudentsModel> findall() {
        return studentsrepository.findAll();
    }
 
}
