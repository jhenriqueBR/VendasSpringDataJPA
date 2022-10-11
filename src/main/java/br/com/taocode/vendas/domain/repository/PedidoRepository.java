package br.com.taocode.vendas.domain.repository;

import br.com.taocode.vendas.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
