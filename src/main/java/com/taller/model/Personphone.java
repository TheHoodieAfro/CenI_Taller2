package com.taller.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the personphone database table.
 * 
 */
@Entity
@NamedQuery(name="Personphone.findAll", query="SELECT p FROM Personphone p")
public class Personphone implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PersonphonePK id;

	private Timestamp modifieddate;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="businessentityid")
	private Person person;

	//bi-directional many-to-one association to Phonenumbertype
	@ManyToOne
	@JoinColumn(name="phonenumbertypeid")
	private Phonenumbertype phonenumbertype;

	public Personphone() {
	}

	public PersonphonePK getId() {
		return this.id;
	}

	public void setId(PersonphonePK id) {
		this.id = id;
	}

	public Timestamp getModifieddate() {
		return this.modifieddate;
	}

	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Phonenumbertype getPhonenumbertype() {
		return this.phonenumbertype;
	}

	public void setPhonenumbertype(Phonenumbertype phonenumbertype) {
		this.phonenumbertype = phonenumbertype;
	}

}