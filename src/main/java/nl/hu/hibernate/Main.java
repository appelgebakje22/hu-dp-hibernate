package nl.hu.hibernate;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import nl.hu.hibernate.dao.AdresDAO;
import nl.hu.hibernate.dao.OvChipkaartDAO;
import nl.hu.hibernate.dao.ProductDAO;
import nl.hu.hibernate.dao.ReizigerDAO;
import nl.hu.hibernate.dao.impl.AdresDAOImpl;
import nl.hu.hibernate.dao.impl.OvChipkaartDAOImpl;
import nl.hu.hibernate.dao.impl.ProductDAOImpl;
import nl.hu.hibernate.dao.impl.ReizigerDAOImpl;
import nl.hu.hibernate.domain.Adres;
import nl.hu.hibernate.domain.OvChipkaart;
import nl.hu.hibernate.domain.Product;
import nl.hu.hibernate.domain.Reiziger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {

	protected static SessionFactory createSessionFactory() {
		return new Configuration().configure("hibernate_real.cfg.xml").buildSessionFactory();
	}

	public static void main(String[] args) {
		Session session = createSessionFactory().openSession();
		ReizigerDAO rdao = new ReizigerDAOImpl(session);
		AdresDAO adao = new AdresDAOImpl(session);
		OvChipkaartDAO odao = new OvChipkaartDAOImpl(session);
		ProductDAO pdao = new ProductDAOImpl(session);

		try {
			testReizigerDAO(rdao);
			testAdresDAO(adao, rdao);
			testOvChipkaartDAO(rdao, odao);
			testProductDAO(pdao, odao, rdao);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		session.close();
	}

	private static void testReizigerDAO(ReizigerDAO rdao) throws SQLException {
		System.out.println("\n---------- Test ReizigerDAO -------------");

		// Haal alle reizigers op uit de database
		List<Reiziger> reizigers = rdao.findAll();
		System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
		for (Reiziger r : reizigers) {
			System.out.println("    " + r);
		}

		// Maak een nieuwe reiziger aan en persisteer deze in de database
		String gbdatum = "1981-03-14";
		Reiziger sietske = new Reiziger(77, "S", "", "Boers", LocalDate.parse(gbdatum));
		System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save(): ");
		rdao.save(sietske);
		reizigers = rdao.findAll();
		System.out.println(reizigers.size() + " reizigers");

		// Vind een specifieke reiziger ahv. een id
		Reiziger reiziger = rdao.findById(77);
		System.out.println("[Test] De gevonden reiziger na ReizigerDAO.findById is: " + reiziger);

		// Vind alle reizigers met een specifieke geboortedatum
		List<Reiziger> reizigersGbd = rdao.findByGbdatum(gbdatum);
		System.out.print("[Test] ReizigerDAO.findByGbdatum geeft de volgende reiziger(s):");
		for (Reiziger r : reizigersGbd) {
			System.out.println(r);
		}

		// Update een bestaande reiziger
		Reiziger sietskeUPDATE = new Reiziger(77, "S", "", "Boers-De Jonge", LocalDate.parse(gbdatum));
		System.out.print("[Test] Eerst " + rdao.findById(77) + ", na ReizigerDAO.update(): ");
		rdao.update(sietskeUPDATE);
		System.out.println(rdao.findById(77));


		// Verwijder een bestaande reiziger
		System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.delete(): ");
		rdao.delete(sietskeUPDATE);
		reizigers = rdao.findAll();
		System.out.println(reizigers.size() + " reizigers");
	}

	private static void testAdresDAO(AdresDAO adao, ReizigerDAO rdao) throws SQLException {
		System.out.println("\n---------- Test AdresDAO -------------");

		// Haal alle adressen op uit de database
		List<Adres> adressen = adao.findAll();
		System.out.println("[Test] AdresDAO.findAll() geeft de volgende adressen:");
		for (Adres r : adressen) {
			System.out.println("    " + r);
		}

		// Maak een nieuw adres aan
		Reiziger sietske = new Reiziger(77, "S", "", "Boers", LocalDate.parse("1981-03-14"));
		rdao.save(sietske);

		System.out.print("[Test] Eerst " + adressen.size() + " adressen, na AdresDAO.save(): ");

		Adres newAdres = new Adres(77, "1111AB", "11", "Proefplein", "Testdorp", sietske);
		adao.save(newAdres);
		adressen = adao.findAll();
		System.out.println(adressen.size() + " adressen");

		// Vind een specifiek adres ahv een ID
		Adres foundAdres = adao.findById(77);
		System.out.println("[Test] Het gevonden adres bij id 77 is: " + foundAdres);

		// Vind een specifiek adres ahv een Reiziger
		foundAdres = adao.findByReiziger(sietske);
		System.out.println("[Test] Het gevonden adres bij een specifieke reiziger is: " + foundAdres);

		// Update een adres
		Adres adresUPDATE = new Adres(77, "2222CD", "22", "Trachtstraat", "Testdorp", sietske);

		System.out.print("[Test] Eerst: " + foundAdres + ", na AdresDAO.update(): ");
		adao.update(adresUPDATE);
		System.out.println(adao.findByReiziger(sietske));

		// Verwijder een adres
		System.out.print("[Test] Eerst " + adressen.size() + " adressen, na AdresDAO.delete(): ");
		adao.delete(adresUPDATE);
		adressen = adao.findAll();
		System.out.println(adressen.size() + " adressen");

		rdao.delete(sietske);
	}

	private static void testOvChipkaartDAO(ReizigerDAO rdao, OvChipkaartDAO odao) throws SQLException {
		System.out.println("\n---------- Test OvChipkaartDAO -------------");

		// Haal alle OvChipkaarten op uit de database
		System.out.println("[Test] OvChipkaartDAO.findAll() geeft de volgende OvChipkaarten:");
		List<OvChipkaart> OvChipkaarten = odao.findAll();
		for (OvChipkaart ovc : OvChipkaarten) {
			System.out.println("    " + ovc);
		}

		//Maak een nieuwe OvChipkaart aan
		Reiziger sietske = new Reiziger(77, "S", "", "Boers", LocalDate.parse("1981-03-14"));
		rdao.save(sietske);

		System.out.println("[Test] Eerst " + OvChipkaarten.size() + " OvChipkaarten, na OvChipkaartDAO.save(): ");
		OvChipkaart OvChipkaart = new OvChipkaart(77, LocalDate.parse("2020-02-20"), (byte) 2, 10.5F, sietske);

		odao.save(OvChipkaart);
		OvChipkaarten = odao.findAll();
		System.out.println("    " + OvChipkaarten.size() + " OvChipkaarten");

		// Vind een specifieke OvChipkaart ahv een ID
		OvChipkaart foundOvChipkaart = odao.findById(77);
		System.out.println("[Test] Het gevonden adres bij id 77 is: " + foundOvChipkaart);

		// Vind een specifieke OvChipkaart ahv een Reiziger
		List<OvChipkaart> foundOvChipkaartList = odao.findByReiziger(sietske);
		System.out.println("[Test] De gevonden OvChipkaart bij een specifieke reiziger is: " + foundOvChipkaartList);

		//Voeg een tweede OvChipkaart toe aan een Reiziger
		OvChipkaart secondOvChipkaart = new OvChipkaart(78, LocalDate.parse("2022-04-22"), (byte) 2, 5.0F, sietske);

		odao.save(secondOvChipkaart);
		foundOvChipkaartList = odao.findByReiziger(sietske);
		System.out.println("[Test] Na het toevoegen van een tweede OvChipkaart: " + foundOvChipkaartList);

		// Update een OvChipkaart
		OvChipkaart OvChipkaartUPDATE = new OvChipkaart(77, LocalDate.parse("2021-03-30"), (byte) 1, 15, sietske);

		System.out.println("[Test] Eerst: " + foundOvChipkaart + ", na AdresDAO.update(): ");
		odao.update(OvChipkaartUPDATE);
		System.out.println("    " + odao.findById(77));

		// Verwijder een OvChipkaart
		System.out.print("[Test] Eerst " + odao.findAll().size() + " OvChipkaarten, na OvChipkaartDAO.delete(): ");
		odao.delete(OvChipkaartUPDATE);
		System.out.println(odao.findAll().size() + " OvChipkaarten");

		odao.delete(secondOvChipkaart);
		rdao.delete(sietske);
	}

	private static void testProductDAO(ProductDAO pdao, OvChipkaartDAO odao, ReizigerDAO rdao) throws SQLException {
		System.out.println("\n---------- Test ProductDAO -------------");

		// Haal alle Producten op uit de database
		System.out.println("[Test] ProductDAO.findAll() geeft de volgende producten:");
		List<Product> producten = pdao.findAll();
		for (Product p : producten) {
			System.out.println("    " + p);
		}

		//Maak een nieuw Product aan
		System.out.println("[Test] Eerst " + producten.size() + " producten, na ProductDAO.save(): ");
		Product product = new Product(77, "Proef Rit", "Reis naar de rand van het universum", 12.34F);

		pdao.save(product);
		producten = pdao.findAll();
		System.out.println("    " + producten.size() + " producten");

		// Vind een specifieke Product ahv een ID
		Product foundProduct = pdao.findById(77);
		System.out.println("[Test] Het gevonden product bij id 77 is: " + foundProduct);

		// Vind een specifiek product ahv een OvChipkaart
		List<Product> foundProductList = pdao.findByOvChipkaart(odao.findById(35283));
		System.out.println("[Test] Het gevonden product bij een specifieke OvChipkaart is: " + foundProduct);

		//Voeg een Product toe aan een OvChipkaart
		Reiziger sietske = new Reiziger(77, "S", "", "Boers", LocalDate.parse("1981-03-14"));
		rdao.save(sietske);
		OvChipkaart OvChipkaart = new OvChipkaart(77, LocalDate.parse("2022-04-22"), (byte) 2, 5.0F, sietske);
		odao.save(OvChipkaart);

		System.out.println("[Test] Het aantal producten op de OvChipkaart voor ProductDAO.save(): " + (OvChipkaart.getProductList() == null ? 0 : OvChipkaart.getProductList().size()));
		OvChipkaart.addProduct(product);
		pdao.update(product);
		System.out.println("Na ProductDAO.save(): " + OvChipkaart.getProductList().size());


		// Update een Product
		Product ProductUPDATE = new Product(77, "Test Track", "Reis naar de rand van het universum en terug", 56.78F);

		System.out.println("[Test] Eerst: " + product + ", na ProductDAO.update(): ");
		pdao.update(ProductUPDATE);
		System.out.println("    " + pdao.findById(77));

		// Verwijder een OvChipkaart
		System.out.print("[Test] Eerst " + pdao.findAll().size() + " producten, na ProductDAO.delete(): ");
		pdao.delete(ProductUPDATE);
		System.out.println(pdao.findAll().size() + " producten");

		odao.delete(OvChipkaart);
		rdao.delete(sietske);
	}
}