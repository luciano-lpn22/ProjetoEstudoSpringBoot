package br.com.compra;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.compra.domain.Categoria;
import br.com.compra.domain.Cidade;
import br.com.compra.domain.Cliente;
import br.com.compra.domain.Endereco;
import br.com.compra.domain.Estado;
import br.com.compra.domain.ItemPedido;
import br.com.compra.domain.Pagamento;
import br.com.compra.domain.PagamentoComBoleto;
import br.com.compra.domain.PagamentoComCartao;
import br.com.compra.domain.Pedido;
import br.com.compra.domain.Produto;
import br.com.compra.domain.enums.EstadoPagamento;
import br.com.compra.domain.enums.TipoCliente;
import br.com.compra.repositories.CategoriaRepository;
import br.com.compra.repositories.CidadeRepository;
import br.com.compra.repositories.ClienteRepository;
import br.com.compra.repositories.EnderecoRepository;
import br.com.compra.repositories.EstadoRepository;
import br.com.compra.repositories.ItemPedidoRepository;
import br.com.compra.repositories.PagamentoRepository;
import br.com.compra.repositories.PedidoRepository;
import br.com.compra.repositories.ProdutoRepository;

@SpringBootApplication
public class CompramcApplication implements  CommandLineRunner{

	@Autowired
	CategoriaRepository categoriaRepository;
	@Autowired
	ProdutoRepository produtoRepository;
	@Autowired
	CidadeRepository cidadeRepository;
	@Autowired
	EstadoRepository estadoRepository;
	@Autowired
	EnderecoRepository enderecoRepository;
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	PagamentoRepository pagamentoRepository;
	@Autowired
	PedidoRepository pedidoRepository;
	@Autowired
	ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CompramcApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		
		Categoria c1= new Categoria(null,"Informatica");
		Categoria c2= new Categoria(null,"Escritorio");
		Categoria c3= new Categoria(null,"Cama Mesa e Banho");
		Categoria c4= new Categoria(null,"Eletrônico");
		Categoria c5= new Categoria(null,"Jardinagem");
		Categoria c6= new Categoria(null,"Decoração");
		Categoria c7= new Categoria(null,"Perfumaria");

		
		Produto p1 = new Produto("Computador", 2000.00);
		Produto p2 = new Produto("Impressora", 800.00);
		Produto p3 = new Produto("Mouse", 80.00);
		
		c2.getProdutos().addAll(Arrays.asList(p2));
		c1.getProdutos().addAll(Arrays.asList(p1,p3,p2));
		
		p1.getCategorias().addAll(Arrays.asList(c1));
		p2.getCategorias().addAll(Arrays.asList(c1,c2));
		p3.getCategorias().addAll(Arrays.asList(c1));
		
		Estado est1= new Estado("Minas Gerais");
		Estado est2= new Estado("São Paulo");
		
		Cidade cid3= new Cidade("Campinas");
		Cidade cid2= new Cidade("São Paulo");
		Cidade cid1= new Cidade("Uberlândia");
		est2.getCidades().addAll(Arrays.asList(cid2,cid3));
		est1.getCidades().addAll(Arrays.asList(cid1));
		
		cid2.setEstado(est2);
		cid3.setEstado(est2);
		cid1.setEstado(est1);
		
		Cliente cli1= new Cliente("Maria Silva ", "maria@gmail.com","36378912377",TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323","92838393"));
		
		
		Endereco end1= new Endereco("Rua Flores","300", "Apto 203", "Jaardim", "38220834",cli1 , cid1);
		Endereco end2= new Endereco("Avenida Matos", "105","sala 800", "centro", "38777012",cli1, cid2);
		cli1.getEnderecos().addAll(Arrays.asList(end1,end2));
		
		
		SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1= new Pedido(null, dateFormat.parse("30/09/2017 10:32"), cli1, end1);
		Pedido ped2= new Pedido(null, dateFormat.parse("10/10/2017 19:35"), cli1, end2);
		
		Pagamento pagto1= new PagamentoComCartao(ped1, EstadoPagamento.QUITADO, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2= new PagamentoComBoleto(ped2, EstadoPagamento.PENDENTE, dateFormat.parse("20/10/2017 00:00"),null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		
		ItemPedido ip1= new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2= new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3= new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		
		categoriaRepository.saveAll(Arrays.asList(c1,c2,c3,c4,c5,c6,c7));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(cid1,cid2,cid3));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(end1,end2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
		
	}

}
