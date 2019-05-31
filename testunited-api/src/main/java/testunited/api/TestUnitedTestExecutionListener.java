package testunited.api;

import java.util.Set;

import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.engine.TestTag;
import org.junit.platform.engine.reporting.ReportEntry;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;
import org.junit.platform.launcher.TestPlan;;

public class TestUnitedTestExecutionListener implements TestExecutionListener {

	@Override
	public void testPlanExecutionStarted(TestPlan testPlan) {
		// TODO Auto-generated method stub
		TestExecutionListener.super.testPlanExecutionStarted(testPlan);
	}

	@Override
	public void testPlanExecutionFinished(TestPlan testPlan) {
		// TODO Auto-generated method stub
		TestExecutionListener.super.testPlanExecutionFinished(testPlan);
	}

	@Override
	public void dynamicTestRegistered(TestIdentifier testIdentifier) {
		// TODO Auto-generated method stub
		TestExecutionListener.super.dynamicTestRegistered(testIdentifier);
	}

	@Override
	public void executionSkipped(TestIdentifier testIdentifier, String reason) {
		// TODO Auto-generated method stub
		TestExecutionListener.super.executionSkipped(testIdentifier, reason);
	}

	@Override
	public void executionStarted(TestIdentifier testIdentifier) {
		// TODO Auto-generated method stub
		TestExecutionListener.super.executionStarted(testIdentifier);
	}

	@Override
	public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
		System.out.println("Finished:" + testIdentifier.getDisplayName());
		
    	Set<TestTag> tags = testIdentifier.getTags();
    	for(TestTag tag: tags) {
    		System.out.println(tag.toString());
    	}
		// TODO Auto-generated method stub
		TestExecutionListener.super.executionFinished(testIdentifier, testExecutionResult);
	}

	@Override
	public void reportingEntryPublished(TestIdentifier testIdentifier, ReportEntry entry) {
		// TODO Auto-generated method stub
		TestExecutionListener.super.reportingEntryPublished(testIdentifier, entry);
	}

}
