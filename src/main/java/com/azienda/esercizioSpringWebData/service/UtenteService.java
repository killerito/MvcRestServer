package com.azienda.esercizioSpringWebData.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.azienda.esercizioSpringWebData.model.Utente;
import com.azienda.esercizioSpringWebData.repository.UtenteRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UtenteService {
	@Autowired
	private UtenteRepository repo;

	public Optional<Utente> login(String username, String password) {
	    return repo.findByUsernameAndPassword(username, password);
	}

	    public boolean usernameEsiste(String username) {
	        return repo.findByUsername(username).isPresent();
	    }
	    
	    public Utente salva(Utente utente) {
	        return repo.save(utente);
	    }
	}