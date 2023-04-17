package br.com.dan.SpringBootPrimeiroExemplo;

import br.com.dan.domain.Cliente;
import br.com.dan.repository.IClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@EnableJpaRepositories(basePackages = "br.com.dan.repository")
@EntityScan("br.com.dan.*")
@ComponentScan(basePackages = "br.com.dan")
public class SpringBootPrimeiroExemploApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(SpringBootPrimeiroExemploApplication.class);

	@Autowired
	private IClienteRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootPrimeiroExemploApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("StartApplication...");
		Cliente cliente = createCliente();
		repository.save(cliente);
	}

	private Cliente createCliente() {
		return Cliente.builder()
				.cidade("Recife")
				.cpf(12312312310L)
				.email("teste@teste.com.br")
				.end("End")
				.estado("PE")
				.nome("Teste Spring Boot")
				.numero(102)
				.tel(10203040L)
				.build();
	}
}