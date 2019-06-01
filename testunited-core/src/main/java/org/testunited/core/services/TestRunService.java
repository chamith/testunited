package org.testunited.core.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.testunited.core.TestRun;
import org.testunited.core.data.TestRunRepository;

@Service
public class TestRunService {

	@Autowired
	private TestRunRepository testRunRepo;
	
	public void save(TestRun testGroup) {
		this.testRunRepo.save(testGroup);
	}
	
	public List<TestRun> getByTestCaseId(UUID testCaseId) {
		return testRunRepo.findByTestCaseId(testCaseId);
	}
	public List<TestRun> getByTestTargetIdAndSession(UUID testCaseId, String session) {
		return testRunRepo.findByTestCaseIdAndSession(testCaseId, session);
	}
	public TestRun getById(UUID id) {
		return this.testRunRepo.findById(id).get();
	}
	
	public List<TestRun> getAll(){
		return (List<TestRun>)this.testRunRepo.findAll();
	}
}
