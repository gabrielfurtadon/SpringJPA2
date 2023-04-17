package br.com.Gabriel.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.Gabriel.entities.Category;
import br.com.Gabriel.entities.Order;
import br.com.Gabriel.entities.User;
import br.com.Gabriel.entities.enums.OrderStatus;
import br.com.Gabriel.repositories.CategoryRepository;
import br.com.Gabriel.repositories.OrderRepository;
import br.com.Gabriel.repositories.UserRepository;

@Configuration // MOSTAR QUE É UMA CLASSE DE CONFIGURAÇÃO
@Profile("test") // TEM QUE SER IGUAL AO PROFILE DO APLICATION PROPERTIES ,RODA SOMENTE NO PERFIL DE TESTE
public class TestConfig implements CommandLineRunner{

	//DATABASE SEEDING -> POPULAÇÃO DO BANCO
	
	@Autowired //COM ISSO O SPRING VE A DEPENDENCIA E ASSOCIAR UMA INSTANCIA DE USERREPOSITRY AQUI DENTRO
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public void run(String... args) throws Exception { // TUDO QUE TIVER DENTRO DO RUN VAI SER EXECUTADO QUANDO A APP FOR INICIADA
		
		//PODE COLOCAR ANTES POIS ELA É UMA CLASSE INDEPENDENTE DA USER E ORDER
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		
		User u1 = new User(null, "Maria Brown","988888888", "maria@gmail.com", "123456"); // id null pois vai gerar no banco
		User u2 = new User(null, "Alex Green","977777777", "alex@gmail.com", "123456");
		
		Order o1 = new Order(null, Instant.parse("2023-04-20T19:53:07Z"), OrderStatus.PAID, u1); //PASSANDO O USUARIO PARA FAZER A ASSOCIAÇÃO DESSE OBJ
		Order o2 = new Order(null, Instant.parse("2023-04-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2); //O HORARIO VAI ESTAR 3 HORAS ANTES NO BR
		Order o3 = new Order(null, Instant.parse("2023-04-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT,u1);
		
		//SALVAR NO BANCO como lista (array)
		userRepository.saveAll(Arrays.asList(u1, u2));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		
		
	}
	
}
