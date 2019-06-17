package org.testunited.core;

import java.util.UUID;

import javax.persistence.*;

@Entity
public class TestTarget {

	@Id
	@GeneratedValue
	@org.hibernate.annotations.Type(type="uuid-char")
	private UUID id;

	private String microservice;

	private String path;

	private String method;
	public TestTarget(UUID id, String microservice, String path, String method) {
		super();
		this.id = id;
		this.microservice = microservice;
		this.path = path;
		this.method = method;
	}
	public TestTarget(String microservice, String path, String method) {
		super();
		this.microservice = microservice;
		this.path = path;
		this.method = method;
	}
	public TestTarget(UUID id) {
		this.id = id;
	}
	public TestTarget() {
	}
	
	public UUID getId() {
		return id;
	}

	public String getMethod() {
		return method;
	}

	public String getMicroservice() {
		return microservice;
	}

	public String getPath() {
		return path;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public void setMicroservice(String microservice) {
		this.microservice = microservice;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
