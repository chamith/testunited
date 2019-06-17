package org.testunited.core;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"testSourceId"})})
public class TestCase {
	
	@Id
	@GeneratedValue
	@org.hibernate.annotations.Type(type="uuid-char")
	private UUID id;
	
	@NotNull
	private String testSourceId;
	private String name;
	private String description;
	
	@ManyToOne//(cascade=CascadeType.ALL)
	private TestTarget testTarget;
	
	@ManyToOne//(cascade=CascadeType.ALL)
	private TestGroup testGroup;

	public TestCase() {
	}

	public TestCase(UUID id) {
		super();
		this.id = id;
	}
	public TestCase(String sourceId, String name, String description) {
		super();
		this.testSourceId = sourceId;
		this.name = name;
		this.description = description;
	}

	public TestCase(UUID uuid, String sourceId, String name, String description, UUID testTargetId, UUID testGroupId) {
		super();
		this.id = uuid;
		this.testSourceId = sourceId;
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

	public String getTestSourceId() {
		return testSourceId;
	}

	public void setTestSourceId(String sourceId) {
		this.testSourceId = sourceId;
	}

}
