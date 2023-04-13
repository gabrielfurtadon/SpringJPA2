package br.com.Gabriel.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_user") // Colocar um outro nome pois User é palavra reservada do H2
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id // Id = serve para mostrar que o atributo abaixo é chave primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement 
	private Long id;
	private String name;
	private String phone;
	private String email;
	private String password;
	
	//ASSOCIAÇÃO UM CLIENTE TEM VÁRIOS PEDIDOS
	@JsonIgnore // PARA NÃO DAR LOOPING POIS TEM ASSOCIAÇÃO DOS DOIS LADOS( USER E ORDER)
	@OneToMany(mappedBy = "client") // MOSTRANDO QUE ESTA DO OUTRO LADO DA TABELA 
	private List<Order> orders = new ArrayList<>();
	
	
	
	//CONSTRUTORES OBRIGATORIOS POR ESTAR USANDO UM FRAMEWORK
	public User() {}
	
	public User(Long id, String name, String phone, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.password = password;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	//SÓ COLOCA O GET POIS NÃO VAI TROCAR A LISTA HORA NENHUMA, VAI SÓ ACRESCENTAR E REMOVER
		public List<Order> getOrders() {
			return orders;
		}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, name, password, phone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && id == other.id && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && Objects.equals(phone, other.phone);
	}

	
	
	
	
	

}
