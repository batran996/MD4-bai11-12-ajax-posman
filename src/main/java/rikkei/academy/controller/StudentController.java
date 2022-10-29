package rikkei.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rikkei.academy.dto.ResponseMessage;
import rikkei.academy.model.Student;
import rikkei.academy.service.IStudentService;

import java.util.Optional;

@RestController
@RequestMapping("students")
//ghep ket noi giua 2 cong fun vaf back
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    private IStudentService studentService;


    @GetMapping
    public ResponseEntity<?> showListStudent() {
        return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        if (student.getName().trim().equals("") || student.getName().length() < 3 || student.getName().length() > 20) {
            return new ResponseEntity<>(new ResponseMessage("name_invalid"), HttpStatus.OK);
        }
        if (studentService.existsByName(student.getName())) {
            return new ResponseEntity<>(new ResponseMessage("name_exited"), HttpStatus.OK);
        }
        studentService.save(student);
        return new ResponseEntity<>(new ResponseMessage("create_success"), HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> showStudentBypage(@PageableDefault(sort = "id", size = 4) Pageable pageable) {
        return new ResponseEntity<>(studentService.findAll(pageable), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> detailStudent(@PathVariable Long id) {
        if (!studentService.findById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(studentService.findById(id).get(), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id,@RequestBody Student student){
        Optional<Student> student1 = studentService.findById(id);
        if(!student1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (student.getName().trim().equals("") || student.getName().length() < 3 || student.getName().length() > 10) {
            return new ResponseEntity<>(new ResponseMessage("name_invalid"), HttpStatus.OK);
        }
        if (studentService.existsByName(student.getName())) {
            return new ResponseEntity<>(new ResponseMessage("name_exited"), HttpStatus.OK);
        }
        student1.get().setName(student.getName());
        studentService.save(student1.get());
        return new ResponseEntity<>(new ResponseMessage("update_success!"), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id){
        Optional<Student> student = studentService.findById(id);
        if(!student.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        studentService.deleteById(id);
        return new ResponseEntity<>(new ResponseMessage("Delete success!"), HttpStatus.OK);
    }


}
