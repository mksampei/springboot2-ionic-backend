package com.samarici.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.samarici.cursomc.domain.ItemPedido;
import com.samarici.cursomc.domain.PagamentoComBoleto;
import com.samarici.cursomc.domain.Pedido;
import com.samarici.cursomc.domain.enums.EstadoPagamento;
import com.samarici.cursomc.repositories.ItemPedidoRepository;
import com.samarici.cursomc.repositories.PagamentoRepository;
import com.samarici.cursomc.repositories.PedidoRepository;
import com.samarici.cursomc.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	@Autowired
	private BoletoService boletoService;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private PagamentoRepository pagamentoRepo;
	@Autowired
	private ItemPedidoRepository itemPedidoRepo;

	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());

		obj.getPagamento().setEstadoPagto(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);

		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}

		obj = repo.save(obj);

		pagamentoRepo.save(obj.getPagamento());

		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoService.find(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}

		itemPedidoRepo.saveAll(obj.getItens());
		return obj;
	}

}
