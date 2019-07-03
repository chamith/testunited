package org.testunited.core;

import java.util.Date;

public class TestResult {
	String testSourceId;
	Date timeStamp;
	boolean result;
	String reason;

	public TestResult() {
	}

	public TestResult(String testSourceId, boolean isSuccessful, String reason) {
		super();
		this.testSourceId = testSourceId;
		this.timeStamp = new Date(System.currentTimeMillis());
		this.result = isSuccessful;
		this.reason = reason;
	}

	public TestResult(String testSourceId, Date timeStamp, boolean isSuccessful, String reason) {
		super();
		this.testSourceId = testSourceId;
		this.timeStamp = timeStamp;
		this.result = isSuccessful;
		this.reason = reason;
	}

	public String getReason() {
		return reason;
	}

	public String getTestSourceId() {
		return testSourceId;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public boolean getResult() {
		return result;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public void setResult(boolean isSuccessful) {
		this.result = isSuccessful;
	}

	public void setTestSourceId(String testSourceId) {
		this.testSourceId = testSourceId;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String toString() {
		return String.format("{\"testSourceId\":\"%s\", \"timeStamp\":\"%s\", \"result\":%b}", this.testSourceId, this.timeStamp.toString(), this.result);
	}
}
