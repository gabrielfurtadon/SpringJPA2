package br.com.Gabriel.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;



@Entity
@Table(name = "tb_product")
public class Product implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private Double price;
	private String imgUrl;
	
	//@Transient //IMPEDE QUE O JPA INTERPRETE 
	@ManyToMany                                // DANDO NOME A CHAVE ESTRANGEIRA             //DEFINIR QUAL É A CHAVE ESTRANGEIRA DA OUTRA ENTIDADE
	@JoinTable(name = "tb_product_category", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name="category_id"))        //FALAR QUAL SERA A TABELA E CHAVES ESTRANGEIRAS QUE VAO ASSOCIAR A TABELA PRODUTO COM CATEGORIA 
	private Set<Category> categories = new HashSet<>();     //SET = CONJUNTO -> GARANTIR QUE NAO VAI TER PRODUTO COM A MESMA CATEGORIA MAIS DE UMA VEZ (NAO ADMITIR REPETIÇÕES DO MESMO ITEM)
									// INSTANCIA PARA GARANTIR QUE A COÇÃO COMECE VAZIA E NAO NULA
	
	
	@OneToMany(mappedBy = "id.product") // ID = PRODUCT | .PRODUCT = ORDEMITEMPK (product_id)
	private Set<OrderItem> orderItems = new HashSet<>();
	
	public Product() {}

	public Product(Long id, String name, String description, Double price, String imgUrl) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	

	public Set<Category> getCategories() {
		return categories;
	}

	@JsonIgnore
	public Set<Order> getOrders() {
		Set<Order> set = new HashSet<>();
		for(OrderItem x : orderItems) { //PERCORRENDO A COLEÇÃO E PARA CADA ELEMNTO VAI ADICIONAR O X.GETORDER()
			set.add(x.getOrder());
		}
		return set;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}

	
	
	
	
	
}
