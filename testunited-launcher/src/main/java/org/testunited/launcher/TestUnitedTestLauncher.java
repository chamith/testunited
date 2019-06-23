package org.testunited.launcher;

import org.apache.commons.math3.analysis.solvers.IllinoisSolver;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.engine.discovery.PackageSelector;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.testunited.api.TestUnitedTestApplication;
import org.testunited.api.TestUnitedTestExecutionListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestUnitedTestLauncher implements TestUnitedTestApplication {

	public static void main(String[] args) throws IOException {

		new TestUnitedTestLauncher().run(args);
	}

	private void runTests(List<TestBundle> testBundles) {
		
		ArrayList<PackageSelector> packageSelectors = new ArrayList<PackageSelector>();
		
		for(var testBundle: testBundles) {
			packageSelectors.add(DiscoverySelectors.selectPackage(testBundle.testPackage));
		}
		
		LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
				.selectors(packageSelectors).build();

		var launcher = LauncherFactory.create();

		var testPlan = launcher.discover(request);

		System.out.printf("TestPlan has tests? %b%n", testPlan.containsTests());

		launcher.execute(request, new TestUnitedTestExecutionListener());
	}

	@Override
	public void run(String... args) {
		TestRunnerArgs testRunnerArgs = TestRunnerArgs.parse(args);
		this.runTests(testRunnerArgs.testBundles);
	}
}
