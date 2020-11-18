package com.samarici.cursomc;

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
import com.samarici.cursomc.domain.Produto;
import com.samarici.cursomc.domain.enums.TipoCliente;
import com.samarici.cursomc.repositories.CategoriaRepository;
import com.samarici.cursomc.repositories.CidadeRepository;
import com.samarici.cursomc.repositories.ClienteRepository;
import com.samarici.cursomc.repositories.EnderecoRepository;
import com.samarici.cursomc.repositories.EstadoRepository;
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
		
		cli1.getTelefones().addAll(Arrays.asList("971716677","430302929"));
		
		Endereco end1 = new Endereco(null, "Rua Flores", "303", "apto 3", "Jardim", "01312000", cli1, cid1);
		Endereco end2 = new Endereco(null, "Avenida Muros", "105", "sala 12", "Centro", "09888200", cli1, cid2);
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
		
		
		repoCliente.saveAll(Arrays.asList(cli1));
		repoEndereco.saveAll(Arrays.asList(end1, end2));
	}

}
