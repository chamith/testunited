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
import org.testunited.core.services.TestRunService;

@RestController
public class TestRunController {

	@Autowired
	TestRunService testRunService;
	
	@GetMapping("/testcases/{testCaseId}/testruns")
	public List<TestRun> getByTestCaseId(@PathVariable UUID testCaseId){
		return this.testRunService.getByTestCaseId(testCaseId);
	}

	@GetMapping("/testcases/{testCaseId}/testSessions/{testSession}/testruns")
	public List<TestRun> getByTestCaseIdAndTestSession(@PathVariable UUID testCaseId,
			@PathVariable String testSession){
		return this.testRunService.getByTestTargetIdAndSession(testCaseId, testSession);
	}
	
	@GetMapping("/testruns/{id}")
	public ResponseEntity<TestRun> getById(@PathVariable UUID id){
		var target = this.testRunService.getById(id);
		if (target == null)
			return new ResponseEntity<TestRun>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<TestRun>(target, HttpStatus.OK);
	}
	
	@GetMapping("/testruns")
	public List<TestRun> getAll(){
		return this.testRunService.getAll();
	}
	
	@PostMapping("/testruns")
	@ResponseStatus(HttpStatus.CREATED)
	public TestRun save(@Valid @RequestBody TestRun testRun) {
		this.testRunService.save(testRun);
		return testRun;
	}
	
	@PostMapping("/testcases/{testCaseId}/testSessions/{testSession}/testruns")
	@ResponseStatus(HttpStatus.CREATED)
	public TestRun save(@PathVariable UUID testCaseId,
			@PathVariable String testSession, @PathVariable TestRun testRun) {
		testRun.setSession(testSession);
		testRun.setTestCase(new TestCase(testCaseId));
		this.testRunService.save(testRun);
		return testRun;
	}
	
	@PostMapping("/testruns/bulk")
	@ResponseStatus(HttpStatus.CREATED)
	public List<TestRun> save(@RequestBody List<TestRun> testRuns) {
		for(TestRun testRun: testRuns)
			this.testRunService.save(testRun);
		return testRuns;
	}
}
