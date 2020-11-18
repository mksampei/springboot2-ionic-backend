package com.samarici.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.samarici.cursomc.domain.Categoria;
import com.samarici.cursomc.domain.Cidade;
import com.samarici.cursomc.domain.Cliente;
import com.samarici.cursomc.domain.Endereco;
import com.samarici.cursomc.domain.Estado;
import com.samarici.cursomc.domain.ItemPedido;
import com.samarici.cursomc.domain.Pagamento;
import com.samarici.cursomc.domain.PagamentoComBoleto;
import com.samarici.cursomc.domain.PagamentoComCartao;
import com.samarici.cursomc.domain.Pedido;
import com.samarici.cursomc.domain.Produto;
import com.samarici.cursomc.domain.enums.EstadoPagamento;
import com.samarici.cursomc.domain.enums.TipoCliente;
import com.samarici.cursomc.repositories.CategoriaRepository;
import com.samarici.cursomc.repositories.CidadeRepository;
import com.samarici.cursomc.repositories.ClienteRepository;
import com.samarici.cursomc.repositories.EnderecoRepository;
import com.samarici.cursomc.repositories.EstadoRepository;
import com.samarici.cursomc.repositories.ItemPedidoRepository;
import com.samarici.cursomc.repositories.PagamentoRepository;
import com.samarici.cursomc.repositories.PedidoRepository;
import com.samarici.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository repoCategoria;
	@Autowired
	private ProdutoRepository repoProduto;
	@Autowired
	private EstadoRepository repoEstado;
	@Autowired
	private CidadeRepository repoCidade;
	@Autowired
	private ClienteRepository repoCliente;
	@Autowired
	private EnderecoRepository repoEndereco;
	@Autowired
	private PedidoRepository repoPedido;
	@Autowired
	private PagamentoRepository repoPagamento;
	@Autowired
	private ItemPedidoRepository repoItemPedido;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		repoCategoria.saveAll(Arrays.asList(cat1, cat2));

		repoProduto.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "Sao Paulo");

		Cidade cid1 = new Cidade(null, "Uberlandia", est1);
		Cidade cid2 = new Cidade(null, "Sao Paulo", est2);
		Cidade cid3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2, cid3));

		repoEstado.saveAll(Arrays.asList(est1, est2));
		repoCidade.saveAll(Arrays.asList(cid1, cid2, cid3));

		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "12345678911", TipoCliente.PESSOA_FISICA);

		cli1.getTelefones().addAll(Arrays.asList("971716677", "430302929"));

		Endereco end1 = new Endereco(null, "Rua Flores", "303", "apto 3", "Jardim", "01312000", cli1, cid1);
		Endereco end2 = new Endereco(null, "Avenida Muros", "105", "sala 12", "Centro", "09888200", cli1, cid2);
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));

		repoCliente.saveAll(Arrays.asList(cli1));
		repoEndereco.saveAll(Arrays.asList(end1, end2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, end2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"),
				null);
		ped2.setPagamento(pagto2);
		
		repoPedido.saveAll(Arrays.asList(ped1, ped2));
		repoPagamento.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		repoItemPedido.saveAll(Arrays.asList(ip1,ip2,ip3));
	}

}
