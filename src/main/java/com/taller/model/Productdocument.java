package com.taller.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.sql.Timestamp;


/**
 * The persistent class for the productdocument database table.
 * 
 */
@Entity
@NamedQuery(name="Productdocument.findAll", query="SELECT p FROM Productdocument p")
public class Productdocument implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "PRODUCTDOCUMENT_PRODUCTDOCUMENTID_GENERATOR", allocationSize = 1, sequenceName = "PRODUCTDOCUMENT_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCTDOCUMENT_PRODUCTDOCUMENTID_GENERATOR")
	private Integer id;

	private Timestamp modifieddate;

	//bi-directional many-to-one association to Document
	@NotNull
	@ManyToOne
	@JoinColumn(name="documentnode")
	private Document document;

	//bi-directional many-to-one association to Product
	@NotNull
	@ManyToOne
	@JoinColumn(name="productid")
	private Product product;

	public Productdocument() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getModifieddate() {
		return this.modifieddate;
	}

	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}

	public Document getDocument() {
		return this.document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}