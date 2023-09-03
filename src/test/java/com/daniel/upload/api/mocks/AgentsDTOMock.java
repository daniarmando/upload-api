package com.daniel.upload.api.mocks;

import static com.daniel.upload.api.utils.DateUtils.toDate;

import java.math.BigDecimal;
import java.util.List;

import com.daniel.upload.api.model.dtos.agent.xml.AgentXMLDTO;
import com.daniel.upload.api.model.dtos.agent.xml.AgentsXMLDTO;
import com.daniel.upload.api.model.dtos.agent.xml.AveragePriceXMLDTO;
import com.daniel.upload.api.model.dtos.agent.xml.GenerationXMLDTO;
import com.daniel.upload.api.model.dtos.agent.xml.PurchaseXMLDTO;
import com.daniel.upload.api.model.dtos.agent.xml.RegionXMLDTO;

public final class AgentsDTOMock {
	
	private AgentsDTOMock() {}
	
	public static AgentsXMLDTO getAgentsDTOMock() {
		
		var regionSe = RegionXMLDTO
				.builder()
				.acronym("SE")
				.generation(new GenerationXMLDTO(List.of(
						new BigDecimal("1.864"), 
						new BigDecimal("1.864"), 
						new BigDecimal("1.864"), 
						new BigDecimal("1.864"), 
						new BigDecimal("1.864"), 
						new BigDecimal("1.864"), 
						new BigDecimal("1.864"))))
				.purchase(new PurchaseXMLDTO(List.of(
						new BigDecimal("1.19"), 
						new BigDecimal("1.19"), 
						new BigDecimal("1.19"),
						new BigDecimal("1.19"),
						new BigDecimal("1.19"),
						new BigDecimal("1.19"),
						new BigDecimal("1.19"))))
				.averagePrice(new AveragePriceXMLDTO(List.of(
						new BigDecimal("1.593"), 
						new BigDecimal("1.593"), 
						new BigDecimal("1.593"),
						new BigDecimal("1.593"),
						new BigDecimal("1.593"),
						new BigDecimal("1.593"),
						new BigDecimal("1.593"))))
				.build();
		
		var regionS = RegionXMLDTO
				.builder()
				.acronym("S")
				.generation(new GenerationXMLDTO(List.of(
						new BigDecimal("1.513"), 
						new BigDecimal("1.513"), 
						new BigDecimal("1.513"), 
						new BigDecimal("1.513"), 
						new BigDecimal("1.513"), 
						new BigDecimal("1.513"), 
						new BigDecimal("1.513"))))
				.purchase(new PurchaseXMLDTO(List.of(
						new BigDecimal("1.27"), 
						new BigDecimal("1.27"), 
						new BigDecimal("1.27"),
						new BigDecimal("1.27"),
						new BigDecimal("1.27"),
						new BigDecimal("1.27"),
						new BigDecimal("1.27"))))
				.averagePrice(new AveragePriceXMLDTO(List.of(
						new BigDecimal("1.512"), 
						new BigDecimal("1.512"), 
						new BigDecimal("1.512"),
						new BigDecimal("1.512"),
						new BigDecimal("1.512"),
						new BigDecimal("1.512"),
						new BigDecimal("1.512"))))
				.build();
		
		var regionNe = RegionXMLDTO
				.builder()
				.acronym("NE")
				.generation(new GenerationXMLDTO(List.of(
						new BigDecimal("1.463"), 
						new BigDecimal("1.463"), 
						new BigDecimal("1.463"), 
						new BigDecimal("1.463"), 
						new BigDecimal("1.463"), 
						new BigDecimal("1.463"), 
						new BigDecimal("1.463"))))
				.purchase(new PurchaseXMLDTO(List.of(
						new BigDecimal("1.936"), 
						new BigDecimal("1.936"), 
						new BigDecimal("1.936"),
						new BigDecimal("1.936"),
						new BigDecimal("1.936"),
						new BigDecimal("1.936"),
						new BigDecimal("1.936"))))
				.averagePrice(new AveragePriceXMLDTO(List.of(
						new BigDecimal("1.343"), 
						new BigDecimal("1.343"), 
						new BigDecimal("1.343"),
						new BigDecimal("1.343"),
						new BigDecimal("1.343"),
						new BigDecimal("1.343"),
						new BigDecimal("1.343"))))
				.build();
		
		var regionN = RegionXMLDTO
				.builder()
				.acronym("N")
				.generation(new GenerationXMLDTO(List.of(
						new BigDecimal("1.707"), 
						new BigDecimal("1.707"), 
						new BigDecimal("1.707"), 
						new BigDecimal("1.707"), 
						new BigDecimal("1.707"), 
						new BigDecimal("1.707"), 
						new BigDecimal("1.707"))))
				.purchase(new PurchaseXMLDTO(List.of(
						new BigDecimal("1.141"), 
						new BigDecimal("1.141"), 
						new BigDecimal("1.141"),
						new BigDecimal("1.141"),
						new BigDecimal("1.141"),
						new BigDecimal("1.141"),
						new BigDecimal("1.141"))))
				.averagePrice(new AveragePriceXMLDTO(List.of(
						new BigDecimal("1.76"), 
						new BigDecimal("1.76"), 
						new BigDecimal("1.76"),
						new BigDecimal("1.76"),
						new BigDecimal("1.76"),
						new BigDecimal("1.76"),
						new BigDecimal("1.76"))))
				.build();
		
		var version = "1.0";
		
		var agentsDTO = List.of(AgentXMLDTO.builder()
				.code(1L)
				.date(toDate("2000-03-14T00:00:00-00:00"))
				.regions(List.of(regionSe, regionS, regionNe, regionN))
				.build());				
		
		return new AgentsXMLDTO(version, agentsDTO);
	}

}
