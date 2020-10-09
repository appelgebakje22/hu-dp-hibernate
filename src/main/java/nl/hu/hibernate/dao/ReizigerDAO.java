package nl.hu.hibernate.dao;

import java.util.List;
import nl.hu.hibernate.domain.Reiziger;

public interface ReizigerDAO extends IDAO<Reiziger> {

	List<Reiziger> findByGbdatum(String date);
}