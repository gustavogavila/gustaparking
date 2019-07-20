package com.gustavo.gustaparking.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ocupacao")
public class Ocupacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "valor_pago")
	private BigDecimal valorPago;

	@Column(name = "data_hora_entrada")
	private LocalDateTime dataHoraEntrada;

	@Column(name = "data_hora_saida")
	private LocalDateTime dataHoraSaida;
	
	@OneToOne
	@JoinColumn(name = "veiculo_id")
	private Veiculo veiculo;
	
	@OneToOne
	@JoinColumn(name = "vaga_id")
	private Vaga vaga;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	public LocalDateTime getDataHoraEntrada() {
		return dataHoraEntrada;
	}

	public void setDataHoraEntrada(LocalDateTime dataHoraEntrada) {
		this.dataHoraEntrada = dataHoraEntrada;
	}

	public LocalDateTime getDataHoraSaida() {
		return dataHoraSaida;
	}

	public void setDataHoraSaida(LocalDateTime dataHoraSaida) {
		this.dataHoraSaida = dataHoraSaida;
	}

}
