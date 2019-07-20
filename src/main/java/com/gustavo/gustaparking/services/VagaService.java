package com.gustavo.gustaparking.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustavo.gustaparking.models.Vaga;
import com.gustavo.gustaparking.repositories.VagaRepository;

@Service
public class VagaService {

	@Autowired private VagaRepository repo;
	
	public Vaga save(Vaga vaga) {
		return repo.save(vaga);
	}
	
	
}
