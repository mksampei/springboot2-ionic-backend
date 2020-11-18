package com.samarici.cursomc.domain.enums;

public enum TipoCliente {

	PESSOA_FISICA(1, "Pessoa Fisica"), PESSOA_JURIDICA(2, "Pessoa Juridica");

	private int cod;
	private String descricao;

	TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoCliente toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (TipoCliente tc : TipoCliente.values()) {
			if (cod.equals(tc.getCod())) {
				return tc;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}
