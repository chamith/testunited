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
	
	public List<TestCase> getByTestTargetId(UUID id) {
		return testCaseRepo.findByTestTargetId(id);
	}
	public List<TestCase> getByTestTargetIdTestGroupId(UUID testTargetId, UUID testGroupId) {
		return testCaseRepo.findByTestTargetIdAndTestGroupId(testTargetId, testGroupId);
	}
	public TestCase getById(UUID id) {
		return this.testCaseRepo.findById(id).get();
	}
	
	public TestCase getByTestSourceId(String sourceId) {
		return testCaseRepo.findByTestSourceId(sourceId);
	}
	
	public List<TestCase> getAll(){
		return (List<TestCase>)this.testCaseRepo.findAll();
	}
	
}
