package com.relations.school.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.relations.school.entity.Career;
import com.relations.school.entity.Student;
import com.relations.school.services.ICareerService;
import com.relations.school.services.IStudentService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,RequestMethod.PUT })
public class StudentController {

	@Autowired
	private IStudentService studentService;
	
	@Autowired
	private ICareerService careerService;

	@RequestMapping(value = "/students", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Student> findAll(Model model) {

		return studentService.findAll();
	}

	@RequestMapping(value = "/students/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Student findPersonas(Model model, @PathVariable Long id) {

		return studentService.findId(id);
	}

	@RequestMapping(value = "/students/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updatePersona(@RequestBody Student student, @PathVariable long id) {
		Student newStudent = studentService.findId(id);
		if (newStudent != null) {
			newStudent.setName(student.getName());		
			newStudent.setAge(student.getAge());
			Student pSaved = studentService.save(newStudent);
			if (pSaved.equals(newStudent)) {
				return new ResponseEntity<>("Data updated successfully", HttpStatus.OK);
			}
		}
		return new ResponseEntity<>("Resource not found", HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/students/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletePersona(Model model, @PathVariable Long id) {

		try {
			studentService.deleteId(id);
			return new ResponseEntity<>("Data deleted successfully", HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>("Resource not found: "+e.toString(), HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/{careerId}/students/", method = RequestMethod.PUT)
	public ResponseEntity<?> createPersona(@RequestBody Student student, @PathVariable Long careerId) {
		
		Career career = careerService.findId(careerId);
		student.setCareer(career);
		Student p = studentService.save(student);
		
		if (p != null) {
			return new ResponseEntity<>("Data created successfully", HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>("Cannot created", HttpStatus.NOT_FOUND);
	}
}
