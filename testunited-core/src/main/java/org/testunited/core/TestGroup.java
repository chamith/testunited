package org.testunited.core;

import java.util.UUID;

import javax.persistence.*;

@Entity
public class TestGroup {
	@Id
	@GeneratedValue
	@org.hibernate.annotations.Type(type="uuid-char")
	private UUID id;


	private String name;

	public TestGroup(UUID id, String name) {
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
	public TestGroup(UUID id) {
		this.id = id;
	}
	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

}
