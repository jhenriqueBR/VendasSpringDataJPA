package br.com.taocode.vendas.domain.repository;

import br.com.taocode.vendas.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	@Query(value = "SELECT c FROM Cliente c WHERE c.nome LIKE :nomeCliente")
	List<Cliente> buscarPorNome (@Param("nomeCliente") String nome);

	//List<Cliente> findByNome(String nome);
	//List<Cliente> findByNomeLike(String nome);


}
