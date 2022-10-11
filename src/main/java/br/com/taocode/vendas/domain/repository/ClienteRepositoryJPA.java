package br.com.taocode.vendas.domain.repository;

import br.com.taocode.vendas.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class ClienteRepositoryJPA {
	@Autowired
	private EntityManager entityManager;

	private static final String jpqlBuscarPorNome = "SELECT c FROM Cliente c WHERE c.nome LIKE :nome ";
	private static final String jpqlBuscarTodos = "SELECT c FROM Cliente c";
	private static final String jpqlDELETE = "DELETE FROM Cliente";

	public void salvar (Cliente cliente) {
		this.entityManager.persist(cliente);
	}

	public void atualizar (Integer id) {
		this.atualizar(this.buscarPorId(id));
	}

	public void atualizar (Cliente cliente) {
		this.entityManager.merge(cliente);
	}

	public void apagar (Integer id) {
		this.apagar(this.buscarPorId(id));
	}

	private void apagar (Cliente cliente) {
		this.entityManager.remove(cliente);
	}

	public void apagarTudo () {
		this.entityManager.createQuery(jpqlDELETE).executeUpdate();
	}

	@Transactional(readOnly = true)
	public Cliente buscarPorId ( Integer id ) {
		return this.entityManager.find(Cliente.class, id);
	}

	@Transactional(readOnly = true)
	public List<Cliente> buscarPorNome (String nome) {
		List<Cliente> clientes = new ArrayList<>();

		TypedQuery<Cliente> query = this.entityManager.createQuery(this.jpqlBuscarPorNome, Cliente.class);
		query.setParameter("nome", nome);

		clientes = query.getResultList();

		return clientes;
	}

	@Transactional(readOnly = true)
	public Cliente ler (Integer id) {
		return this.buscarPorId(id);
	}


	@Transactional(readOnly = true)
	public List<Cliente> listarTodos () {
		return this.entityManager
				.createQuery(jpqlBuscarTodos, Cliente.class)
				.getResultList();
	}
}
