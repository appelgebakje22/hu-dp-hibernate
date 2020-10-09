package nl.hu.hibernate.dao.impl;

import java.time.LocalDate;
import java.util.List;
import nl.hu.hibernate.dao.ReizigerDAO;
import nl.hu.hibernate.domain.Reiziger;
import org.hibernate.Session;

public class ReizigerDAOImpl extends AbstractDAOImpl<Reiziger> implements ReizigerDAO {

	public ReizigerDAOImpl(Session session) {
		super(Reiziger.class, session);
	}

	@Override
	public List<Reiziger> findByGbdatum(String date) {
		return this.session
				.createQuery("FROM Reiziger WHERE geboortedatum = :date", Reiziger.class)
				.setParameter("date", LocalDate.parse(date))
				.getResultList();
	}
}