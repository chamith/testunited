package org.testunited.core;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class TestCase {
	
	@Id
	@GeneratedValue
	private UUID id;
	private String name;
	private String description;
	
	@ManyToOne
	private TestTarget testTarget;
	
	@ManyToOne
	private TestGroup testGroup;

	public TestCase() {
	}
	
	public TestCase(String name, String description, long testTargetId, long testGroupId) {
		super();
		this.name = name;
		this.description = description;
		this.testTarget = new TestTarget(testTargetId);
		this.testGroup = new TestGroup(testGroupId);
	}

	public TestCase(UUID uuid, String name, String description, long testTargetId, long testGroupId) {
		super();
		this.id = uuid;
		this.name = name;
		this.description = description;
		this.testTarget = new TestTarget(testTargetId);
		this.testGroup = new TestGroup(testGroupId);
	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public TestGroup getTestGroup() {
		return testGroup;
	}

	public TestTarget getTestTarget() {
		return testTarget;
	}

	public UUID getId() {
		return id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTestGroup(TestGroup testGroup) {
		this.testGroup = testGroup;
	}

	public void setTestTarget(TestTarget testTarget) {
		this.testTarget = testTarget;
	}

	public void setId(UUID uuid) {
		this.id = uuid;
	}

}
