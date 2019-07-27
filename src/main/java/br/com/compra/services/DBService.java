package br.com.compra.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
import br.com.compra.domain.enums.Perfil;
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

@Service
public class DBService {

	@Autowired
	BCryptPasswordEncoder pe;
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
	
	public void instantiateTestDataBase() throws ParseException, InterruptedException {
		Categoria c1= new Categoria(null,"Informatica");
		Categoria c2= new Categoria(null,"Escritorio");
		Categoria c3= new Categoria(null,"Cama Mesa e Banho");
		Categoria c4= new Categoria(null,"Eletrônico");
		Categoria c5= new Categoria(null,"Jardinagem");
		Categoria c6= new Categoria(null,"Decoração");
		Categoria c7= new Categoria(null,"Perfumaria");
		Categoria c8= new Categoria(null,"Teste");

		
		Produto p1 = new Produto("Computador", 2000.00);
		Produto p2 = new Produto("Impressora", 800.00);
		Produto p3 = new Produto("Mouse", 80.00);
		Produto p4 = new Produto("Mesa de Escritorio",300.00);
		Produto p5 = new Produto("Toalha",50.00);
		Produto p6 = new Produto("Colcha",200.00);
		Produto p7 = new Produto("TV true calor",1200.00);
		Produto p8 = new Produto("Roçadeira",800.00);
		Produto p9 = new Produto("Abajour",100.00);
		Produto p10 = new Produto("Pendente",180.00);
		Produto p11 = new Produto("Shampoo",90.00);
		Produto p12 = new Produto("Produto 12",10.00);
		Produto p13 = new Produto("Produto 13",10.00);
		Produto p14 = new Produto("Produto 14",10.00);
		Produto p15 = new Produto("Produto 15",10.00);
		Produto p16 = new Produto("Produto 16",10.00);
		Produto p17 = new Produto("Produto 17",10.00);
		Produto p18 = new Produto("Produto 18",10.00);
		Produto p19 = new Produto("Produto 19",10.00);
		Produto p20 = new Produto("Produto 20",10.00);
		Produto p21 = new Produto("Produto 21",10.00);
		Produto p22 = new Produto("Produto 22",10.00);
		Produto p23 = new Produto("Produto 23",10.00);
		Produto p24 = new Produto("Produto 24",10.00);
		Produto p25 = new Produto("Produto 25",10.00);
		Produto p26 = new Produto("Produto 26",10.00);
		Produto p27 = new Produto("Produto 27",10.00);
		Produto p28 = new Produto("Produto 28",10.00);
		Produto p29 = new Produto("Produto 29",10.00);
		Produto p30 = new Produto("Produto 30",10.00);
		Produto p31 = new Produto("Produto 31",10.00);
		Produto p32 = new Produto("Produto 32",10.00);
		Produto p33 = new Produto("Produto 33",10.00);
		Produto p34 = new Produto("Produto 34",10.00);
		Produto p35 = new Produto("Produto 35",10.00);
		Produto p36 = new Produto("Produto 36",10.00);
		Produto p37 = new Produto("Produto 37",10.00);
		Produto p38 = new Produto("Produto 38",10.00);
		Produto p39 = new Produto("Produto 39",10.00);
		Produto p40 = new Produto("Produto 40",10.00);
		Produto p41 = new Produto("Produto 41",10.00);
		Produto p42 = new Produto("Produto 42",10.00);
		Produto p43 = new Produto("Produto 43",10.00);
		Produto p44 = new Produto("Produto 44",10.00);
		Produto p45 = new Produto("Produto 45",10.00);
		Produto p46 = new Produto("Produto 46",10.00);
		Produto p47 = new Produto("Produto 47",10.00);
		Produto p48 = new Produto("Produto 48",10.00);
		Produto p49 = new Produto("Produto 49",10.00);
		Produto p50 = new Produto("Produto 50",10.00);
		
		
		
		c1.getProdutos().addAll(Arrays.asList(p1 ,p2 ,p3 ,p4 ,p5 ,p6 ,p7 ,p8 ,p9 ,p10,
											  p11,p12,p13,p14,p15,p16,p17,p18,p19,p20,
											  p21,p22,p23,p24,p25,p26,p27,p28,p29,p30,
											  p31,p32,p33,p34,p35,p36,p37,p38,p39,p40,
											  p41,p42,p43,p44,p45,p46,p47,p48,p49,p50));
		c2.getProdutos().addAll(Arrays.asList(p2,p4));
		c3.getProdutos().addAll(Arrays.asList(p5,p6));
		c4.getProdutos().addAll(Arrays.asList(p1,p2,p3,p7));
		c5.getProdutos().addAll(Arrays.asList(p8));
		c6.getProdutos().addAll(Arrays.asList(p9,p10));
		c7.getProdutos().addAll(Arrays.asList(p11));
		
		
		
		p1.getCategorias().addAll(Arrays.asList(c1,c4));
		p2.getCategorias().addAll(Arrays.asList(c1,c2,c4));
		p3.getCategorias().addAll(Arrays.asList(c1,c4));
		p4.getCategorias().addAll(Arrays.asList(c2));
		p5.getCategorias().addAll(Arrays.asList(c3));
		p6.getCategorias().addAll(Arrays.asList(c3));
		p7.getCategorias().addAll(Arrays.asList(c4));
		p8.getCategorias().addAll(Arrays.asList(c5));
		p9.getCategorias().addAll(Arrays.asList(c6));
		p10.getCategorias().addAll(Arrays.asList(c6));
		p11.getCategorias().addAll(Arrays.asList(c7));
		
		p12.getCategorias().addAll(Arrays.asList(c1));
		p13.getCategorias().addAll(Arrays.asList(c1));
		p14.getCategorias().addAll(Arrays.asList(c1));
		p15.getCategorias().addAll(Arrays.asList(c1));
		p16.getCategorias().addAll(Arrays.asList(c1));
		p17.getCategorias().addAll(Arrays.asList(c1));
		p18.getCategorias().addAll(Arrays.asList(c1));
		p19.getCategorias().addAll(Arrays.asList(c1));
		p20.getCategorias().addAll(Arrays.asList(c1));
		p21.getCategorias().addAll(Arrays.asList(c1));
		p22.getCategorias().addAll(Arrays.asList(c1));
		p23.getCategorias().addAll(Arrays.asList(c1));
		p24.getCategorias().addAll(Arrays.asList(c1));
		p25.getCategorias().addAll(Arrays.asList(c1));
		p26.getCategorias().addAll(Arrays.asList(c1));
		p27.getCategorias().addAll(Arrays.asList(c1));
		p28.getCategorias().addAll(Arrays.asList(c1));
		p29.getCategorias().addAll(Arrays.asList(c1));
		p30.getCategorias().addAll(Arrays.asList(c1));
		p31.getCategorias().addAll(Arrays.asList(c1));
		p32.getCategorias().addAll(Arrays.asList(c1));
		p33.getCategorias().addAll(Arrays.asList(c1));
		p34.getCategorias().addAll(Arrays.asList(c1));
		p35.getCategorias().addAll(Arrays.asList(c1));
		p36.getCategorias().addAll(Arrays.asList(c1));
		p37.getCategorias().addAll(Arrays.asList(c1));
		p38.getCategorias().addAll(Arrays.asList(c1));
		p39.getCategorias().addAll(Arrays.asList(c1));
		p40.getCategorias().addAll(Arrays.asList(c1));
		p41.getCategorias().addAll(Arrays.asList(c1));
		p42.getCategorias().addAll(Arrays.asList(c1));
		p43.getCategorias().addAll(Arrays.asList(c1));
		p44.getCategorias().addAll(Arrays.asList(c1));
		p45.getCategorias().addAll(Arrays.asList(c1));
		p46.getCategorias().addAll(Arrays.asList(c1));
		p47.getCategorias().addAll(Arrays.asList(c1));
		p48.getCategorias().addAll(Arrays.asList(c1));
		p49.getCategorias().addAll(Arrays.asList(c1));
		p50.getCategorias().addAll(Arrays.asList(c1));
		
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
		
		Cliente cli1= new Cliente("Maria Silva ", "luciano_lpn22@yahoo.com.br","36378912377",TipoCliente.PESSOAFISICA,pe.encode("123"));
		Cliente cli2= new Cliente("Anan Costa ", "luciano-lpn@hotmail.com.br","44859685024",TipoCliente.PESSOAFISICA,pe.encode("123"));
		cli2.addPerfis(Perfil.ADMIN);
		cli1.getTelefones().addAll(Arrays.asList("27363323","92838393"));
		cli2.getTelefones().addAll(Arrays.asList("9080809090","888799086"));
		
		
		Endereco end1= new Endereco("Rua Flores","300", "Apto 203", "Jaardim", "38220834",cli1 , cid1);
		Endereco end2= new Endereco("Avenida Matos", "105","sala 800", "centro", "38777012",cli1, cid2);
		Endereco end3= new Endereco("Avenida americas", "15"," 800", "casquerio", "38777012",cli2, cid2);
		cli1.getEnderecos().addAll(Arrays.asList(end1,end2));
		cli2.getEnderecos().addAll(Arrays.asList(end3));
		
		
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
		
		
		categoriaRepository.saveAll(Arrays.asList(c1,c2,c3,c4,c5,c6,c7,c8));
		produtoRepository.saveAll(Arrays.asList(p1 ,p2 ,p3 ,p4 ,p5 ,p6 ,p7 ,p8 ,p9 ,p10,
												p11,p12,p13,p14,p15,p16,p17,p18,p19,p20,
				  								p21,p22,p23,p24,p25,p26,p27,p28,p29,p30,
				  								p31,p32,p33,p34,p35,p36,p37,p38,p39,p40,
				  								p41,p42,p43,p44,p45,p46,p47,p48,p49,p50));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(cid1,cid2,cid3));
		
		clienteRepository.saveAll(Arrays.asList(cli1,cli2));
		enderecoRepository.saveAll(Arrays.asList(end1,end2,end3));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
		
		}
}
