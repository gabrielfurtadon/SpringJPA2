package br.com.Gabriel.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.Gabriel.entities.User;

@RestController
@RequestMapping(value = "/users") //CONTROLADOR REST QUE RESPONDE NO CAMINHO /USERS
public class UserResoucer {

	//METODO ENDPOINT PARA ACESSAR OS USUARIOS
	@GetMapping //MOSTRAR QUE ESSE METODO RESPONDE A UMA REQUISIÇÃO TIPO GET 
	public ResponseEntity<User> findAll() {
		User u = new User(1L, "Gabriel", "991580417", "gabriel@gmail.com", "gabriel123");
		return ResponseEntity.ok().body(u); //ok -> retorna resposta com sucesso no HTTP | .body -> corpo da resposta usuario u
	}
	
}
