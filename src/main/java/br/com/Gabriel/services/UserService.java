package br.com.Gabriel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.Gabriel.entities.User;
import br.com.Gabriel.repositories.UserRepository;

@Service //REGISTRA COMO SERVICE DO SPRING E ASSIM PODE SER INJETADO AUTOMATICAMENTE COM AUTOWIRED
public class UserService {

	//DECLARANDO DEPENDENCIA PARA O USERREPOSITORY
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id); // optional tira a necessidade de verificar se é nulo 
		return obj.get(); // VAI RETORNAR UM OBJETO DO TIPO USER QUE ESTIVER DENTRO DO OPTIONAL
	}
	
}
