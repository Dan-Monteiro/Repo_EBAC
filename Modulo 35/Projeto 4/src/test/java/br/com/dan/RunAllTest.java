package br.com.dan;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author rodrigo.pires
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	ClienteDAOTest.class,
	ProdutoDAOTest.class,
	VendaDAOTest.class 
})
public class RunAllTest {

}