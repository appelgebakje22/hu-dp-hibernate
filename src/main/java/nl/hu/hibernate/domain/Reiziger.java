package nl.hu.hibernate.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "reiziger")
public class Reiziger {

	@Id
	@Column(name = "reiziger_id")
	private int reizigerId;

	@Column(name = "voorletters")
	private String voorletters;

	@Column(name = "tussenvoegsel")
	private String tussenvoegsel;

	@Column(name = "achternaam")
	private String achternaam;

	@Column(name = "geboortedatum")
	private LocalDate geboortedatum;

	public Reiziger(int reizigerId, String voorletters, String tussenvoegsel, String achternaam, LocalDate geboortedatum) {
		this.reizigerId = reizigerId;
		this.voorletters = voorletters;
		this.tussenvoegsel = tussenvoegsel;
		this.achternaam = achternaam;
		this.geboortedatum = geboortedatum;
	}

	public Reiziger() {
		//For hibernate
	}

	public int getReizigerId() {
		return reizigerId;
	}

	public void setReizigerId(int reizigerId) {
		this.reizigerId = reizigerId;
	}

	public String getVoorletters() {
		return voorletters;
	}

	public void setVoorletters(String voorletters) {
		this.voorletters = voorletters;
	}

	public String getTussenvoegsel() {
		return tussenvoegsel;
	}

	public void setTussenvoegsel(String tussenvoegsel) {
		this.tussenvoegsel = tussenvoegsel;
	}

	public String getAchternaam() {
		return achternaam;
	}

	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}

	public LocalDate getGeboortedatum() {
		return geboortedatum;
	}

	public void setGeboortedatum(LocalDate geboortedatum) {
		this.geboortedatum = geboortedatum;
	}
}