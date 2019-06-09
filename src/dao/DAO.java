package dao;

import java.util.List;

import exception.PersistenceException;

public interface DAO<T> {
	// CRUD
	public T create(T t) throws PersistenceException;

	public List<T> read() throws PersistenceException;

	public T update(T t) throws PersistenceException;

	public void delete(T t) throws PersistenceException;

	// extras
	public T get(T t) throws PersistenceException;

	public List<T> search(T t) throws PersistenceException;
}
