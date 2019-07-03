package org.testunited.core.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.io.IOException;
import java.util.ArrayList;

import org.hamcrest.Matchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testunited.core.TestCase;
import org.testunited.core.TestGroup;
import org.testunited.core.TestRun;
import org.testunited.core.TestTarget;
import org.testunited.core.data.TestTargetRepository;
import org.testunited.core.services.TestGroupService;
import org.testunited.core.services.TestRunService;
import org.testunited.core.services.TestTargetService;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import net.minidev.json.reader.JsonWriter;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import java.util.Date;
import java.util.UUID;
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class TestRunControllerTests {

	private MockMvc mockMvc;

	private final String jsonSingleTestGroupGood = 
			"{\n" + 
			"    \"id\": \"672124b6-9894-11e5-be38-001d42e813fe\",\n" + 
			"    \"name\": \"my_test_group_1\"\n"+ 
			"}";

	private final String jsonSingleTestGroupBad = 
			"{\n" + 
			"    \"id\": \"672124b6-9894-11e5-be38-001d42e813fe\",\n" + 
			"    \"name\"asdfaf \"my_test_group_2\"\n"+ 
			"}";

	private final TestRun testRun1 = new TestRun(new TestCase(UUID.fromString("3d50454f-0ba1-455b-8311-c7195c92e296")), new Date(), true, "mysession", null);
	private final TestGroup testTarget2 = new TestGroup(UUID.fromString("672124b6-9894-11e5-be38-001d42e813fe"), "my_test_group_2");

	private final ArrayList<TestGroup> testTargetList = new ArrayList<TestGroup>();

	@InjectMocks
	private TestRunController controller;

	@Mock
	private TestRunService serviceMock;

	@BeforeAll
	public void setUp() throws Exception {
		mockMvc = standaloneSetup(controller).build();
		ObjectMapper testRunObjectMapper = new ObjectMapper();
		String testRunJson = testRunObjectMapper.writeValueAsString(this.testRun1);
		System.out.println(testRunJson);
	}

//	@Test
//	public void testGetById() throws Exception {
//		when(serviceMock.getById(1)).thenReturn(this.testTarget1);
//
//		this.mockMvc.perform(get("/testgroups/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
//				.andExpect(jsonPath("$.id", Matchers.is(1)))
//				.andExpect(jsonPath("$.name", Matchers.is("my_test_group_1")));
//	}
//	
//	@Test
//	public void testGetById_nonExistantId() throws Exception {
//		when(serviceMock.getById(1)).thenReturn(null);
//
//		this.mockMvc.perform(get("/testgroups/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
//	}
//	
//	@Test
//	public void testGetAll() throws Exception {
//
//		this.testTargetList.add(this.testTarget1);
//		this.testTargetList.add(this.testTarget2);
//
//		when(serviceMock.getAll()).thenReturn(this.testTargetList);
//
//		this.mockMvc.perform(get("/testgroups").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
//				.andExpect(jsonPath("$.*", Matchers.hasSize(2)));
//	}
//
	@Test
	public void testAdd() throws Exception {
				
		this.mockMvc
				.perform(post("/testruns").content(this.jsonSingleTestGroupGood).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}
//	
//	@Test
//	public void testUpdate() throws Exception {
//			
//		this.mockMvc
//				.perform(put("/testgroups").content(this.jsonSingleTestGroupGood).contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isCreated());
//	}

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
