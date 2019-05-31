package org.testunited.core.web;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.testunited.core.TestGroup;
import org.testunited.core.services.TestGroupService;

@RestController
@RequestMapping("/testgroups")
public class TestGroupController {

	@Autowired
	TestGroupService testGroupService;
	
	@GetMapping("/hello")
	public String sayHello() {
		return "hello";
	}
	@GetMapping
	public ArrayList<TestGroup> getAll(){
		return this.testGroupService.getAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TestGroup> getById(@PathVariable long id){
		var target = this.testGroupService.getById(id);
		if (target == null)
			return new ResponseEntity<TestGroup>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<TestGroup>(target, HttpStatus.OK);
	}
	
	@PostMapping
	@PutMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TestGroup save(@Valid @RequestBody TestGroup testGroup) {
		this.testGroupService.save(testGroup);
		return testGroup;
	}
	
}
