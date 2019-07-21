package com.gustavo.gustaparking.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustavo.gustaparking.models.Ocupacao;
import com.gustavo.gustaparking.models.ParametroSistema;
import com.gustavo.gustaparking.models.Vaga;
import com.gustavo.gustaparking.models.Veiculo;
import com.gustavo.gustaparking.models.enums.Situacao;
import com.gustavo.gustaparking.repositories.OcupacaoRepository;
import com.gustavo.gustaparking.repositories.ParametroSistemaRepository;
import com.gustavo.gustaparking.repositories.VagaRepository;
import com.gustavo.gustaparking.repositories.VeiculoRepository;

@Service
public class OcupacaoService {

	@Autowired
	private OcupacaoRepository repo;
	@Autowired
	private VagaRepository vagaRepo;
	@Autowired
	private VeiculoRepository veiculoRepo;
	@Autowired
	private ParametroSistemaRepository sistemaRepo;

	@Transactional
	public Ocupacao arrive(String placa) {

		List<Vaga> vagasDisponiveis = vagaRepo.findBySituacao(0);

		if (vagasDisponiveis == null) {
			throw new RuntimeException("Não existem vagas disponíveis.");
		}

		Veiculo veiculo = veiculoRepo.findByPlaca(placa);

		if (veiculo == null) {
			veiculo = new Veiculo(null, placa);
		}

		veiculoRepo.save(veiculo);

		Vaga vagaEscolhida = vagaRepo.findById(vagasDisponiveis.get(0).getId()).orElse(null);
		vagaEscolhida.setSituacao(Situacao.OCUPADO);
		vagaRepo.save(vagaEscolhida);

		Ocupacao ocupacao = new Ocupacao(null, null, LocalDateTime.now(), null, veiculo, vagaEscolhida);

		return repo.save(ocupacao);
	}

	@Transactional
	public Ocupacao depart(Veiculo veiculo) {
		Veiculo veiculoEncontrado = veiculoRepo.findByPlaca(veiculo.getPlaca());
		Ocupacao ocupacao = repo.findByVeiculo(veiculoEncontrado);
		LocalDateTime dataHoraSaida = LocalDateTime.now();
		ocupacao.setDataHoraSaida(dataHoraSaida);
		ocupacao.setValorPago(calculaValorAPagar(ocupacao.getDataHoraEntrada(), dataHoraSaida));

		Vaga vagaADesocupar = vagaRepo.findById(ocupacao.getVaga().getId()).orElse(null);
		vagaADesocupar.setSituacao(Situacao.LIVRE);
		vagaRepo.save(vagaADesocupar);

		return repo.save(ocupacao);
	}

	private BigDecimal calculaValorAPagar(LocalDateTime dataHoraEntrada, LocalDateTime dataHoraSaida) {
		ParametroSistema sistema = sistemaRepo.findById(1L).orElse(null);

		BigDecimal valorPorHora = sistema.getValorPorHora();

		LocalDateTime entradaMock = dataHoraEntrada.minusHours(3L);
		LocalDateTime saidaMock = dataHoraSaida.minusMinutes(97);

		long minutosDePermanencia = ChronoUnit.MINUTES.between(entradaMock, saidaMock);

		double horasDePermanencia = Math.round(minutosDePermanencia / 60.0);

		if (minutosDePermanencia % 60 != 0) {
			horasDePermanencia += 1;
		}

		return valorPorHora.multiply(new BigDecimal(horasDePermanencia));
	}

}
