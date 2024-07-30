package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class studentController {
    @Autowired
    private StudentService studentService;
    @PostMapping("/student/save")
    public String saveStudent(@RequestBody Student student) {
     try {
         studentService.saveStudent(student);
     }catch (Exception e){
       return e.getMessage();
     }
     return  "success";
    }
    @GetMapping("/student/getByName/{name}")
    public ResponseEntity<Object> showByNameStudent(@PathVariable String name) {
        List<Student> student = studentService.findByName(name);
        if(student != null && !student.isEmpty()) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with name " + name + " not found");
        }
    }
    @GetMapping("/student/getByStudentName/{name}")
    public  ResponseEntity<Object> getByStudentName (@PathVariable String name) {
        List<Student> student = studentService.findByStudentName(name);
        if(student != null && !student.isEmpty()) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with name " + name + " not found");
        }
    }
    @PostMapping("student/saveAll")
    public String saveAllStudent(@RequestBody List<Student> student) {
        try {
            studentService.saveAllStudent(student);
        }catch (Exception e) {
            return "Error " +e.getMessage();
        }
        return "Success";
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Object> showOneStudent(@PathVariable Integer id) {
        try {
            Optional<Student> student = studentService.showOne(id);
            if(student != null) {
               return ResponseEntity.ok(student);
            }else  {
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with id " + id + " not found");
            }

        } catch (Exception e) {
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error" + e.getMessage());
        }
    }
    @DeleteMapping("/student/{id}")
    public String deleteOneStudent (@PathVariable Integer id) {
       try {
              studentService.deleteOneStudent(id);
       }catch (Exception e){
        return  "Error " + e.getMessage();
       }
       return "success";
    }
    @PutMapping("/student/update")
    public ResponseEntity<String> UpdatedStudent(@RequestParam Integer id , @RequestBody Student updatedStudent) {
          try {
             studentService.updateStudent(id , updatedStudent);
         return ResponseEntity.ok("Student with id " + id + " updated successfully");

          }catch (Exception e) {
              return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                      .body("Error updating the given id" + id + " " +e.getMessage());
          }
    }
}
