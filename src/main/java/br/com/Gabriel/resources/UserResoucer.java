package br.com.Gabriel.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.Gabriel.entities.User;
import br.com.Gabriel.services.UserService;

//CONTROLADOR REST
@RestController
@RequestMapping(value = "/users") //CONTROLADOR REST QUE RESPONDE NO CAMINHO /USERS
public class UserResoucer {

	@Autowired
	private UserService service;
	
	// /users COM METODO GET RETORNA O FINDALL
	//METODO ENDPOINT PARA ACESSAR OS USUARIOS
	@GetMapping //MOSTRAR QUE ESSE METODO RESPONDE A UMA REQUISIÇÃO TIPO GET 
	public ResponseEntity<List<User>> findAll() {
		// User u = new User(1L, "Gabriel", "991580417", "gabriel@gmail.com", "gabriel123"); instancção manual mocada, nao precida depois que cria o repository
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list); //ok -> retorna resposta com sucesso no HTTP | .body -> corpo da resposta usuario u
	}
	
	@GetMapping(value = "/{id}")  // PARA FALAR QUE A URL TEM UM PARAMETRO que é o id 
	public ResponseEntity<User> findById(@PathVariable Long id)	{ //PARA O SPRING ACEITAR O ID COMO PARAMETRO QUE VAI CHEGAR NA URL
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj); //JOGANDO O OBJ QUE FOI ACHADO COMO RESPOSTA (ok 200)
	}
	
	//INSERIR UM NOVO USUARIO
	@PostMapping                       //CHEGAR NO MODO JSON NA HORA DE FAZER REQUISIÇÃO E ESSE JSON VAI SER DESERIALIZADO PARA UM OBJETO USER
	public ResponseEntity<User> insert(@RequestBody User obj){
		obj = service.insert(obj);
		//CRIANDO UMA RESPOSTA 201 (METODO HTTP QUANDO É CRIADO NOVO OBJ)
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	
	//DELETAR USER     void pois a resposta da requisição não vai retornar nenhum corpo
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	 
	
	
	
	
}
