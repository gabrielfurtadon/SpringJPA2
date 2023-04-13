package br.com.Gabriel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.Gabriel.entities.Order;


public interface OrderRepository extends JpaRepository<Order, Long>{

}
