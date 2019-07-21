package com.gustavo.gustaparking.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parametro_sistema")
public class ParametroSistema {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "nome_estacionamento", length = 30, nullable = false)
	private String nomeEstacionamento;

	@Column(name = "valor_por_hora", nullable = false)
	private BigDecimal valorPorHora;

	public ParametroSistema() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeEstacionamento() {
		return nomeEstacionamento;
	}

	public void setNomeEstacionamento(String nomeEstacionamento) {
		this.nomeEstacionamento = nomeEstacionamento;
	}

	public BigDecimal getValorPorHora() {
		return valorPorHora;
	}

	public void setValorPorHora(BigDecimal valorPorHora) {
		this.valorPorHora = valorPorHora;
	}

}
