package com.gustavo.gustaparking.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustavo.gustaparking.models.Ocupacao;
import com.gustavo.gustaparking.models.ParametroSistema;
import com.gustavo.gustaparking.models.Vaga;
import com.gustavo.gustaparking.models.Veiculo;
import com.gustavo.gustaparking.models.dtos.FinanceiroPeriodo;
import com.gustavo.gustaparking.models.dtos.PermanenciaMediaDiaria;
import com.gustavo.gustaparking.models.dtos.PermanenciaMediaPeriodo;
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

//		LocalDateTime entradaMock = dataHoraEntrada.minusHours(3L);
//		LocalDateTime saidaMock = dataHoraSaida.minusMinutes(97);
		
		long minutosDePermanencia = ChronoUnit.MINUTES.between(dataHoraEntrada, dataHoraSaida);

		double horasDePermanencia = Math.round(minutosDePermanencia / 60.0);

		if (minutosDePermanencia % 60 != 0) {
			horasDePermanencia += 1;
		}

		return valorPorHora.multiply(new BigDecimal(horasDePermanencia));
	}

	public PermanenciaMediaDiaria getRelatorioPermanenciaMediaDiaria(String data) {
		LocalDateTime inicioDia = toLocalDateTime(data, 0, 0, 0);
		LocalDateTime finalDia = toLocalDateTime(data, 23, 59, 59);
		try {
			List<Ocupacao> ocupacoes = repo.findByPeriod(inicioDia, finalDia);
			double mediaEmHoras = calcularMediaPermanencia(ocupacoes);
			return new PermanenciaMediaDiaria(mediaEmHoras);
		} catch (Exception e) {
			throw new RuntimeException("Não existem registros nesse dia.");
		}
	}

	public PermanenciaMediaPeriodo getRelatorioPermanenciaMediaPeriodo(String dataInicio, String dataFinal) {
		LocalDateTime periodoInicio = toLocalDateTime(dataInicio, 0, 0, 0);
		LocalDateTime periodoFinal = toLocalDateTime(dataFinal, 23, 59, 59);
		try {
			List<Ocupacao> ocupacoes = repo.findByPeriod(periodoInicio, periodoFinal);
			double mediaEmHoras = calcularMediaPermanencia(ocupacoes);
			return new PermanenciaMediaPeriodo(mediaEmHoras);
		} catch (Exception e) {
			throw new RuntimeException("Não existem registros nesse período.");
		}

	}

	public FinanceiroPeriodo getRelatorioFinanceiroPeriodo(String dataInicio, String dataFinal) {
		LocalDateTime periodoInicio = toLocalDateTime(dataInicio, 0, 0, 0);
		LocalDateTime periodoFinal = toLocalDateTime(dataFinal, 23, 59, 59);
		try {
			List<Ocupacao> ocupacoes = repo.findByPeriod(periodoInicio, periodoFinal);
			BigDecimal valorMaximo = calcularValorMaximo(ocupacoes);
			BigDecimal valorMinimo = calcularValorMinimo(ocupacoes);
			BigDecimal valorTotal = calcularValorTotal(ocupacoes);
			
			return new FinanceiroPeriodo(valorMaximo, valorMinimo, valorTotal);
		} catch (Exception e) {
			throw new RuntimeException("Não existem registros nesse período.");
		}
	}

	private BigDecimal calcularValorTotal(List<Ocupacao> ocupacoes) {
		BigDecimal valorTotal = new BigDecimal(0);
		for (Ocupacao o : ocupacoes) {
			valorTotal = valorTotal.add(o.getValorPago());
		}
		return valorTotal;
	}

	private BigDecimal calcularValorMinimo(List<Ocupacao> ocupacoes) {
		BigDecimal valorMinimo = new BigDecimal(0);
		List<BigDecimal> valores = new ArrayList<>();
		for (Ocupacao o : ocupacoes) {
			valores.add(o.getValorPago());
		}
		valorMinimo = valores.stream().min(Comparator.comparing(BigDecimal::doubleValue)).orElse(null);
		return valorMinimo;
	}

	private BigDecimal calcularValorMaximo(List<Ocupacao> ocupacoes) {
		BigDecimal valorMaximo = new BigDecimal(0);
		List<BigDecimal> valores = new ArrayList<>();
		for (Ocupacao o : ocupacoes) {
			valores.add(o.getValorPago());
		}
		valorMaximo = valores.stream().max(Comparator.comparing(BigDecimal::doubleValue)).orElse(null);
		return valorMaximo;
	}

	private LocalDateTime toLocalDateTime(String data, int... tempo) {
		String[] arrayData = data.split("-");
		int dia = Integer.parseInt(arrayData[0]);
		int mes = Integer.parseInt(arrayData[1]);
		int ano = Integer.parseInt(arrayData[2]);
		return LocalDateTime.of(ano, mes, dia, tempo[0], tempo[1], tempo[2]);
	}

	private double calcularMediaPermanencia(List<Ocupacao> ocupacoes) {
		long totalDeSegundos = 0;
		for (Ocupacao o : ocupacoes) {
			totalDeSegundos += ChronoUnit.SECONDS.between(o.getDataHoraEntrada(), o.getDataHoraSaida());
		}
		double mediaEmSegundos = totalDeSegundos / ocupacoes.size();
		double mediaEmHoras = mediaEmSegundos / 3600;

		return mediaEmHoras;
	}

}
