package com.gustavo.gustaparking.models.dtos;

import java.time.LocalDateTime;

public class VagaDTO {

	private String nomeDoEstacionamento;
	private LocalDateTime dataHoraConsulta;
	private Long vagasDisponiveis;
	private Long vagasOcupadas;
	private Long totalVagas;

	public VagaDTO(String nomeDoEstacionamento, LocalDateTime dataHoraConsulta, Long vagasDisponiveis, Long vagasOcupadas, Long totalVagas) {
		super();
		this.nomeDoEstacionamento = nomeDoEstacionamento;
		this.dataHoraConsulta = dataHoraConsulta;
		this.vagasDisponiveis = vagasDisponiveis;
		this.vagasOcupadas = vagasOcupadas;
		this.totalVagas = totalVagas;
	}

	public String getNomeDoEstacionamento() {
		return nomeDoEstacionamento;
	}

	public void setNomeDoEstacionamento(String nomeDoEstacionamento) {
		this.nomeDoEstacionamento = nomeDoEstacionamento;
	}

	public LocalDateTime getDataHoraConsulta() {
		return dataHoraConsulta;
	}

	public void setDataHoraConsulta(LocalDateTime dataHoraConsulta) {
		this.dataHoraConsulta = dataHoraConsulta;
	}

	public Long getVagasDisponiveis() {
		return vagasDisponiveis;
	}

	public void setVagasDisponiveis(Long vagasDisponiveis) {
		this.vagasDisponiveis = vagasDisponiveis;
	}

	public Long getVagasOcupadas() {
		return vagasOcupadas;
	}

	public void setVagasOcupadas(Long vagasOcupadas) {
		this.vagasOcupadas = vagasOcupadas;
	}

	public Long getTotalVagas() {
		return totalVagas;
	}

	public void setTotalVagas(Long totalVagas) {
		this.totalVagas = totalVagas;
	}

}
