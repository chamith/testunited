package org.testunited.core.web;

public class TestResult {
private String microservice;
private String path;
private String method;
private String group;
private String name;
private String session;
private boolean result;


public String getGroup() {
	return group;
}
public String getMethod() {
	return method;
}
public String getMicroservice() {
	return microservice;
}
public String getName() {
	return name;
}
public String getPath() {
	return path;
}
public String getSession() {
	return session;
}
public boolean isResult() {
	return result;
}
public void setGroup(String group) {
	this.group = group;
}
public void setMethod(String method) {
	this.method = method;
}
public void setMicroservice(String microservice) {
	this.microservice = microservice;
}
public void setName(String name) {
	this.name = name;
}
public void setPath(String path) {
	this.path = path;
}
public void setResult(boolean result) {
	this.result = result;
}
public void setSession(String session) {
	this.session = session;
}
}
