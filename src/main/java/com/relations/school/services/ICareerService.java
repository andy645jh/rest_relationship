package com.relations.school.services;

import java.util.List;

import com.relations.school.entity.Career;

public interface ICareerService {
	List<Career> findAll();
	Career findId(Long id);
    void deleteId(Long id);
    Career save(Career p);  
}
