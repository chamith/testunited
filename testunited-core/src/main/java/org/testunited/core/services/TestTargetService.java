package org.testunited.core.services;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.testunited.core.TestTarget;
import org.testunited.core.data.TestTargetRepository;

@Service
public class TestTargetService {

	@Autowired
	private TestTargetRepository testTargetRepo;
	
	public void save(TestTarget testTarget) {
		this.testTargetRepo.save(testTarget);
	}
	
	public ArrayList<TestTarget> getAll() {
		return (ArrayList<TestTarget>) testTargetRepo.findAll();
	}
	
	public TestTarget getById(UUID id) {
		return this.testTargetRepo.findById(id).get();
	}
	
}
