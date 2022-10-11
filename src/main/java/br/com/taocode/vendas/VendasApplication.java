package br.com.taocode.vendas;


import br.com.taocode.vendas.domain.entity.Cliente;
import br.com.taocode.vendas.domain.repository.ClienteRepositoryJPA;
import br.com.taocode.vendas.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

	@Bean
	public CommandLineRunner init(
			@Autowired ClienteRepositoryJPA clienteRepository,
			@Autowired ClienteRepository clienteJpaRepository) {
		return args -> {
			//this.usarRepositoryProprio(clienteRepository);
			//ou
			this.usarJpaRepository(clienteJpaRepository);
		};
	}

	private void usarJpaRepository(ClienteRepository clienteJpaRepository) {
		// Inserindo os dados
		Cliente cliente = new Cliente();
		cliente.setNome("J.Henrique");
		clienteJpaRepository.save(cliente);
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
		SpringApplication.run(VendasApplication.class, args);
	}
}
