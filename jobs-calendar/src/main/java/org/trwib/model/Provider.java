package org.trwib.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the provider database table.
 * 
 */
@Entity
@Table(name = "provider")
public class Provider implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="provider_id")
	private int id;

	@Column(name="name")
	private String name;

	@Column(name="address_1")
	private String address1;

	@Column(name="address_2")
	private String address2;

	@Column(name="city")
	private String city;

	@Column(name="state")
	private String state;

	@Column(name="zip")
	private String zip;

	@Column(name="phone")
	private String phone;

	@Column(name="programs")
	private String programs;

	@Column(name="emp_support")
	private boolean empSupport;

	@Column(name="job_retention")
	private boolean jobRetention;

	@Column(name="job_training")
	private boolean jobTraining;

	@Column(name="pre_employment")
	private boolean preEmployment;

	@Column(name="support_services")
	private boolean supportServices;


	public Provider() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public boolean getEmpSupport() {
		return this.empSupport;
	}

	public void setEmpSupport(boolean empSupport) {
		this.empSupport = empSupport;
	}

	public boolean getJobRetention() {
		return this.jobRetention;
	}

	public void setJobRetention(boolean jobRetention) {
		this.jobRetention = jobRetention;
	}

	public boolean getJobTraining() {
		return this.jobTraining;
	}

	public void setJobTraining(boolean jobTraining) {
		this.jobTraining = jobTraining;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean getPreEmployment() {
		return this.preEmployment;
	}

	public void setPreEmployment(boolean preEmployment) {
		this.preEmployment = preEmployment;
	}

	public String getPrograms() {
		return this.programs;
	}

	public void setPrograms(String programs) {
		this.programs = programs;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean getSupportServices() {
		return this.supportServices;
	}

	public void setSupportServices(boolean supportServices) {
		this.supportServices = supportServices;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

}