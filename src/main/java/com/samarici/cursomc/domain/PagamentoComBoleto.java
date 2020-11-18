package com.samarici.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.samarici.cursomc.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComBoleto extends Pagamento {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date dataVencimento;
	private Date dataPagmento;

	public PagamentoComBoleto() {
		super();
	}

	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVencimento,
			Date dataPagmento) {
		super(id, estado, pedido);
		this.dataVencimento = dataVencimento;
		this.dataPagmento = dataPagmento;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagmento() {
		return dataPagmento;
	}

	public void setDataPagmento(Date dataPagmento) {
		this.dataPagmento = dataPagmento;
	}

}
