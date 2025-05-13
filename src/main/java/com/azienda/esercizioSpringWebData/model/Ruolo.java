package com.azienda.esercizioSpringWebData.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Ruolo {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
private String nome;
public Ruolo() {
super();
}
public Ruolo(String nome) {
	super();
	this.nome = nome;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
@Override
public String toString() {
	return "Ruolo [id=" + id + ", nome=" + nome + "]";
}

}
