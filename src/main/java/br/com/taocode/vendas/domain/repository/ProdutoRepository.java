package br.com.taocode.vendas.domain.repository;

import br.com.taocode.vendas.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
