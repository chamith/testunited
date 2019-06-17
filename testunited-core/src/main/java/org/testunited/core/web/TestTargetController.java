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
import org.testunited.core.TestTarget;
import org.testunited.core.services.TestTargetService;

@RestController
@RequestMapping("/testtargets")
public class TestTargetController {

	@Autowired
	TestTargetService testTargetService;
	
	@GetMapping("/hello")
	public String sayHello() {
		return "hello";
	}
	@GetMapping
	public ArrayList<TestTarget> getAll(){
		return this.testTargetService.getAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TestTarget> getById(@PathVariable UUID id){
		var target = this.testTargetService.getById(id);
		if (target == null)
			return new ResponseEntity<TestTarget>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<TestTarget>(target, HttpStatus.OK);
	}
	
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
	@ResponseStatus(HttpStatus.CREATED)
	public TestTarget save(@Valid @RequestBody TestTarget testTarget) {
		this.testTargetService.save(testTarget);
		return testTarget;
	}
	
	@PostMapping("/bulk")
	@ResponseStatus(HttpStatus.CREATED)
	public List<TestTarget> saveMany(@RequestBody List<TestTarget> testTargets) {
		for(TestTarget testTarget: testTargets)
			this.testTargetService.save(testTarget);
		return testTargets;
	}
	
}
