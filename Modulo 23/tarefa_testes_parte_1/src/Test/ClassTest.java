package Test;

import main.Application;
import main.Pessoa;
import main.Sexo;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ClassTest {

    @Test
    public void testListReturnWithOnlyFemalePerson() {
        List<Pessoa> listaPessoas = new ArrayList<Pessoa>(){{
            add(new Pessoa("Dan", Sexo.MASCULINO));
            add(new Pessoa("Ana", Sexo.FEMININO));
            add(new Pessoa("Paula", Sexo.FEMININO));
            add(new Pessoa("Pedro", Sexo.MASCULINO));
            add(new Pessoa("Diana", Sexo.FEMININO));
        }};

       List<Pessoa> listaPessoasFemininas = Application.getListaPessoasFemininas(listaPessoas);

        for (Pessoa pessoa: listaPessoasFemininas) {
            Assert.assertEquals( Sexo.FEMININO, pessoa.getSexo());
        }

        Assert.assertEquals(3, listaPessoasFemininas.size());
    }
}