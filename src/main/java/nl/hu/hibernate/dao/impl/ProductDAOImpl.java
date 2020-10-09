package nl.hu.hibernate.dao.impl;

import java.util.List;
import nl.hu.hibernate.dao.ProductDAO;
import nl.hu.hibernate.domain.OvChipkaart;
import nl.hu.hibernate.domain.Product;
import org.hibernate.Session;

public class ProductDAOImpl extends AbstractDAOImpl<Product> implements ProductDAO {

	public ProductDAOImpl(Session session) {
		super(Product.class, session);
	}

	@Override
	public List<Product> findByOvChipkaart(OvChipkaart ov) {
		return this.session
				.createQuery("SELECT p FROM Product p JOIN p.ovList o WHERE o = :ov", Product.class)
				.setParameter("ov", ov)
				.getResultList();
	}

	@Override
	public boolean delete(Product product) {
		if (product.getOvList() != null) {
			for (OvChipkaart ov : product.getOvList()) {
				ov.removeProduct(product);
			}
		}
		return super.delete(product);
	}
}