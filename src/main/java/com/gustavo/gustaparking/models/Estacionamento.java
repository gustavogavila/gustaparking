package com.gustavo.gustaparking.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "estacionamento")
public class Estacionamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable = false)
	private Long id;

	@Column(name = "nome", nullable = false, length = 30)
	private String nome;

	@Column(name = "valor_por_hora", nullable = false)
	private BigDecimal valorPorHora;

	@Column(name = "total_de_vagas", nullable = false)
	private Integer totalDeVagas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getValorPorHora() {
		return valorPorHora;
	}

	public void setValorPorHora(BigDecimal valorPorHora) {
		this.valorPorHora = valorPorHora;
	}

	public Integer getTotalDeVagas() {
		return totalDeVagas;
	}

	public void setTotalDeVagas(Integer totalDeVagas) {
		this.totalDeVagas = totalDeVagas;
	}

}
