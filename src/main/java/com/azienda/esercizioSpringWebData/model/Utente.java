package com.azienda.esercizioSpringWebData.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Utente {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
private String username;
private String password;
private boolean logged;
@ManyToOne
private Ruolo ruolo;
public Utente() {
	super();
}
public Utente(String username, String password, boolean logged) {
	super();
	this.username = username;
	this.password = password;
	this.logged = logged;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public boolean isLogged() {
	return logged;
}
public void setLogged(boolean logged) {
	this.logged = logged;
}

public Ruolo getRuolo() {
	return ruolo;
}
public void setRuolo(Ruolo ruolo) {
	this.ruolo = ruolo;
}
@Override
public String toString() {
	return "Utente [id=" + id + ", username=" + username + ", password=" + password + ", logged=" + logged + "]";
}

}
