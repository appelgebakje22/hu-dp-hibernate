package nl.hu.hibernate.dao;

import java.util.List;
import nl.hu.hibernate.domain.OvChipkaart;
import nl.hu.hibernate.domain.Reiziger;

public interface OvChipkaartDAO extends IDAO<OvChipkaart> {

	List<OvChipkaart> findByReiziger(Reiziger reiziger);
}