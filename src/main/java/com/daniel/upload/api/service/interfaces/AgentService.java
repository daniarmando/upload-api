package com.daniel.upload.api.service.interfaces;

import java.util.Collection;

import org.springframework.web.multipart.MultipartFile;

import com.daniel.upload.api.model.dtos.agent.RegionDTO;

public interface AgentService {
	
	void processUpload(MultipartFile file);
	
	Collection<RegionDTO> getRegionValuesByAcronym(String acronym);

}
