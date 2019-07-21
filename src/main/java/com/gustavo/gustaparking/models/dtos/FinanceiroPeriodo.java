package com.gustavo.gustaparking.models.dtos;

import java.math.BigDecimal;

public class FinanceiroPeriodo {

	private BigDecimal valorMaximo;
	private BigDecimal valorMinimo;
	private BigDecimal valorTotal;

	public FinanceiroPeriodo(BigDecimal valorMaximo, BigDecimal valorMinimo, BigDecimal valorTotal) {
		super();
		this.valorMaximo = valorMaximo;
		this.valorMinimo = valorMinimo;
		this.valorTotal = valorTotal;
	}

	public BigDecimal getValorMaximo() {
		return valorMaximo;
	}

	public void setValorMaximo(BigDecimal valorMaximo) {
		this.valorMaximo = valorMaximo;
	}

	public BigDecimal getValorMinimo() {
		return valorMinimo;
	}

	public void setValorMinimo(BigDecimal valorMinimo) {
		this.valorMinimo = valorMinimo;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

}
