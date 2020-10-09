package nl.hu.hibernate.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "adres")
public class Adres {

	@Id
	@Column(name = "adres_id")
	private int adresId;

	@Column(name = "postcode")
	private String postcode;

	@Column(name = "huisnummer")
	private String huisnummer;

	@Column(name = "straat")
	private String straat;

	@Column(name = "woonplaats")
	private String woonplaats;

	@OneToOne
	@JoinColumn(name = "reiziger_id")
	private Reiziger reiziger;

	public Adres(int adresId, String postcode, String huisnummer, String straat, String woonplaats, Reiziger reiziger) {
		this.adresId = adresId;
		this.postcode = postcode;
		this.huisnummer = huisnummer;
		this.straat = straat;
		this.woonplaats = woonplaats;
		this.reiziger = reiziger;
	}

	public Adres() {
		//For hibernate
	}

	public int getAdresId() {
		return adresId;
	}

	public void setAdresId(int adresId) {
		this.adresId = adresId;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getHuisnummer() {
		return huisnummer;
	}

	public void setHuisnummer(String huisnummer) {
		this.huisnummer = huisnummer;
	}

	public String getStraat() {
		return straat;
	}

	public void setStraat(String straat) {
		this.straat = straat;
	}

	public String getWoonplaats() {
		return woonplaats;
	}

	public void setWoonplaats(String woonplaats) {
		this.woonplaats = woonplaats;
	}

	public Reiziger getReiziger() {
		return reiziger;
	}

	public void setReiziger(Reiziger reiziger) {
		this.reiziger = reiziger;
	}
}