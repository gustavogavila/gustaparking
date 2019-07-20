package com.gustavo.gustaparking.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustavo.gustaparking.models.Estacionamento;
import com.gustavo.gustaparking.repositories.EstacionamentoRepository;

@Service
public class EstacionamentoService {

	@Autowired
	private EstacionamentoRepository repo;

	public Estacionamento update(Long id, Estacionamento estacionamento) {
		Estacionamento estacionamentoExistente = repo.findById(id).orElse(null);
		if (estacionamento.getNome() != null)
			estacionamentoExistente.setNome(estacionamento.getNome());
		if (estacionamento.getTotalDeVagas() != null)
			estacionamentoExistente.setTotalDeVagas(estacionamento.getTotalDeVagas());
		if (estacionamento.getValorPorHora() != null)
			estacionamentoExistente.setValorPorHora(estacionamento.getValorPorHora());

		return repo.save(estacionamentoExistente);

	}

}
