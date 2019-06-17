package org.testunited.core.web;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.testunited.core.TestGroup;
import org.testunited.core.TestTarget;
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
	public ResponseEntity<TestGroup> getById(@PathVariable UUID id){
		var target = this.testGroupService.getById(id);
		if (target == null)
			return new ResponseEntity<TestGroup>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<TestGroup>(target, HttpStatus.OK);
	}
	
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
	@ResponseStatus(HttpStatus.CREATED)
	public TestGroup save(@Valid @RequestBody TestGroup testGroup) {
		this.testGroupService.save(testGroup);
		return testGroup;
	}
	
	@PostMapping("/bulk")
	@ResponseStatus(HttpStatus.CREATED)
	public List<TestGroup> saveMany(@RequestBody List<TestGroup> testGroups) {
		for(TestGroup testGroup: testGroups)
			this.testGroupService.save(testGroup);
		return testGroups;
	}
	
}
