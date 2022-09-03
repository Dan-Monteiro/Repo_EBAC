package test.br.com.dan;

import org.junit.Assert;
import org.junit.Test;

import br.com.dan.TesteCliente;

public class TesteClienteTest {

	@Test
	public void testeClasseCliente() {
		TesteCliente cli = new TesteCliente();
		cli.adicionarNome("Dan");
		cli.adicionarNome1("Dan");

		Assert.assertEquals("Dan", cli.getNome());
	}
}
