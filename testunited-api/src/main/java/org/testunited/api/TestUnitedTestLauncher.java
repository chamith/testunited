package org.testunited.api;

import org.junit.platform.engine.discovery.PackageSelector;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;

import static org.junit.platform.engine.discovery.DiscoverySelectors.*;

import java.util.ArrayList;
import java.util.List;

public class TestUnitedTestLauncher implements TestUnitedTestApplication {
	public static void main(String[] args) {
		new TestUnitedTestLauncher().run();
	}

	@Override
	public void run(String... args) {
		List<PackageSelector> packages = new ArrayList<PackageSelector>();
		
		for(String s: args) {
			packages.add(selectPackage(s));
		}
		
		LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
				.selectors(packages).build();
		Launcher launcher = LauncherFactory.create();

		launcher.registerTestExecutionListeners(new TestUnitedTestExecutionListener());
		launcher.execute(request);
	}
}
