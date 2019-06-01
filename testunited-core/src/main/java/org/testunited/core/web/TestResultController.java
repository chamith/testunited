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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.testunited.core.TestCase;
import org.testunited.core.TestGroup;
import org.testunited.core.TestRun;
import org.testunited.core.TestTarget;
import org.testunited.core.services.TestCaseService;
import org.testunited.core.services.TestGroupService;
import org.testunited.core.services.TestRunService;
import org.testunited.core.services.TestTargetService;

@RestController
public class TestResultController {

	@Autowired
	TestRunService testRunService;
	@Autowired
	TestTargetService testTargetService;
	@Autowired
	TestCaseService testCaseService;
	@Autowired
	TestGroupService testGroupService;
	
	@GetMapping("/testresults/hello")
	public String sayHello() {
		return "hello";
	}

	@PostMapping("/testresults")
	@ResponseStatus(HttpStatus.CREATED)
	public TestResult save(@Valid @RequestBody TestResult testResult) {
		//this.testRunService.save(testResult);
		return testResult;
	}
	
	@PostMapping("/testresults/bulk")
	@ResponseStatus(HttpStatus.CREATED)
	public List<TestResult> save(@RequestBody List<TestResult> testResults) {
		//for(TestResult testResult: testResults)
		//	this.testRunService.save(testResult);
		return testResults;
	}
}
