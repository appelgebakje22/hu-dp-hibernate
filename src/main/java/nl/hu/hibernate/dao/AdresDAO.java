package nl.hu.hibernate.dao;

import nl.hu.hibernate.domain.Adres;
import nl.hu.hibernate.domain.Reiziger;

public interface AdresDAO extends IDAO<Adres> {

	Adres findByReiziger(Reiziger reiziger);
}