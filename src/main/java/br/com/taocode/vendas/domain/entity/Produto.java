package br.com.taocode.vendas.domain.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "PRODUTOS")
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRODUTO")
	private Integer id;

	@Column(name = "DESCRICAO", nullable = false)
	private String descricao;

	@Column(name = "PRECO_UNITARIO", precision = 19, scale = 2)
	private BigDecimal preco;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
}
