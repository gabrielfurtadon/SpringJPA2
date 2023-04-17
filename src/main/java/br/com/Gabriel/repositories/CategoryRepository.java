package br.com.Gabriel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.Gabriel.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
