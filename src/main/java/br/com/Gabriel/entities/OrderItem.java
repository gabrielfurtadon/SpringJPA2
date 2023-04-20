package br.com.Gabriel.entities;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.Gabriel.entities.pk.OrdemItemPk;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	
	@EmbeddedId
	private OrdemItemPk id = new OrdemItemPk(); // ID = PEDIDO E PRODUTO (TEM QUE INSTANCIAR SEMPRE QUE FOI CLASSE AUXILIAR QUE É ID COMPOSTO
	
	private int quantity;
	private double price;
	
	public OrderItem() {}
	
	public OrderItem(Order order, Product product, int quantity, double price) {
		super();
		id.setOrder(order);
		id.setProduct(product);
		this.quantity = quantity;
		this.price = price;
	}
	
	@JsonIgnore
	public Order getOrder() {
		return id.getOrder();
	}

	//INFORMA UM PEDIDO NO PARAMETRO E O METODO VAI NO ID (ORDERITEMPK E JOGA O PEDIDO) 
	public void setOrder(Order order) {
		id.setOrder(order);
	}
	
	
	public Product getProduct() {
		return id.getProduct();
	}
	
	public void setProduct(Product product) {
		id.setProduct(product);
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	//METODO QUE DA O SUBTOTAL DO PRODUTO (preço x quantidade)
	// NA PLATAFORMA JAVA ENTERPRISE O QUE VALE É O GET (POR ISSO COLOCAR GETSUBTOTAL)
	public Double getSubTotal() {
		return price * quantity;
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
		OrderItem other = (OrderItem) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
	
}
