package com.daniel.upload.api.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.daniel.upload.api.model.entities.Agent;

public interface AgentRepository extends JpaRepository<Agent, String> {
	
	@Query("SELECT a FROM Agent a JOIN FETCH a.regions r JOIN FETCH r.values WHERE r.acronym = :acronym")
	Collection<Agent> findByRegionAcronym(@Param("acronym") String acronym);

}
