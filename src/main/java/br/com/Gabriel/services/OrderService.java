package br.com.Gabriel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.Gabriel.entities.Order;
import br.com.Gabriel.repositories.OrderRepository;

@Service //REGISTRA COMO SERVICE DO SPRING E ASSIM PODE SER INJETADO AUTOMATICAMENTE COM AUTOWIRED
public class OrderService {

	//DECLARANDO DEPENDENCIA PARA O OrderREPOSITORY
	@Autowired
	private OrderRepository repository;
	
	public List<Order> findAll() {
		return repository.findAll();
	}
	
	public Order findById(Long id) {
		Optional<Order> obj = repository.findById(id); // optional tira a necessidade de verificar se é nulo 
		return obj.get(); // VAI RETORNAR UM OBJETO DO TIPO Order QUE ESTIVER DENTRO DO OPTIONAL
	}
	
}
