package br.com.dan;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	ClienteDAOTest.class,
	ProdutoDAOTest.class,
	VendaDAOTest.class 
})
public class RunAllTest {

}