package com.relations.school.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.relations.school.entity.Career;

@Repository
public interface CareerRepository extends CrudRepository<Career, Long>{

}
