package com.azienda.esercizioSpringWebData.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.azienda.esercizioSpringWebData.model.Utente;

public interface UtenteRepository extends JpaRepository<Utente, Integer> {
public Optional<Utente> findByUsername(String username);
public Optional<Utente> findByUsernameAndPassword(String username, String password);
}
