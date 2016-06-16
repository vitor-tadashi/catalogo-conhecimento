package br.com.resource.catalogoconhecimento.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface GenericDAO<T, K extends Serializable> {

	Class<T> getEntityClass();

	T findById(final K id);

	List<T> findAll();

	List<T> findAll(String propertyOrder, Boolean isDesc);

	List<T> findByExample(final T exampleInstance);

	List<T> findByNamedQuery(final String queryName, Object... params);

	List<T> findByNamedQueryAndNamedParams(final String queryName, final Map<String, ? extends Object> params);

	int countAll();

	int countByExample(final T exampleInstance);

	T save(final T entity);

	void delete(final T entity);

	public T update(T entity);

}