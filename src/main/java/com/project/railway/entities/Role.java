package com.project.railway.entities;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "email", referencedColumnName = "email")
//	private User user;

	private String role;

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(int id, String role) {
		super();
		this.id = id;
//		this.user = user;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
