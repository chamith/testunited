package org.testunited.core.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.util.ArrayList;
import java.util.UUID;

import org.hamcrest.Matchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testunited.core.TestTarget;
import org.testunited.core.data.TestTargetRepository;
import org.testunited.core.services.TestTargetService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@Tag("microservice:booktitle")
public class TestTargetControllerTests {

	private MockMvc mockMvc;

	private final String jsonSingleTestTargetGood = 
			"{\n" + 
			"    \"id\": \"672124b6-9894-11e5-be38-001d42e813fe\",\n" + 
			"    \"microservice\": \"test_ms_1\",\n"+ 
			"    \"path\": \"/\",\n" + 
			"    \"method\": \"GET\"\n" + 
			"}";

	private final String jsonSingleTestTargetBad = 
			"{\n" + 
			"    \"id\": \"672124b6-9894-11e5-be38-001d42e813fe\",\n" + 
			"    \"microservice\": \"test_ms_2\",\n"+ 
			"    \"path\": \"/items\"\n" + 
			"    \"method\": \"POST\"\n" + 
			"}";

	private final TestTarget testTarget1 = new TestTarget(UUID.fromString("672124b6-9894-11e5-be38-001d42e813fe"), "test_ms_1", "/", "GET");
	private final TestTarget testTarget2 = new TestTarget(UUID.fromString("672124b6-9894-11e5-be38-001d42e813fe"), "test_ms_2", "/items", "POST");

	private final ArrayList<TestTarget> testTargetList = new ArrayList<TestTarget>();

	@InjectMocks
	private TestTargetController controller;

	@Mock
	private TestTargetService serviceMock;

	@BeforeAll
	public void setUp() throws Exception {
		mockMvc = standaloneSetup(controller).build();

	}

	@Test
	public void testGetById() throws Exception {
		when(serviceMock.getById(UUID.fromString("672124b6-9894-11e5-be38-001d42e813fe"))).thenReturn(this.testTarget1);

		this.mockMvc.perform(get("/testtargets/672124b6-9894-11e5-be38-001d42e813fe").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", Matchers.is("672124b6-9894-11e5-be38-001d42e813fe")))
				.andExpect(jsonPath("$.microservice", Matchers.is("test_ms_1")))
				.andExpect(jsonPath("$.path", Matchers.is("/")))
				.andExpect(jsonPath("$.method", Matchers.is("GET")));
	}
	
	@Test
	public void testGetById_nonExistantId() throws Exception {
		when(serviceMock.getById(UUID.fromString("672124b6-9894-11e5-be38-001d42e813fe"))).thenReturn(null);

		this.mockMvc.perform(get("/testtargets/672124b6-9894-11e5-be38-001d42e813fe").accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}
	
	@Test
	public void testGetAll() throws Exception {

		this.testTargetList.add(this.testTarget1);
		this.testTargetList.add(this.testTarget2);

		when(serviceMock.getAll()).thenReturn(this.testTargetList);

		this.mockMvc.perform(get("/testtargets").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.*", Matchers.hasSize(2)));
	}

	@Test
	public void testAdd() throws Exception {
				
		this.mockMvc
				.perform(post("/testtargets").content(this.jsonSingleTestTargetGood).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void testUpdate() throws Exception {
			
		this.mockMvc
				.perform(put("/testtargets").content(this.jsonSingleTestTargetGood).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

//	@Test
//	@Tag("method:GET")
//	@Tag("route:/booktitles")
//	@Tag(TestTags.GROUP_REQUEST_VALIDATION)
//	@Tag("case:bad_json")
//	@DisplayName("Validate status code when bad JSON is sent")
//	public void testAddBadRequestBadJson() throws Exception {
//		this.mockMvc
//				.perform(post("/booktitles").content(jsonSingleBookTitleBad).contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isBadRequest());
//	}
//
//	@Test
//	public void testAddBadRequestNotJson() throws Exception {
//		this.mockMvc.perform(post("/booktitles").content("NotJason").contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isBadRequest());
//	}

}
