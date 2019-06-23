package org.testunited.launcher;

import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.engine.discovery.PackageSelector;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.testunited.api.TestUnitedTestApplication;
import org.testunited.api.TestUnitedTestExecutionListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestUnitedTestLauncher implements TestUnitedTestApplication {
	private TestBundleResolutionMode testBundleResolutionMode;
	
	public TestUnitedTestLauncher() {
		this.testBundleResolutionMode = TestBundleResolutionMode.Classpath;
	}
	
	public TestUnitedTestLauncher(TestBundleResolutionMode testBundleResolutionMode) {
		this.testBundleResolutionMode = testBundleResolutionMode;
	}
	
	public static void main(String[] args) throws IOException {

		var launcher = new TestUnitedTestLauncher(TestBundleResolutionMode.Local);
		
		launcher.run(args);
	}

	private ArrayList<TestBundle> getTestBundles(String testBundlesArg) {
		ArrayList<TestBundle> testBundles = new ArrayList<>();

		if (testBundlesArg == null | testBundlesArg.isEmpty()) {
			System.out.println("no test bundles given");
			return null;
		}

		String[] testBundleArray = testBundlesArg.split(";");

		for (String s : testBundleArray) {
			String[] testBundleElements = s.split(":");
			try {
				TestBundle testBundle = new TestBundle(testBundleElements[0], testBundleElements[1],
						testBundleElements[2], testBundleElements[3]);
				testBundles.add(testBundle);
			} catch (Exception ex) {
				System.out.println(ex.toString());
			}
		}
		return testBundles;
	}

	private void runTests(List<TestBundle> testBundles) {
		
		var res = ClassLoader.getSystemClassLoader()
				.getResource("com/pearson/learnright/booktitle/test/api/GET_All_Test.class");
		System.out.println(res.toString());
		
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
		
		if (args.length == 0) {
			System.out.println("no args");
			return;
		}

		ArrayList<TestBundle> testBundles = this.getTestBundles(args[0]);

		
		System.out.printf("Test Bundles Found: %d%n", testBundles.size());
		for(var testBundle: testBundles) System.out.println(testBundle.toString());

		
		this.runTests(testBundles);
		//artifactManager.clearCache();
	}
}
