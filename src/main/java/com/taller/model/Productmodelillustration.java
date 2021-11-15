package com.taller.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the productmodelillustration database table.
 * 
 */
@Entity
@NamedQuery(name="Productmodelillustration.findAll", query="SELECT p FROM Productmodelillustration p")
public class Productmodelillustration implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProductmodelillustrationPK id;

	private Timestamp modifieddate;

	//bi-directional many-to-one association to Illustration
	@ManyToOne
	@JoinColumn(name="illustrationid")
	private Illustration illustration;

	//bi-directional many-to-one association to Productmodel
	@ManyToOne
	@JoinColumn(name="productmodelid")
	private Productmodel productmodel;

	public Productmodelillustration() {
	}

	public ProductmodelillustrationPK getId() {
		return this.id;
	}

	public void setId(ProductmodelillustrationPK id) {
		this.id = id;
	}

	public Timestamp getModifieddate() {
		return this.modifieddate;
	}

	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}

	public Illustration getIllustration() {
		return this.illustration;
	}

	public void setIllustration(Illustration illustration) {
		this.illustration = illustration;
	}

	public Productmodel getProductmodel() {
		return this.productmodel;
	}

	public void setProductmodel(Productmodel productmodel) {
		this.productmodel = productmodel;
	}

}