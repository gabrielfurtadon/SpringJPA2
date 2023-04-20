package br.com.Gabriel.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.Gabriel.entities.enums.OrderStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-dd'T'HH:dd:ss'Z'", timezone = "GMT") //GARANTIR QUE A FORMATAÇÃO DA DATA APARECERA CORRETAMENTE
	private Instant moment;
	private Integer orderStatus;
	
	//CRIAR A ASSOCIAÇÃO: MUITOS PEDIDOS PARA UM CLIENTE
	@ManyToOne
	@JoinColumn(name = "client_id") // (NA TABELA PEDIDOS DO BD VAI TER UMA CHAVE ESTRANGEIRA QUE VAI CONTER O ID DO USUARIO ASSOCIADO AO PEDIDO
	private User client;
	
	
	@OneToMany(mappedBy = "id.order") //ORDERITEMPK É UM ATRIBUTO DO ORDERITEM - NO ORDERITEM TEM O ID E NO ID É QUE TEM O PEDIDO 
	private Set<OrderItem> items = new HashSet<>();
	
	//UM PEDIDO TEM UMA PAGAMENTO 
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL) //NO UM PARA UM TEM QUE COLOCAR ESSE CASCADE POIS AS DUAS ENTIDADES VAO TER O MESMO ID. EX PEDIDO ID 5 TEM PAGAMENTO ID 5
	private Payment payment;
	
	
	public Order() {}

	public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
		super();
		this.id = id;
		this.moment = moment;
		setOrderStatus(orderStatus);
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus);
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		if(orderStatus != null) {
		this.orderStatus = orderStatus.getCode();
		}
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public Set<OrderItem> getItems() {
		return items;
	}
	
	
	
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	//METODO QUE RETORNA O VALOR TOTAL DO PEDIDO
	public Double getTotal() {
		double sum = 0;
		for(OrderItem oi : items) {
			sum += oi.getSubTotal();
		}
		return sum;
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
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
	
}
