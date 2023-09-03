package com.daniel.upload.api.controller;

import static com.daniel.upload.api.mocks.AgentMock.getAgentMockWithOnlySERegion;
import static com.daniel.upload.api.mocks.XmlMock.getXmlMock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import com.daniel.upload.api.exception.ControllerExceptionHandler;
import com.daniel.upload.api.model.dtos.agent.RegionDTO;
import com.daniel.upload.api.service.interfaces.AgentService;

@ExtendWith(MockitoExtension.class)
class AgentControllerTest {
	
	@InjectMocks
	private AgentController controller;
	
	@Mock
	private AgentService service;
	
	private MockMvc mockMvc;
	
	@BeforeEach
    public void testBefore() {
    	
    	mockMvc = MockMvcBuilders.standaloneSetup(controller)    			
    			.setControllerAdvice(new ControllerExceptionHandler()).build();    	
    }
	
	@Test
	void testUploadXml() throws Exception {
		
		doNothing().when(service).processUpload(any(MultipartFile.class));
		
		var request = MockMvcRequestBuilders
				.multipart("/agents/uploads")
				.file("file", getXmlMock().getBytes());
		
		mockMvc.perform(request)
			.andDo(print())
			.andExpect(status().isNoContent());
		
		then(service).should(times(1)).processUpload(any(MultipartFile.class));
	}
	
	@Test
	void testGetRegionByAcronym() throws Exception {
		
		final var agent = getAgentMockWithOnlySERegion();
		final var expected = List.of(RegionDTO.fromAgent(agent));
		
		given(service.getRegionValuesByAcronym(anyString())).willReturn(expected);
		
		var request = MockMvcRequestBuilders
				.get("/agents/regions/SE");
		
		mockMvc.perform(request)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$").isNotEmpty());
		
		then(service).should(times(1)).getRegionValuesByAcronym(anyString());
	}
	
}
