package br.com.taocode.vendas;

import br.com.taocode.vendas.domain.entity.Cliente;
import br.com.taocode.vendas.domain.entity.Pedido;
import br.com.taocode.vendas.domain.repository.ClienteRepository;
import br.com.taocode.vendas.domain.repository.ClienteRepositoryJPA;
import br.com.taocode.vendas.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

//@SpringBootApplication
public class VendasApplicationWithRunner {

	@Bean
	public CommandLineRunner init(
			@Autowired ClienteRepositoryJPA clienteRepository,
			@Autowired ClienteRepository clienteJpaRepository,
			@Autowired PedidoRepository  pedidoRepository
			) {
		return args -> {
			//this.usarRepositoryProprio(clienteRepository);
			//ou
			this.usarJpaRepository(clienteJpaRepository, pedidoRepository);
		};
	}

	private void usarJpaRepository(
			ClienteRepository clienteJpaRepository,
			PedidoRepository  pedidoRepository 	) {
		// Inserindo os dados
		Cliente cliente = new Cliente();
		cliente.setNome("J.Henrique");
		clienteJpaRepository.save(cliente);

		Pedido p = new Pedido();
		p.setCliente(cliente);
		p.setDataPedido(LocalDate.now());
		p.setTotal(BigDecimal.valueOf(0));

		pedidoRepository.save(p);

		Pedido p2 = new Pedido();
		p2.setCliente(cliente);
		p2.setDataPedido(LocalDate.now());
		p2.setTotal(BigDecimal.valueOf(0));

		pedidoRepository.save(p2);


		// Lendo por @Query
		//Cliente clienteRetorno = clienteJpaRepository.findClienteFetchPedidos(cliente.getId());
		Cliente clienteRetorno = clienteJpaRepository.buscarPorIdComPedidos(cliente.getId());

		System.out.println("");
		System.out.println("*** Listando os Pedidos do Cliente J.Henrique ***");
		System.out.println(clienteRetorno);
		System.out.println(clienteRetorno.getPedidos());

		System.out.println();
		clienteRetorno.getPedidos().forEach(pedido -> {
			System.out.println(pedido);
		});

		// Lendo pelo findId
		Cliente clienteRetorno2 = clienteJpaRepository.buscarPorIdComPedidos(cliente.getId());

		System.out.println("");
		System.out.println("*** Listando os Pedidos do Cliente J.Henrique ***");
		System.out.println(clienteRetorno2);
		System.out.println(clienteRetorno2.getPedidos());

		System.out.println();
		clienteRetorno2.getPedidos().forEach(pedido -> {
			System.out.println(pedido);
		});



		clienteJpaRepository.save(new Cliente("Iwana"));
		clienteJpaRepository.save(new Cliente("Merize"));
		clienteJpaRepository.save(new Cliente("J.Henrique Castro"));

		List<Cliente> clientes = clienteJpaRepository.findAll();

		System.out.println("");
		System.out.println("*** 1a Listagem ***");
		clientes.forEach(c -> {
			System.out.printf("Cliente { id=%d, nome='%s' } %n", c.getId(), c.getNome());
		});

		// Alterando o nome do primeiro registro
		//var clientePorNome = clienteJpaRepository.findByNome("J.Henrique Castro");
		var clientePorNome = clienteJpaRepository.buscarPorNome("J.Henrique Castro");

		if (clientePorNome.size() > 0) {
			var clienteBanco = clientePorNome.get(0);

			clienteBanco.setNome("José Henrique");
			clienteJpaRepository.save(clienteBanco);
		}

		// Outra forma de listar tudo, mas tem que ter o método toString na classe cliente
		clientes = clienteJpaRepository.findAll();
		System.out.println("");
		System.out.println("*** 2a Listagem ***");
		clientes.forEach(System.out::println);

		// Excluindo o Cliente Merize
		//clientePorNome = clienteJpaRepository.findByNomeLike("Merize");
		clientePorNome = clienteJpaRepository.buscarPorNome("J.Henrique Castro");

		if (clientePorNome.size() > 0) {
			clienteJpaRepository.delete(clientePorNome.get(0));
		}

		System.out.println("");
		System.out.println("*** 3a Listagem ***");
		clienteJpaRepository.findAll().forEach(System.out::println);

		// Apagando tudo
		pedidoRepository.deleteAll();
		clienteJpaRepository.deleteAll();

		// Verificando se existem registros
		clientes = clienteJpaRepository.findAll();

		if (clientes.isEmpty()) {
			System.out.printf("%n*** Não há Registros ***");
		} else {
			System.out.println("");
			clientes.forEach(System.out::println);
		};
	}

	private void usarRepositoryProprio(ClienteRepositoryJPA clienteRepository) {
		// Inserindo os dados
		Cliente cliente = new Cliente();
		cliente.setNome("J.Henrique");
		clienteRepository.salvar(cliente);
		clienteRepository.salvar(new Cliente("Iwana"));
		clienteRepository.salvar(new Cliente("Merize"));

		List<Cliente> clientes = clienteRepository.listarTodos();

		System.out.println("");
		System.out.println("*** 1a Listagem ***");
		clientes.forEach(c -> {
			System.out.printf("Cliente { id=%d, nome='%s' } %n", c.getId(), c.getNome());
		});

		// Alterando o nome do primeiro registro
		var clientePorNome = clienteRepository.buscarPorNome("J.henrique");
		var clienteBanco = new Cliente();

		if (clientePorNome.size() > 0) {
			clienteBanco = clientePorNome.get(0);

			clienteBanco.setNome("José Henrique");
			clienteRepository.atualizar(clienteBanco);
		}

		// Outra forma de listar tudo, mas tem que ter o método toString na classe cliente
		clientes = clienteRepository.listarTodos();
		System.out.println("");
		System.out.println("*** 2a Listagem ***");
		clientes.forEach(System.out::println);


		// Excluindo o Cliente Merize
		clientePorNome = clienteRepository.buscarPorNome("Merize");
		clienteBanco = new Cliente();

		if (clientePorNome.size() > 0) {
			clienteRepository.apagar(clientePorNome.get(0).getId());
		}

		System.out.println("");
		System.out.println("*** 3a Listagem ***");
		clienteRepository.listarTodos().forEach(System.out::println);

		// Apagando tudo
		clienteRepository.apagarTudo();

		// Verificando se existem registros
		clientes = clienteRepository.listarTodos();

		if (clientes.isEmpty()) {
			System.out.printf("%n*** Não há Registros ***");
		} else {
			System.out.println("");
			clientes.forEach(System.out::println);
		};
	}


	public static void main(String[] args) {
		SpringApplication.run(VendasApplicationWithRunner.class, args);
	}
}
