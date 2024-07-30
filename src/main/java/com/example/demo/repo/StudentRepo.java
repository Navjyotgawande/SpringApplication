package com.example.demo.repo;

import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {
    public List<Student> findByName(String name);


//    jpql
//    @Query("select u FROM Student u WHERE u.name=:name")
//   public List<Student> getStudentByName(@Param("name") String name);

//    native query
    @Query(value = "SELECT * FROM Student WHERE name = :name", nativeQuery = true)
    List<Student> getStudentByName(@Param("name") String name);
}
