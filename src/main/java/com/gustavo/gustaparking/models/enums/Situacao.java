package com.gustavo.gustaparking.models.enums;

public enum Situacao {
	LIVRE(0), OCUPADO(1);

	private Integer codigo;

	Situacao(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public static Situacao toEnum(Integer codigo) {
		if (codigo == null)
			return null;

		for (Situacao s : Situacao.values()) {
			if (codigo.equals(s.getCodigo())) {
				return s;
			}
		}

		throw new IllegalArgumentException("Código inválido: " + codigo);
	}
}
