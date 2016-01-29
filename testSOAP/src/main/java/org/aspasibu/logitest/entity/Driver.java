package org.aspasibu.logitest.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.apache.commons.codec.digest.DigestUtils;

@Entity
@SequenceGenerator(name = "driver_gen", initialValue = 1)
public class Driver {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "driver_gen")
	@Column(nullable = false)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String surname;

	@Column(unique = true, nullable = false)
	private String userName;

	@Column(nullable = false)
	private String password;

	public Driver() {
	}

	public Driver(String surname, String name, String username,
			String password) {
		this.surname = surname;
		this.name = name;
		this.userName = username;
		setPassword(password);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = DigestUtils.md5Hex(password);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getName(), this.getSurname(),
				this.getPassword(), this.getUserName(), this.getId());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Driver other = (Driver) obj;
		if (!Objects.equals(this.getName(), other.getName()))
			return false;
		if (!Objects.equals(this.getSurname(), other.getSurname()))
			return false;
		if (!Objects.equals(this.getPassword(), other.getPassword()))
			return false;
		if (!Objects.equals(this.getUserName(), other.getUserName()))
			return false;
		if (!Objects.equals(this.getId(), other.getId()))
			return false;
		return true;

	}

	public boolean passwordEquals(String password) {
		if (password == null) {
			return false;
		}		
		
		return DigestUtils.md5Hex(password).equals(this.password);
	}
}
