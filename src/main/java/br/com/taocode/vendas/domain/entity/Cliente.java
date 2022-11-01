package br.com.taocode.vendas.domain.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "CLIENTES")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CLIENTE")
	private Integer id;
	@Column( name = "NOME", length = 100)
	private String nome;
	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
	private Set<Pedido> pedidos;

	public Cliente () { }

	public Cliente (String nome) {
		this.nome = nome;
	}
	public Cliente (Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<Pedido> getPedidos() {
		return pedidos;
	}

	@Override
	public String toString() {
		return "Cliente { " +
				"id=" + id +
				", nome='" + nome + "\' } ";
				//", nome='" + nome + '\'' +
				//'}';
	}
}
