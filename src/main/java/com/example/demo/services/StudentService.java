package com.example.demo.services;

import com.example.demo.entity.Student;
import com.example.demo.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;
    public void saveStudent(Student student) {
       studentRepo.save(student);
    }

    public Optional<Student> showOne(Integer id) {
     return studentRepo.findById(id);
    }

    public Optional<Student> deleteOneStudent(Integer id) {
            Optional<Student> StudentToBeDelete = studentRepo.findById(id);
             if(StudentToBeDelete.isPresent()){
              studentRepo.deleteById(id);
              return  StudentToBeDelete;
             }else  {
                  throw new IllegalArgumentException("Student with id " + id + " is not present");
             }


    }

//    public void updateStudent(Integer id, Student updatedStudent) {
//       Optional<Student>StudentToBeUpdate = studentRepo.findById(id);
//       if(StudentToBeUpdate.isPresent()){
//        Student existingStudent = StudentToBeUpdate.get();
//        existingStudent.setLocation(updatedStudent.getLocation());
//        studentRepo.save(existingStudent);
//       }else{
//           throw new IllegalArgumentException("Student with ID " + id + " not found.");
//       }
//    }
public void updateStudent(Integer id, Student updatedStudent) {
    studentRepo.findById(id)
            .map(existingStudent -> {
                existingStudent.setLocation(updatedStudent.getLocation());
                existingStudent.setName(updatedStudent.getName());
                existingStudent.setRollNo(updatedStudent.getRollNo());
                return studentRepo.save(existingStudent);
            })
            .orElseThrow(() -> new IllegalArgumentException("Student with ID " + id + " not found."));
}

    public void saveAllStudent(List<Student> student) {
        studentRepo.saveAll(student);
    }

    public List<Student> findByName(String name) {
      return  studentRepo.findByName(name);
    }
    public List<Student> findByStudentName(String name) {
        return  studentRepo.getStudentByName(name);
    }
}
