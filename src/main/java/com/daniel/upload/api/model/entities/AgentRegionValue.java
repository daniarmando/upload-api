package com.daniel.upload.api.model.entities;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.daniel.upload.api.model.dtos.agent.xml.RegionXMLDTO;
import com.daniel.upload.api.model.enums.AgentRegionType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "agent_region_value")
public class AgentRegionValue {
	
	@Id	
	private String id;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "agent_region_id", nullable = false)
	private AgentRegion agentRegion;
	
	@Enumerated(EnumType.STRING)
	private AgentRegionType type;
		
	@Column(name = "val", precision = 34, scale = 3)
	private BigDecimal value;
	
	public static Set<AgentRegionValue> fromRegionDTO(String regionId, RegionXMLDTO regionDTO) {
		
		var generation = regionDTO.getGeneration().getValues().stream()
				.map(value -> fromTypeAndValue(regionId, AgentRegionType.GENERATION, value));
		
		var purchase = regionDTO.getPurchase().getValues().stream()
				.map(value -> fromTypeAndValue(regionId, AgentRegionType.PURCHASE, value));
		
		var averagePrice = regionDTO.getAveragePrice().getValues().stream()
				.map(value -> fromTypeAndValue(regionId, AgentRegionType.AVERAGE_PRICE, value));
		
		return Stream.concat(Stream.concat(generation, purchase), averagePrice).collect(Collectors.toSet());
	}

	private static AgentRegionValue fromTypeAndValue(String regionId, AgentRegionType type, BigDecimal value) {
		return AgentRegionValue.builder()
				.id(UUID.randomUUID().toString())
				.agentRegion(AgentRegion.builder().id(regionId).build())
				.type(type).value(value).build();
	}

}
