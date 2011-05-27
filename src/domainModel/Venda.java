package domainModel;
import javax.persistence.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.*;

@Entity
@Table(name="vendas")
public class Venda {
	@Id
	//GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.REFRESH})
	@JoinColumn(name="id_cliente")
	private Cliente cliente;

	@Temporal(TemporalType.DATE)
	@Column(name="Data")
	private Date data;

	@Column(name="ValorTotal")
	private float valortotal;
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="itemvendas")
	private List<ItemVenda> itens;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente id_cliente) {
		this.cliente = id_cliente;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public float getValortotal() {
		return valortotal;
	}

	public void setValortotal(float valortotal) {
		this.valortotal = valortotal;
	}
	
	public List<ItemVenda> getItens() {
		return itens;
	}

	public void addItem (Produto p, int qty){
		if(this.itens==null)this.itens = new LinkedList<ItemVenda>();
		ItemVenda item = new ItemVenda();
		item.setVenda(this);
		item.setProduto(p);
		item.setQuantidade(qty);
		itens.add(item);
		this.valortotal+=p.getPrecounitario()*qty;
	}
	
	public void removeItem (Produto p){
		for(ItemVenda v : this.itens)
			if(v.getProduto() == p)
				this.itens.remove(v);
			
	}
		
}