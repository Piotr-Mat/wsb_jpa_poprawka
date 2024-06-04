package com.capgemini.wsb.persistence.entity;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "PATIENT")
public class PatientEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false, unique = true)
	private Long id;

	@Column(nullable = false, length = 50)
	private String firstName;

	@Column(nullable = false, length = 50)
	private String lastName;

	@Column(nullable = false, length = 50, unique = true)
	private String telephoneNumber;

	@Column(unique = true, length = 100)
	private String email;

	@Column(nullable = false, unique = true, length = 50)
	private String patientNumber;

	@Column(nullable = false, length = 10)
	private LocalDate dateOfBirth;

	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Collection<VisitEntity> visits;

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinTable(
			name = "PATIENT_TO_ADDRESS",
			joinColumns = @JoinColumn(name = "PATIENT_ID"),
			inverseJoinColumns = @JoinColumn(name = "ADDRESS_ID")
	)
	private Collection<AddressEntity> addresses;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPatientNumber() {
		return patientNumber;
	}

	public void setPatientNumber(String patientNumber) {
		this.patientNumber = patientNumber;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Collection<VisitEntity> getVisits() {
		return visits;
	}

	public void setVisits(Collection<VisitEntity> visits) { this.visits = visits; }

	public Collection<AddressEntity> getAddresses() { return addresses; }

	public void setAddresses(Collection<AddressEntity> addresses) { this.addresses = addresses; }
}
