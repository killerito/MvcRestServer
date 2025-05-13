package com.azienda.esercizioSpringWebData.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.azienda.esercizioSpringWebData.model.Ruolo;
import com.azienda.esercizioSpringWebData.repository.RuoloRepository;

@Service
public class RuoloService {

	 @Autowired
	    private RuoloRepository repo;

	    public Optional<Ruolo> trovaPerNome(String nome) {
	        return repo.findByNome(nome);
	    }

	    public Ruolo salva(Ruolo ruolo) {
	        return repo.save(ruolo);
	    }

	    public boolean esiste(String nome) {
	        return repo.findByNome(nome).isPresent();
	    }
	}

