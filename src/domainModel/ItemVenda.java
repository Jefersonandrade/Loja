package domainModel;

import javax.persistence.*;


import domainModel.Produto;
import domainModel.Venda;

@Entity
@Table(name="Itemvendas")
public class ItemVenda {
	
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@OneToMany(fetch=FetchType.LAZY)
	@Id
	private int id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_produto")
	private Produto produto;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_venda")
	private Venda venda;
	
	@Column
	private int quantidade;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto id_produto) {
		this.produto = id_produto;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda id_venda) {
		this.venda = id_venda;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}


}
