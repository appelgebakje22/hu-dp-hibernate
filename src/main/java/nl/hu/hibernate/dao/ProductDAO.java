package nl.hu.hibernate.dao;

import java.util.List;
import nl.hu.hibernate.domain.OvChipkaart;
import nl.hu.hibernate.domain.Product;

public interface ProductDAO extends IDAO<Product> {

	List<Product> findByOvChipkaart(OvChipkaart ov);
}