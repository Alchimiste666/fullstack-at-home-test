package com.rize.test.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Cacheable
@Table(name = "ARTISTS")
@Cache(region = "artits", usage = CacheConcurrencyStrategy.READ_WRITE)
public class Artist implements Serializable {

	// Fields
	@Id
	@SequenceGenerator(name = "artistSequenceGenerator", sequenceName = "ARTISTS_SEQUENCE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "artistSequenceGenerator")
	private Integer id;

	@CreationTimestamp
	@Column(name = "CREATED_AT", updatable = false, nullable = false, columnDefinition = "timestamp with time zone")
	private Instant createdAt;

	@UpdateTimestamp
	@Column(name = "UPDATED_AT", nullable = false, columnDefinition = "timestamp with time zone")
	private Instant updatedAt;

	@NotEmpty(message = "First name is required")
	@Column(name = "FIRST_NAME", length = 100, nullable = false)
	private String firstName;

	@Column(name = "MIDDLE_NAME", length = 100)
	private String middleName;

	@NotEmpty(message = "Last name is required")
	@Column(name = "LAST_NAME", length = 100, nullable = false)
	private String lastName;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "Category is required")
	@Column(name = "CATEGORY", nullable = false)
	private Category category;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull(message = "Birthday is required")
	@Column(name = "BIRTHDAY", nullable = false)
	private Date birthday;

	@NotNull(message = "Email is required")
	@Column(name = "EMAIL", length = 100, nullable = false, unique = true)
	private String email;

	@Column(name = "NOTES")
	private String notes;

	// Read only columns
	@Column(name = "FULL_NAME", length = 256, nullable = false, insertable = false, updatable = false)
	private String fullName;

	// Read only columns
	@Column(name = "BIRTHDAY_MONTH", length = 20, nullable = false, insertable = false, updatable = false)
	private String birthdayMonth;

	// Getters and setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getFullName() {
		return fullName;
	}

	public String getBirthdayMonth() {
		return birthdayMonth;
	}
}
