package nl.hu.hibernate.dao.impl;

import java.io.Serializable;
import java.util.List;
import nl.hu.hibernate.dao.IDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;

abstract class AbstractDAOImpl<ENTITY> implements IDAO<ENTITY> {

	private final Class<ENTITY> entityClass;
	protected final Session session;

	public AbstractDAOImpl(Class<ENTITY> entityClass, Session session) {
		this.entityClass = entityClass;
		this.session = session;
	}

	@Override
	public boolean save(ENTITY entity) {
		try {
			this.session.persist(entity);
			return true;
		} catch (Throwable ignored) {
			return false;
		}
	}

	@Override
	public boolean update(ENTITY entity) {
		try {
			this.session.update(entity);
			return true;
		} catch (Throwable ignored) {
			return false;
		}
	}

	@Override
	public ENTITY findById(Serializable id) {
		return this.session.get(entityClass, id);
	}

	@Override
	public List<ENTITY> findAll() {
		Transaction transaction = this.session.beginTransaction();
		List<ENTITY> result = this.session.createQuery("from " + this.entityClass.getSimpleName(), this.entityClass).getResultList();
		transaction.commit();
		return result;
	}

	@Override
	public boolean delete(ENTITY entity) {
		try {
			this.session.delete(entity);
			return true;
		} catch (Throwable ignored) {
			return false;
		}
	}
}