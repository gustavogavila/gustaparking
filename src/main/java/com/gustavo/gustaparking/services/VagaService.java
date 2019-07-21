package com.gustavo.gustaparking.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustavo.gustaparking.models.ParametroSistema;
import com.gustavo.gustaparking.models.Vaga;
import com.gustavo.gustaparking.models.dtos.VagaDTO;
import com.gustavo.gustaparking.models.enums.Situacao;
import com.gustavo.gustaparking.repositories.ParametroSistemaRepository;
import com.gustavo.gustaparking.repositories.VagaRepository;

@Service
public class VagaService {

	@Autowired private VagaRepository repo;
	@Autowired private ParametroSistemaRepository sistemaRepo;
	
	public Vaga save(Vaga vaga) {
		return repo.save(vaga);
	}

	public VagaDTO getRelatorioDiario() {
		ParametroSistema sistema = sistemaRepo.findById(1L).orElse(null);
		String nomeEstacionamento = sistema.getNomeEstacionamento();
		Long totalVagas = repo.countById();
		Long vagasDisponiveis = repo.countBySituacao(Situacao.LIVRE.getCodigo());
		Long vagasOcupadas = repo.countBySituacao(Situacao.OCUPADO.getCodigo());
		LocalDateTime dataHoraConsulta = LocalDateTime.now();
		return new VagaDTO(nomeEstacionamento, dataHoraConsulta, vagasDisponiveis, vagasOcupadas, totalVagas);
	}
}
