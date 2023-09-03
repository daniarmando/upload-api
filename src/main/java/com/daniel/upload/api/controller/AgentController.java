package com.daniel.upload.api.controller;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.daniel.upload.api.model.dtos.agent.RegionDTO;
import com.daniel.upload.api.service.interfaces.AgentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
@Tag(name = "Agents")
@RequiredArgsConstructor
@RequestMapping("agents")
public class AgentController {
	
	private final AgentService service;
	
	@Operation(summary = "Receives and processes an xml file from agents")
	@RequestBody(content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE))
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PostMapping(value = "/uploads", produces = MediaType.APPLICATION_JSON_VALUE, consumes = { 
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Void> upload(MultipartFile file) {
		
		log.info("{} received with {} bytes", file.getOriginalFilename(), file.getSize());
		
		service.processUpload(file);		

		return ResponseEntity.noContent().build();
	}		
	
	@Operation(summary = "Return regions values by acronym")	
	@GetMapping(value = "/regions/{acronym}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<RegionDTO>> getRegionByAcronym(@PathVariable String acronym) {
		
		log.info("finding values by region {}", acronym);
				
		return ResponseEntity.ok(service.getRegionValuesByAcronym(acronym));
	}

}
