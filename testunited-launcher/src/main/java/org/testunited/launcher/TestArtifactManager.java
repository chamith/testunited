package org.testunited.launcher;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.maven.repository.internal.MavenRepositorySystemUtils;
import org.eclipse.aether.DefaultRepositorySystemSession;
import org.eclipse.aether.RepositorySystem;
import org.eclipse.aether.RepositorySystemSession;
import org.eclipse.aether.artifact.Artifact;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.connector.basic.BasicRepositoryConnectorFactory;
import org.eclipse.aether.impl.DefaultServiceLocator;
import org.eclipse.aether.repository.LocalRepository;
import org.eclipse.aether.repository.RemoteRepository;
import org.eclipse.aether.resolution.ArtifactRequest;
import org.eclipse.aether.resolution.ArtifactResult;
import org.eclipse.aether.spi.connector.RepositoryConnectorFactory;
import org.eclipse.aether.spi.connector.transport.TransporterFactory;
import org.eclipse.aether.transport.file.FileTransporterFactory;
import org.eclipse.aether.transport.http.HttpTransporterFactory;

public class TestArtifactManager {
	public static final String DEFAULT_LOCAL_REPOSITORY = "m2";	
	public static final String DEFAULT_LOCAL_REPOSITORY_CACHE = "m2-cache";	
	public static final String DEFAULT_TEST_ARTIFACT_CACHE = "test-jars";

	private String m2LocalHome;
	private TestBundleResolutionMode testBundleResolutionMode;
	private ArrayList<Path> cachedArtifacts;
	
	public static void main(String[] args) {
		
		if(args.length < 2)
		{
			System.out.println("invalid arguments");
			return;
		}
		
		System.out.println("TEST_ARTIFACT_IDS: "+ args[0]);
		System.out.println("MODE: "+ args[1]);

		var artifactManager = new TestArtifactManager(TestBundleResolutionMode.Local);
		var testBundles = artifactManager.getTestBundles(args[0]);
		artifactManager.m2LocalHome = DEFAULT_LOCAL_REPOSITORY;
		
		artifactManager.resolveTestBundles(testBundles);
	}
	public TestArtifactManager() {
		this(TestBundleResolutionMode.Classpath);
	}
	
	public TestArtifactManager(TestBundleResolutionMode testBundleResolutionMode) {
		this.testBundleResolutionMode = testBundleResolutionMode;
		this.cachedArtifacts = new ArrayList<Path>();
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

	
	public void resolveTestBundle(TestBundle testBundle) {
		
		if(this.testBundleResolutionMode == TestBundleResolutionMode.Classpath)
			return;
		
		
		RepositorySystem repositorySystem = getRepositorySystem();
		RepositorySystemSession repositorySystemSession = getRepositorySystemSession(repositorySystem);
		
		Artifact artifact = new DefaultArtifact(testBundle.group, testBundle.artifact, "jar", testBundle.version);
		ArtifactRequest artifactRequest = new ArtifactRequest();
		artifactRequest.setArtifact(artifact);
		artifactRequest.setRepositories(getRepositories(repositorySystem, repositorySystemSession));

		try {
			ArtifactResult artifactResult = repositorySystem.resolveArtifact(repositorySystemSession,
					artifactRequest);
			artifact = artifactResult.getArtifact();
			System.out.printf("artifact %s resolved to %s\n", artifact, artifact.getFile());

			this.copyToCache(artifact);

		} catch (Exception e) {
			System.err.printf("error resolving artifact: %s\n", e.getMessage());
		}
	}
	
	public void resolveTestBundles(List<TestBundle> testBundles) {
		for(var tb: testBundles) {
			this.resolveTestBundle(tb);
		}
	}

	public void copyToCache(Artifact artifact){
		//Path path = FileSystems.getDefault().getPath(".").toAbsolutePath();
		//System.out.println("Working Dir: " + path.toAbsolutePath());
		
		Path original = Paths.get(artifact.getFile().getAbsolutePath());
		Path copied = Paths.get(FileSystems.getDefault().getPath(this.DEFAULT_TEST_ARTIFACT_CACHE).toAbsolutePath().toString(), artifact.getFile().getName());
		
		try {
			//this.cachedArtifacts.add(Files.move(original, copied, StandardCopyOption.REPLACE_EXISTING));
			Files.createSymbolicLink(copied, original);
			//Files.exists(copied, LinkOption.NOFOLLOW_LINKS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public RepositorySystem getRepositorySystem() {
		DefaultServiceLocator serviceLocator = MavenRepositorySystemUtils.newServiceLocator();
		serviceLocator.addService(RepositoryConnectorFactory.class, BasicRepositoryConnectorFactory.class);
		serviceLocator.addService(TransporterFactory.class, FileTransporterFactory.class);
		serviceLocator.addService(TransporterFactory.class, HttpTransporterFactory.class);

		serviceLocator.setErrorHandler(new DefaultServiceLocator.ErrorHandler() {
			@Override
			public void serviceCreationFailed(Class<?> type, Class<?> impl, Throwable exception) {
				System.err.printf("error creating service: %s\n", exception.getMessage());
				exception.printStackTrace();
			}
		});

		return serviceLocator.getService(RepositorySystem.class);
	}

	public DefaultRepositorySystemSession getRepositorySystemSession(RepositorySystem system) {
		DefaultRepositorySystemSession repositorySystemSession = MavenRepositorySystemUtils.newSession();

		LocalRepository localRepository = new LocalRepository(DEFAULT_LOCAL_REPOSITORY_CACHE);
		repositorySystemSession
				.setLocalRepositoryManager(system.newLocalRepositoryManager(repositorySystemSession, localRepository));

		repositorySystemSession.setRepositoryListener(new ConsoleRepositoryEventListener());

		return repositorySystemSession;
	}

	public List<RemoteRepository> getRepositories(RepositorySystem system, RepositorySystemSession session) {
		return Arrays.asList(getCentralMavenRepository(), getLocalMavenRepository());
	}

	private RemoteRepository getCentralMavenRepository() {
		return new RemoteRepository.Builder("central", "default", "http://central.maven.org/maven2/").build();
	}

	private RemoteRepository getLocalMavenRepository() {
		Path m2LocalHomeAbsolutePath = Paths.get(this.m2LocalHome, "repository").toAbsolutePath();
		return new RemoteRepository.Builder("local", "default", "file:"+ m2LocalHomeAbsolutePath.toString()).build();
	}
	
	public void clearCache() {
		for(Path file: this.cachedArtifacts) {
			try {
				Files.deleteIfExists(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
