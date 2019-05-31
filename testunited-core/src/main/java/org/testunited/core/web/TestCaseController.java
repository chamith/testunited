package org.testunited.core.web;

import java.util.ArrayList;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.testunited.core.TestCase;
import org.testunited.core.TestGroup;
import org.testunited.core.TestTarget;
import org.testunited.core.services.TestCaseService;

@RestController
public class TestCaseController {

	@Autowired
	TestCaseService testCaseService;
	
	@GetMapping("/testcases/hello")
	public String sayHello() {
		return "hello";
	}
	@GetMapping("/testtargets/{testTargetId}/testcases")
	public ArrayList<TestCase> getByTestTargetId(@PathVariable long testTargetId){
		return this.testCaseService.getByTestTargetId(testTargetId);
	}

	@GetMapping("/testtargets/{testTargetId}/testgroups/{testGroupId}/testcases")
	public ArrayList<TestCase> getByTestTargetIdAndTesGroupId(@PathVariable long testTargetId,
			@PathVariable long testGroupId){
		return this.testCaseService.getByTestTargetIdTestGroupId(testTargetId, testGroupId);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TestCase> getById(@PathVariable UUID id){
		var target = this.testCaseService.getById(id);
		if (target == null)
			return new ResponseEntity<TestCase>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<TestCase>(target, HttpStatus.OK);
	}
	
	@PostMapping("/testtargets/{testTargetId}/testgroups/{testGroupId}/testcases")
	@ResponseStatus(HttpStatus.CREATED)
	public TestCase save(@PathVariable long testTargetId, 
			@PathVariable long testGroupId, 
			@Valid @RequestBody TestCase testCase) {
		testCase.setTestGroup(new TestGroup(testGroupId));
		testCase.setTestTarget(new TestTarget(testTargetId));
		this.testCaseService.save(testCase);
		return testCase;
	}
	
}
