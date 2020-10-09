package nl.hu.hibernate.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ov_chipkaart")
public class OvChipkaart {

	@Id
	@Column(name = "kaart_nummer")
	private int kaartNummer;

	@Column(name = "geldig_tot")
	private LocalDate geldigTot;

	@Column(name = "klasse")
	private byte klasse;

	@Column(name = "saldo")
	private float saldo;

	@ManyToOne
	@JoinColumn(name = "reiziger_id")
	private Reiziger reiziger;

	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name = "ov_chipkaart_product", joinColumns = @JoinColumn(name = "kaart_nummer"), inverseJoinColumns = @JoinColumn(name = "product_nummer"))
	private List<Product> productList;

	public OvChipkaart(int kaartNummer, LocalDate geldigTot, byte klasse, float saldo, Reiziger reiziger) {
		this.kaartNummer = kaartNummer;
		this.geldigTot = geldigTot;
		this.klasse = klasse;
		this.saldo = saldo;
		this.reiziger = reiziger;
	}

	public OvChipkaart() {
		//For hibernate
	}

	public int getKaartNummer() {
		return kaartNummer;
	}

	public void setKaartNummer(int kaartNummer) {
		this.kaartNummer = kaartNummer;
	}

	public LocalDate getGeldigTot() {
		return geldigTot;
	}

	public void setGeldigTot(LocalDate geldigTot) {
		this.geldigTot = geldigTot;
	}

	public byte getKlasse() {
		return klasse;
	}

	public void setKlasse(byte klasse) {
		this.klasse = klasse;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public Reiziger getReiziger() {
		return reiziger;
	}

	public void setReiziger(Reiziger reiziger) {
		this.reiziger = reiziger;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public void addProduct(Product product) {
		if (this.productList == null) {
			this.productList = new ArrayList<>();
		}
		this.productList.add(product);
	}

	public void removeProduct(Product product) {
		if (this.productList != null) {
			this.productList.remove(product);
		}
	}
}