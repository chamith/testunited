package org.testunited.core;

import javax.persistence.*;

@Entity
public class TestTarget {

	@Id
	@GeneratedValue
	private long id;

	private String microservice;

	private String path;

	private String method;
	public TestTarget(long id, String microservice, String path, String method) {
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
	public TestTarget(long id) {
		this.id = id;
	}
	public TestTarget() {
	}
	
	public long getId() {
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

	public void setId(long id) {
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
