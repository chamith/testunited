package org.testunited.core.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.testunited.core.TestCase;
import org.testunited.core.data.TestCaseRepository;

@Service
public class TestCaseService {

	@Autowired
	private TestCaseRepository testCaseRepo;
	
	public void save(TestCase testGroup) {
		this.testCaseRepo.save(testGroup);
	}
	
	public List<TestCase> getByTestTargetId(long id) {
		return testCaseRepo.findByTestTargetId(id);
	}
	public List<TestCase> getByTestTargetIdTestGroupId(long testTargetId, long testGroupId) {
		return testCaseRepo.findByTestTargetIdAndTestGroupId(testTargetId, testGroupId);
	}
	public TestCase getById(UUID id) {
		return this.testCaseRepo.findById(id).get();
	}
	
	public List<TestCase> getAll(){
		return (List<TestCase>)this.testCaseRepo.findAll();
	}
	
}
