package nl.hu.hibernate;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import nl.hu.hibernate.domain.Adres;
import nl.hu.hibernate.domain.OvChipkaart;
import nl.hu.hibernate.domain.Product;
import nl.hu.hibernate.domain.Reiziger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {

	public static void main(String[] args) {
		SessionFactory session = createSessionFactory();
		assert session != null;
		// Test fetch
		try {
			Session repo = session.getCurrentSession();
			Transaction transaction = repo.beginTransaction();
			List<Reiziger> result = repo.createQuery("FROM Reiziger", Reiziger.class).getResultList();
			System.out.println("Eerste reiziger resultaat achternaam: " + result.get(0).achternaam);
			transaction.commit();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		// Test Insert with dependencies
		try {
			Session repo = session.getCurrentSession();
			Transaction transaction = repo.beginTransaction();
			Reiziger reiziger = new Reiziger(77, "DA", "van den", "Ham", LocalDate.of(1995, Month.DECEMBER, 3));
			Adres adres = new Adres(77, "3437VD", "65", "Ringslangweide", "Nieuwegein", reiziger);
			repo.saveOrUpdate(reiziger);
			repo.saveOrUpdate(adres);
			OvChipkaart ov = new OvChipkaart(77, LocalDate.ofYearDay(2021, 365), (byte) 2, 100.0F, reiziger);
			Product prod = new Product(77, "Studentenreisproduct weekvrij", "Soms", 1.0F);
			ov.setProductList(new ArrayList<>(Collections.singletonList(prod)));
			repo.saveOrUpdate(ov);
			System.out.println("Save succesvol");
			transaction.commit();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		// Test query new inserts and delete them
		try {
			Session repo = session.getCurrentSession();
			Transaction transaction = repo.beginTransaction();
			Reiziger reiziger = repo.get(Reiziger.class, 77);
			Adres adres = repo.createQuery("FROM Adres WHERE reiziger = :Reiziger", Adres.class).setParameter("Reiziger", reiziger).getSingleResult();
			OvChipkaart ov = repo.get(OvChipkaart.class, 77);
			Product product = ov.productList.get(0);
			System.out.println("Fetch succesvol!");
			repo.delete(product);
			repo.delete(ov);
			repo.delete(adres);
			repo.delete(reiziger);
			System.out.println("Delete succesvol!");
			transaction.commit();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected static SessionFactory createSessionFactory() {
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate_real.cfg.xml").build();
		try {
			return new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
			StandardServiceRegistryBuilder.destroy(registry);
			return null;
		}
	}
}
