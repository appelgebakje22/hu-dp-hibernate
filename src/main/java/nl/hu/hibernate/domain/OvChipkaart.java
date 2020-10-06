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
import java.util.List;

@Entity
@Table(name = "ov_chipkaart")
public class OvChipkaart {

	@Id
	@Column(name = "kaart_nummer")
	public int kaartNummer;

	@Column(name = "geldig_tot")
	public LocalDate geldigTot;

	@Column(name = "klasse")
	public byte klasse;

	@Column(name = "saldo")
	public float saldo;

	@ManyToOne
	@JoinColumn(name = "reiziger_id")
	public Reiziger reiziger;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "ov_chipkaart_product", joinColumns = @JoinColumn(name = "kaart_nummer"), inverseJoinColumns = @JoinColumn(name = "product_nummer"))
	public List<Product> productList;

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
}