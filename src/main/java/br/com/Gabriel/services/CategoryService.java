package br.com.Gabriel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.Gabriel.entities.Category;
import br.com.Gabriel.repositories.CategoryRepository;


@Service //REGISTRA COMO SERVICE DO SPRING E ASSIM PODE SER INJETADO AUTOMATICAMENTE COM AUTOWIRED
public class CategoryService {

	//DECLARANDO DEPENDENCIA PARA O USERREPOSITORY
	@Autowired
	private CategoryRepository repository;
	
	public List<Category> findAll() {
		return repository.findAll();
	}
	
	public Category findById(Long id) {
		Optional<Category> obj = repository.findById(id); // optional tira a necessidade de verificar se é nulo 
		return obj.get(); // VAI RETORNAR UM OBJETO DO TIPO USER QUE ESTIVER DENTRO DO OPTIONAL
	}
	
}
