package br.com.Gabriel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.Gabriel.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
