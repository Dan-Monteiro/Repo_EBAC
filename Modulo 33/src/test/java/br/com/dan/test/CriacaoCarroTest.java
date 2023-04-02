package br.com.dan.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import br.com.dan.domain.dao.AcessorioDao;
import br.com.dan.domain.dao.CarroDao;
import br.com.dan.domain.dao.IAcessorioDao;
import br.com.dan.domain.dao.ICarroDao;
import br.com.dan.domain.dao.IMarcaDao;
import br.com.dan.domain.dao.MarcaDao;
import br.com.dan.domain.model.Acessorio;
import br.com.dan.domain.model.Carro;
import br.com.dan.domain.model.Marca;

public class CriacaoCarroTest {

	private ICarroDao carroDao;
	private IMarcaDao marcaDao;
	private IAcessorioDao acessorioDao;
	
	public CriacaoCarroTest() {
		this.carroDao = new CarroDao();
		this.marcaDao = new MarcaDao();
		this.acessorioDao = new AcessorioDao();
	}

	@Test
	public void testContextoCadastroCarro() {
		Marca marca = new Marca();
		marca.setNome("Volkswagen");
		
		Marca marcaResultado = this.marcaDao.cadastrar(marca);
		
		Carro carro = new Carro();
		carro.setNome("Fusca");
		carro.setCor("azul");
		carro.setMarca(marcaResultado);
		
		Acessorio acessorio = new Acessorio();
		acessorio.setNome("Direção Elétrica");

		Acessorio acessorio2 = new Acessorio();
		acessorio2.setNome("Ar condicionado");
		
		Acessorio acessorioResultado = this.acessorioDao.cadastrar(acessorio);
		Acessorio acessorioResultado2 = this.acessorioDao.cadastrar(acessorio2);
		
		carro.getAcessorios().add(acessorioResultado);
		carro.getAcessorios().add(acessorioResultado2);
		
		Carro carroResultado = this.carroDao.cadastrar(carro);
		Acessorio acessorioAlt1 = carroResultado.getAcessorios().get(0);
		Acessorio acessorioAlt2 = carroResultado.getAcessorios().get(1);
		
		// Assert
		assertNotNull(marcaResultado);
		assertNotNull(carroResultado);
		assertNotNull(acessorioResultado);
		assertNotNull(acessorioResultado2);
		assertEquals(acessorioAlt1.getNome(), acessorio.getNome());
		assertEquals(acessorioAlt2.getNome(), acessorio2.getNome());
	}
}
