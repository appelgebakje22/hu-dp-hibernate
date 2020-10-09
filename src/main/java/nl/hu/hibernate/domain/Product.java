package nl.hu.hibernate.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@Column(name = "product_nummer")
	private int productNummer;

	@Column(name = "naam")
	private String naam;

	@Column(name = "beschrijving")
	private String beschrijving;

	@Column(name = "prijs")
	private float prijs;

	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name = "ov_chipkaart_product", joinColumns = @JoinColumn(name = "product_nummer"), inverseJoinColumns = @JoinColumn(name = "kaart_nummer"))
	private List<OvChipkaart> ovList;

	public Product(int productNummer, String naam, String beschrijving, float prijs) {
		this.productNummer = productNummer;
		this.naam = naam;
		this.beschrijving = beschrijving;
		this.prijs = prijs;
	}

	public Product() {
		//For hibernate
	}

	public int getProductNummer() {
		return productNummer;
	}

	public void setProductNummer(int productNummer) {
		this.productNummer = productNummer;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getBeschrijving() {
		return beschrijving;
	}

	public void setBeschrijving(String beschrijving) {
		this.beschrijving = beschrijving;
	}

	public float getPrijs() {
		return prijs;
	}

	public void setPrijs(float prijs) {
		this.prijs = prijs;
	}

	public List<OvChipkaart> getOvList() {
		return ovList;
	}

	public void setOvList(List<OvChipkaart> ovList) {
		this.ovList = ovList;
	}

	public void addOvChipkaart(OvChipkaart ov) {
		if (this.ovList == null) {
			this.ovList = new ArrayList<>();
		}
		this.ovList.add(ov);
	}

	public void removeOvChipkaart(OvChipkaart ov) {
		if (this.ovList != null) {
			this.ovList.remove(ov);
		}
	}
}