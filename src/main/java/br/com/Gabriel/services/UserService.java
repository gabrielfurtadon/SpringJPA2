package br.com.Gabriel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.Gabriel.entities.User;
import br.com.Gabriel.repositories.UserRepository;
import br.com.Gabriel.services.exceptions.DatabaseException;
import br.com.Gabriel.services.exceptions.ResourceNotFoundExceptions;
import jakarta.persistence.EntityNotFoundException;

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
		//return obj.get(); // VAI RETORNAR UM OBJETO DO TIPO USER QUE ESTIVER DENTRO DO OPTIONAL (VAI RETORNAR UMA EXESSÃO SE NAO TIVER O OBJ NO OPTIONAL)
		return obj.orElseThrow(() -> new ResourceNotFoundExceptions(id));   // orElseThrow -> VAI TENTAR DAR O GET, SE NAO DER VAI LANÇAR A EXCESSÃO
	}
	
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		try {
		repository.deleteById(id);
		}catch(EmptyResultDataAccessException e) { 
			throw new ResourceNotFoundExceptions(id);
		}catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public User update(Long id, User obj) {
		try {
		User entity = repository.getReferenceById(id); //INSTANCIAR O USUARIO PELO JPA, SEM IR NO BANCO AINDA
		updateData(entity, obj);
		return repository.save(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundExceptions(id);
		}
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
		
	}
	
}
