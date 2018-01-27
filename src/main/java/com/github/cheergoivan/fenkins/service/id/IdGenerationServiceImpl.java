package com.github.cheergoivan.fenkins.service.id;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class IdGenerationServiceImpl implements IdGenerationService{

	@Override
	public String generateId() {
		return UUID.randomUUID().toString().replace("-", "");
	}

}
