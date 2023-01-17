package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Pagination;
import com.example.demo.Model.StudentsModel;
import com.example.demo.Repository.Studentsrepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentsController {

    @Autowired
    public Studentsrepository studentsrepository;

    @GetMapping("")
    List<StudentsModel> getStudentsModels()
    {
        return studentsrepository.findAll();
    }

    @GetMapping("/getbylastname")
    List<StudentsModel> getstudentswithlastname(@RequestParam String lastName)
    {
        return studentsrepository.findByLastName(lastName);
    }

    @GetMapping("/sort/{field}")
    List<StudentsModel> getstudentswithascendingModels(@PathVariable String field)
    {
        return studentsrepository.findAll(Sort.by(Sort.Direction.ASC,field));
    }

    @GetMapping("/payload")
    Page<StudentsModel> getstudentspaginaList(@RequestBody Pagination pageObj )
    {
        int page = pageObj.getPage();
        int pagesize = pageObj.getPageSize();
        return studentsrepository.findAll(PageRequest.of(page, pagesize));
    }

    @GetMapping("/{offset}/{size}/{field}")
    Page<StudentsModel> getstudentspaginationwithsorting(@PathVariable int size,@PathVariable int offset,@PathVariable String field)
    {
        return studentsrepository.findAll(PageRequest.of(offset, size,Sort.by(field).ascending()));
    }

    @GetMapping("/{size}")
    Page<StudentsModel> getstudentszeropaginatPage(@PathVariable int size)
    {
        return studentsrepository.findAll(PageRequest.ofSize(size));
    }

    @PostMapping("")
    public StudentsModel addStudent(@RequestBody StudentsModel studentsModel)
    {
       return studentsrepository.save(studentsModel);  
    }

    @DeleteMapping("")
    public String deletestudent(@RequestParam String firstName)
    {
        studentsrepository.deleteByFirstName(firstName);
        return "done";
    }

    @PutMapping("")
    public String updatestudent( @RequestBody StudentsModel studentsModel)
    {
        studentsrepository.save(studentsModel);
        return "updated";
    }
}
