package com.relations.school.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.relations.school.entity.Career;
import com.relations.school.repository.CareerRepository;

@Service
public class CareerService implements ICareerService{
	@Autowired
    private CareerRepository repository;

    @Override
    public List<Career> findAll() {

        return (List<Career>) repository.findAll();
    }
    
    public Career save(Career p)    
    {
    	return repository.save(p);
    }
    
    public Career findId(Long id)
    {
    	return repository.findById(id).get();
    }   

    
    public void deleteId(Long id)
    {
    	repository.deleteById(id);
    }
}
