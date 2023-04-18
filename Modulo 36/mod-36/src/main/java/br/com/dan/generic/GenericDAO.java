package br.com.dan.generic;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.dan.Exceptions.DAOException;
import br.com.dan.Exceptions.MaisDeUmRegistroException;
import br.com.dan.Exceptions.TableException;
import br.com.dan.Exceptions.TipoChaveNaoEncontradaException;
import br.com.dan.dao.Persistente;

public class GenericDAO <T extends Persistente, E extends Serializable> implements IGenericDAO <T,E> {

	private static final String PERSISTENCE_UNIT_NAME = "ExemploJPA";

	public static final String PRODUCTION = "PROD";

	public static final String TEST = "TEST";

	protected EntityManagerFactory entityManagerFactory;
	
	protected EntityManager entityManager;
	
	private Class<T> persistenteClass;

	private String persistenceUnitName;

	public GenericDAO(Class<T> persistenteClass) {
		this.persistenteClass = persistenteClass;
	}

	public GenericDAO(Class<T> persistenteClass, String persistenceUnitName) {
		this.persistenteClass = persistenteClass;
		this.persistenceUnitName = persistenceUnitName;
	}
	
	@Override
	public T cadastrar(T entity) throws TipoChaveNaoEncontradaException, DAOException {
		openConnection();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
		closeConnection();
		return entity;
	}

	@Override
	public void excluir(T entity) throws DAOException {
		openConnection();
		entity = entityManager.merge(entity);
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
		closeConnection();
	}

	@Override
	public T alterar(T entity) throws TipoChaveNaoEncontradaException, DAOException {
		openConnection();
		entity = entityManager.merge(entity);
		entityManager.getTransaction().commit();
		closeConnection();
		return entity;
	}

	@Override
	public T consultar(E valor) throws MaisDeUmRegistroException, TableException, DAOException {
		openConnection();
		T entity = entityManager.find(this.persistenteClass, valor);
		entityManager.getTransaction().commit();
		closeConnection();
		return entity;
	}

	@Override
	public Collection<T> buscarTodos() throws DAOException {
		openConnection();
		List<T> list = 
				entityManager.createQuery(getSelectSql(), this.persistenteClass).getResultList();
		closeConnection();
		return list;
	}
	
	protected void openConnection() {
		entityManagerFactory = 
				Persistence.createEntityManagerFactory(getPersistenceUnitName());
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
	}
	
	protected void closeConnection() {
		entityManager.close();
		entityManagerFactory.close();
	}
	
	private String getSelectSql() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT obj FROM ");
		sb.append(this.persistenteClass.getSimpleName());
		sb.append(" obj");
		return sb.toString();
	}

	private String getPersistenceUnitName() {
		if (this.persistenceUnitName != null) {
			return this.persistenceUnitName;
		}
		return PERSISTENCE_UNIT_NAME;
	}

	public String getScopeDB() {
		if (Objects.equals(this.persistenceUnitName, PERSISTENCE_UNIT_NAME)) {
			return PRODUCTION;
		}
		return TEST;
	}

}