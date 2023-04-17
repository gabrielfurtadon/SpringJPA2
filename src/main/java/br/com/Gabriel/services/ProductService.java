package br.com.Gabriel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.Gabriel.entities.Product;
import br.com.Gabriel.repositories.ProductRepository;

@Service //REGISTRA COMO SERVICE DO SPRING E ASSIM PODE SER INJETADO AUTOMATICAMENTE COM AUTOWIRED
public class ProductService {

	//DECLARANDO DEPENDENCIA PARA O USERREPOSITORY
	@Autowired
	private ProductRepository repository;
	
	public List<Product> findAll() {
		return repository.findAll();
	}
	
	public Product findById(Long id) {
		Optional<Product> obj = repository.findById(id); // optional tira a necessidade de verificar se é nulo 
		return obj.get(); // VAI RETORNAR UM OBJETO DO TIPO USER QUE ESTIVER DENTRO DO OPTIONAL
	}
	
}
