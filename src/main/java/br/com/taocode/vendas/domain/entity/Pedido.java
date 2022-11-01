package br.com.taocode.vendas.domain.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "PEDIDOS")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PEDIDO")
	private Integer id;

	@ManyToOne()
	@JoinColumn(name="CLIENTE")
	private Cliente cliente;

	@Column(name = "DATA_PEDIDO", nullable = false)
	private LocalDate dataPedido;

	@Column(name = "TOTAL", precision = 19, scale = 2)
	private BigDecimal total;

	@OneToMany (mappedBy = "pedido")
	private List<PedidoItem> itens = new ArrayList<PedidoItem>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalDate getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Pedido{" +
				"id=" + id +
				", dataPedido=" + dataPedido +
				", total=" + total +
				'}';
	}
}
