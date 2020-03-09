package com.relations.school.services;

import java.util.List;
import com.relations.school.entity.Student;

public interface IStudentService
{	
	List<Student> findAll();
	Student findId(Long id);
    void deleteId(Long id);
    Student save(Student p);  	
}
