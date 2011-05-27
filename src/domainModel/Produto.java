package domainModel;

import javax.persistence.*;

@Entity
@Table(name="produtos")
public class Produto {
	
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@OneToMany(fetch=FetchType.LAZY)
	@Id
	private int id;

	@Column
	private String nome;

	@Column
	private float precounitario;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getPrecounitario() {
		return precounitario;
	}

	public void setPrecounitario(float precounitario) {
		this.precounitario = precounitario;
	}
}

