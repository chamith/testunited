package org.testunited.core;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TestGroup {
	@Id
	//@GeneratedValue
	private long id;

	private String name;

	public TestGroup(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public TestGroup(String name) {
		super();
		this.name = name;
	}

	public TestGroup() {
	}
	public TestGroup(long id) {
		this.id = id;
	}
	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

}
