package br.com.taocode.vendas.domain.entity;

import javax.persistence.*;


/*
CREATE TABLE ITENS_PEDIDOS (
		ITEM_PEDIDO INT NOT NULL AUTO_INCREMENT,
		PEDIDO INT NOT NULL,
		PRODUTO INT NOT NULL,
		QUANTIDADE INT NOT NULL,
		PRIMARY KEY (ITEM_PEDIDO)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8;
*/

@Entity
@Table(name = "ITENS_PEDIDOS")
public class PedidoItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ITEM_PEDIDO")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "PEDIDO", nullable = false)
	private Pedido pedido;

	@ManyToOne
	@JoinColumn(name = "PRODUTO", nullable = false)
	private Produto produto;

	@Column(name = "QUANTIDADE", nullable = false)
	private Integer quantidade;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}
