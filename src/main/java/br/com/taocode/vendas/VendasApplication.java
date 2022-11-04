package br.com.taocode.vendas;


import br.com.taocode.vendas.domain.entity.Cliente;
import br.com.taocode.vendas.domain.entity.Pedido;
import br.com.taocode.vendas.domain.repository.ClienteRepositoryJPA;
import br.com.taocode.vendas.domain.repository.ClienteRepository;
import br.com.taocode.vendas.domain.repository.PedidoRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class VendasApplication {

	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}
}
