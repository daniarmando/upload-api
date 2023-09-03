package com.daniel.upload.api.mocks;

import static com.daniel.upload.api.utils.DateUtils.toDate;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.daniel.upload.api.model.entities.Agent;
import com.daniel.upload.api.model.entities.AgentRegion;
import com.daniel.upload.api.model.entities.AgentRegionValue;
import com.daniel.upload.api.model.enums.AgentRegionType;

public final class AgentMock {
	
	public static final String FIXED_ID = "ccccf742-9246-41b0-905d-e4dc8f8ed0fe";
	
	private AgentMock() {}
	
	public static Agent getAgentMock() {
		
		var regionSe = AgentRegion.builder()
				.id(UUID.randomUUID().toString())
				.acronym("SE")
				.values(getRegionValues(new BigDecimal("1.864"), new BigDecimal("1.19"), new BigDecimal("1.593")))
				.build();
		
		var regionS = AgentRegion.builder()
				.id(UUID.randomUUID().toString())
				.acronym("S")
				.values(getRegionValues(new BigDecimal("1.513"), new BigDecimal("1.27"), new BigDecimal("1.512")))
				.build();
		
		var regionNE = AgentRegion.builder()
				.id(UUID.randomUUID().toString())
				.acronym("NE")
				.values(getRegionValues(new BigDecimal("1.463"), new BigDecimal("1.936"), new BigDecimal("1.343")))
				.build();
		
		var regionN = AgentRegion.builder()
				.id(UUID.randomUUID().toString())
				.acronym("N")
				.values(getRegionValues(new BigDecimal("1.707"), new BigDecimal("1.141"), new BigDecimal("1.76")))
				.build();
		
		return Agent.builder()
				.id(FIXED_ID)
				.code(1L)
				.date(toDate("2000-03-14T00:00:00-00:00"))
				.regions(Set.of(regionSe, regionS, regionNE, regionN))
				.build();		
	}
	
	public static Agent getAgentMockWithOnlySERegion() {
		
		var regionSe = AgentRegion.builder()
				.id(UUID.randomUUID().toString())
				.acronym("SE")
				.values(getRegionValues(new BigDecimal("1.864"), new BigDecimal("1.19"), new BigDecimal("1.593")))
				.build();				
		
		return Agent.builder()
				.id(FIXED_ID)
				.code(1L)
				.date(toDate("2000-03-14T00:00:00-00:00"))
				.regions(Set.of(regionSe))
				.build();		
	}
	
	private static Set<AgentRegionValue> getRegionValues(BigDecimal generation, 
			BigDecimal purchase, BigDecimal averagePrice) {
		
		var regionValues = new HashSet<AgentRegionValue>();
		
		var regionValueForGeneration = AgentRegionValue.builder()
				.id(UUID.randomUUID().toString())
				.type(AgentRegionType.GENERATION)
				.value(generation)
				.build();
		
		var regionValueForPurchasse= AgentRegionValue.builder()
				.id(UUID.randomUUID().toString())
				.type(AgentRegionType.PURCHASE)
				.value(purchase)
				.build();
		
		var regionSeValueForAveragePrice = AgentRegionValue.builder()
				.id(UUID.randomUUID().toString())
				.type(AgentRegionType.AVERAGE_PRICE)
				.value(averagePrice)
				.build();
		
		for (int i = 0; i <= 6; i++) {
			
			regionValues.add(regionValueForGeneration);
			regionValues.add(regionValueForPurchasse);
			regionValues.add(regionSeValueForAveragePrice);			
		}
		
		return regionValues;
	}

}
