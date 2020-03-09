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
import com.relations.school.services.ICareerService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,RequestMethod.PUT })
public class CareerController {

	@Autowired
	private ICareerService careerService;

	@RequestMapping(value = "/careers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Career> findAll(Model model) {

		return careerService.findAll();
	}

	@RequestMapping(value = "/careers/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Career findPersonas(Model model, @PathVariable Long id) {

		return careerService.findId(id);
	}

	@RequestMapping(value = "/careers/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updatePersona(@RequestBody Career career, @PathVariable long id) {
		Career newCareer = careerService.findId(id);
		if (newCareer != null) {
			newCareer.setName(career.getName());			
			Career pSaved = careerService.save(newCareer);
			if (pSaved.equals(newCareer)) {
				return new ResponseEntity<>("Data updated successfully", HttpStatus.OK);
			}
		}

		return new ResponseEntity<>("Resource not found", HttpStatus.NOT_FOUND);

	}

	@RequestMapping(value = "/careers/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletePersona(Model model, @PathVariable Long id) {

		try {
			careerService.deleteId(id);
			return new ResponseEntity<>("Data deleted successfully", HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>("Resource not found", HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/careers/", method = RequestMethod.PUT)
	public ResponseEntity<?> createPersona(@RequestBody Career career) {

		Career p = careerService.save(career);
		if (p != null) {
			return new ResponseEntity<>("Data created successfully", HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>("Cannot created", HttpStatus.NOT_FOUND);
	}
}
