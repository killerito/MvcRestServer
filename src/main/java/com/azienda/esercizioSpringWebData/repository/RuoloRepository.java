package com.azienda.esercizioSpringWebData.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.azienda.esercizioSpringWebData.model.Ruolo;

public interface RuoloRepository extends JpaRepository<Ruolo, Integer> {
public Optional<Ruolo> findByNome(String nome);	
}
