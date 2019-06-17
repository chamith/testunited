package org.testunited.core.services;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.testunited.core.TestGroup;
import org.testunited.core.data.TestGroupRepository;

@Service
public class TestGroupService {

	@Autowired
	private TestGroupRepository testGroupRepo;
	
	public void save(TestGroup testGroup) {
		this.testGroupRepo.save(testGroup);
	}
	
	public ArrayList<TestGroup> getAll() {
		return (ArrayList<TestGroup>) testGroupRepo.findAll();
	}
	
	public TestGroup getById(UUID id) {
		return this.testGroupRepo.findById(id).get();
	}
	
}
