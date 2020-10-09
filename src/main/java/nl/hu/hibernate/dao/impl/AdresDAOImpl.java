package nl.hu.hibernate.dao.impl;

import nl.hu.hibernate.dao.AdresDAO;
import nl.hu.hibernate.domain.Adres;
import nl.hu.hibernate.domain.Reiziger;
import org.hibernate.Session;

public class AdresDAOImpl extends AbstractDAOImpl<Adres> implements AdresDAO {

	public AdresDAOImpl(Session session) {
		super(Adres.class, session);
	}

	@Override
	public Adres findByReiziger(Reiziger reiziger) {
		return this.session
				.createQuery("FROM Adres WHERE reiziger = :reiziger", Adres.class)
				.setParameter("reiziger", reiziger)
				.getSingleResult();
	}
}