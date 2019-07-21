package com.gustavo.gustaparking.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustavo.gustaparking.models.ParametroSistema;
import com.gustavo.gustaparking.repositories.ParametroSistemaRepository;

@Service
public class ParametroSistemaService {

	@Autowired
	private ParametroSistemaRepository repo;

	public ParametroSistema update(ParametroSistema parametrosNovos) {

		ParametroSistema parametrosDoSistema = repo.findById(1L).orElse(null);

		if (parametrosNovos.getNomeEstacionamento() != null) {
			parametrosDoSistema.setNomeEstacionamento(parametrosNovos.getNomeEstacionamento());
		}

		if (parametrosNovos.getValorPorHora() != null) {
			parametrosDoSistema.setValorPorHora(parametrosNovos.getValorPorHora());
		}

		return repo.save(parametrosDoSistema);

	}

	public ParametroSistema listar() {
		ParametroSistema parametros = repo.findById(1L).orElse(null);
		return parametros;
	}
}
