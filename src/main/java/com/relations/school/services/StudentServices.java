package com.relations.school.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.relations.school.entity.Student;
import com.relations.school.repository.StudentRepository;

@Service
public class StudentServices implements IStudentService {

	@Autowired
    private StudentRepository repository;

    @Override
    public List<Student> findAll() {

        return (List<Student>) repository.findAll();
    }
    
    public Student save(Student p)    
    {
    	return repository.save(p);
    }
    
    public Student findId(Long id)
    {
    	return repository.findById(id).get();
    }   
    
    public void deleteId(Long id)
    {
    	repository.deleteById(id);
    }
}
