package org.testunited.core.web;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.testunited.core.TestResult;
import org.testunited.core.TestRun;
import org.testunited.core.TestTarget;
import org.testunited.core.services.TestCaseService;
import org.testunited.core.services.TestGroupService;
import org.testunited.core.services.TestRunService;
import org.testunited.core.services.TestTargetService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;

@RestController
public class TestResultController {

	Logger logger = LoggerFactory.getLogger(getClass());

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

		//System.out.println(testResult.toString());
		TestCase testCase = testCaseService.getByTestSourceId(testResult.getTestSourceId());
		if (testCase == null) {
			testCase = new TestCase(testResult.getTestSourceId(), testResult.getTestSourceId(), null);
			this.testCaseService.save(testCase);
		}

		TestRun testRun = new TestRun(testCase, testResult.getTimeStamp(), testResult.getResult(),
				testResult.getReason(), null);
		this.testRunService.save(testRun);
		return testResult;
	}

	@PostMapping("/testresults/bulk")
	@ResponseStatus(HttpStatus.CREATED)
	public List<TestResult> save(@RequestBody List<TestResult> testResults) {

		if (logger.isInfoEnabled()) {
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			// StdDateFormat is ISO8601 since jackson 2.9
			mapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));
			try {
				String results = mapper.writeValueAsString(testResults);
				logger.info(results);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// for(TestResult testResult: testResults)
		// this.testRunService.save(testResult);
		for (TestResult r : testResults)
			this.save(r);
		return testResults;
	}
}
