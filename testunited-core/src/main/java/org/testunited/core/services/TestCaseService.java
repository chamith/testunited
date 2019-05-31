package org.testunited.core.services;

import java.util.ArrayList;
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
	
	public ArrayList<TestCase> getByTestTargetId(long id) {
		return (ArrayList<TestCase>) testCaseRepo.findByTestTargetId(id);
	}
	public ArrayList<TestCase> getByTestTargetIdTestGroupId(long testTargetId, long testGroupId) {
		return (ArrayList<TestCase>) testCaseRepo.findByTestTargetIdAndTestGroupId(testTargetId, testGroupId);
	}
	public TestCase getById(UUID id) {
		return this.testCaseRepo.findById(id).get();
	}
	
}
