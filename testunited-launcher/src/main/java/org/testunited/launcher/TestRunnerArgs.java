package org.testunited.launcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestRunnerArgs {
List<TestBundle> testBundles;
TestBundleResolutionMode resolutionMode;
private static String ARG_TEST_BUNDLE_IDS = "TEST_BUNDLE_IDS";
private static String ARG_TEST_BUNDLE_MODE = "TEST_BUNDLE_MODE";
private static TestBundleResolutionMode DEFAULT_RESOLUTION_MODE = TestBundleResolutionMode.Classpath;

public static HashMap<String, String> getArgValues(String... args) throws IllegalArgumentException{
	HashMap<String, String> argValues = new HashMap<String, String>();

	for(String s:args) {
		String[] argVal = s.split("=");
		
		try {
		argValues.put(argVal[0], argVal[1]);
		}
		catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	return argValues;
}

public static ArrayList<TestBundle> getTestBundles(String testBundlesArg) {
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

private static TestBundleResolutionMode getResolutionMode(String resolutionArg) {
	TestBundleResolutionMode resolutionMode;
	
	switch(resolutionArg.toLowerCase()) {
	case "local":
		resolutionMode = TestBundleResolutionMode.Local;
		break;
	case "remote":
		resolutionMode = TestBundleResolutionMode.Remote;
		break;
		default:
			resolutionMode = DEFAULT_RESOLUTION_MODE;
	}
	
	return resolutionMode;
}

public static TestRunnerArgs parse(String... args) {
	TestRunnerArgs testRunnerArgs = new TestRunnerArgs();
	HashMap<String,String> argValues = null;
		
	argValues = getArgValues(args);
	
	if(argValues == null || !argValues.containsKey(ARG_TEST_BUNDLE_IDS)) {
		System.out.printf("Argument '%s' is missing%n", ARG_TEST_BUNDLE_IDS);
		return null;
	}
	
	System.out.println("---------arguments---------");
	for(var key: argValues.keySet())System.out.printf("[%s:%s]%n",key,argValues.get(key));
	System.out.println("---------------------------");

	testRunnerArgs.testBundles = getTestBundles(argValues.get(ARG_TEST_BUNDLE_IDS));
	testRunnerArgs.resolutionMode = getResolutionMode(argValues.get(ARG_TEST_BUNDLE_MODE));
	
	System.out.printf("Test Bundles Found: %d%n", testRunnerArgs.testBundles.size());
	for(var testBundle: testRunnerArgs.testBundles) System.out.println(testBundle.toString());
	
	return testRunnerArgs;
}
}
