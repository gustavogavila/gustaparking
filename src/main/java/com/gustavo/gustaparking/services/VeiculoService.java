package com.gustavo.gustaparking.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustavo.gustaparking.models.Veiculo;
import com.gustavo.gustaparking.repositories.VeiculoRepository;

@Service
public class VeiculoService {

	@Autowired
	private VeiculoRepository repo;
	
	public List<Veiculo> listarCadastrados() { 
		return repo.findAll();
	}

}
