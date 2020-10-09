package nl.hu.hibernate.dao.impl;

import java.util.List;
import nl.hu.hibernate.dao.OvChipkaartDAO;
import nl.hu.hibernate.domain.Adres;
import nl.hu.hibernate.domain.OvChipkaart;
import nl.hu.hibernate.domain.Product;
import nl.hu.hibernate.domain.Reiziger;
import org.hibernate.Session;

public class OvChipkaartDAOImpl extends AbstractDAOImpl<OvChipkaart> implements OvChipkaartDAO {

	public OvChipkaartDAOImpl(Session session) {
		super(OvChipkaart.class, session);
	}

	@Override
	public List<OvChipkaart> findByReiziger(Reiziger reiziger) {
		return this.session
				.createQuery("FROM OvChipkaart WHERE reiziger = :reiziger", OvChipkaart.class)
				.setParameter("reiziger", reiziger)
				.getResultList();
	}

	@Override
	public boolean delete(OvChipkaart ovChipkaart) {
		if (ovChipkaart.getProductList() != null) {
			for (Product product : ovChipkaart.getProductList()) {
				product.removeOvChipkaart(ovChipkaart);
			}
		}
		return super.delete(ovChipkaart);
	}
}