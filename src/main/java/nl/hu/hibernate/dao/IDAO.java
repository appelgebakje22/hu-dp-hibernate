package nl.hu.hibernate.dao;

import java.io.Serializable;
import java.util.List;

public interface IDAO<T> {

	boolean save(T entity);

	boolean update(T entity);

	T findById(Serializable id);

	List<T> findAll();

	boolean delete(T entity);
}