package br.com.Gabriel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.Gabriel.entities.OrderItem;


public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
