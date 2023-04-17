package br.com.Gabriel.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.Gabriel.entities.Product;
import br.com.Gabriel.services.ProductService;

//CONTROLADOR REST
@RestController
@RequestMapping(value = "/products") //CONTROLADOR REST QUE RESPONDE NO CAMINHO /USERS
public class ProductResoucer {

	@Autowired
	private ProductService service;
	
	//METODO ENDPOINT PARA ACESSAR OS USUARIOS
	@GetMapping //MOSTRAR QUE ESSE METODO RESPONDE A UMA REQUISIÇÃO TIPO GET 
	public ResponseEntity<List<Product>> findAll() {
		// Product u = new Product(1L, "Gabriel", "991580417", "gabriel@gmail.com", "gabriel123"); instancção manual mocada, nao precida depois que cria o repository
		List<Product> list = service.findAll();
		return ResponseEntity.ok().body(list); //ok -> retorna resposta com sucesso no HTTP | .body -> corpo da resposta usuario u
	}
	
	@GetMapping(value = "/{id}")  // PARA FALAR QUE A URL TEM UM PARAMETRO que é o id 
	public ResponseEntity<Product> findById(@PathVariable Long id)	{ //PARA O SPRING ACEITAR O ID COMO PARAMETRO QUE VAI CHEGAR NA URL
		Product obj = service.findById(id);
		return ResponseEntity.ok().body(obj); //JOGANDO O OBJ QUE FOI ACHADO COMO RESPOSTA 
	}
	
	
	
	
	
}
