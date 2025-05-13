package com.azienda.esercizioSpringWebData.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.azienda.esercizioSpringWebData.model.Ruolo;
import com.azienda.esercizioSpringWebData.model.Utente;
import com.azienda.esercizioSpringWebData.service.RuoloService;
import com.azienda.esercizioSpringWebData.service.UtenteService;

import jakarta.annotation.PostConstruct;
@Component
public class AmministratoreCreato {

	    @Autowired
	    private RuoloService ruoloService;

	    @Autowired
	    private UtenteService utenteService;

	    @PostConstruct
	    public void init() {
	    	 Ruolo ruoloAdmin = ruoloService.trovaPerNome("administrator").orElse(null);
	         if (ruoloAdmin == null) {
	             ruoloAdmin = ruoloService.salva(new Ruolo("administrator"));
	         }

	         // Crea utente admin se non esiste
	         if (!utenteService.usernameEsiste("admin")) {
	             Utente admin = new Utente();
	             admin.setUsername("admin");
	             admin.setPassword("admin");  
	             admin.setLogged(false);
	             admin.setRuolo(ruoloAdmin);

	             utenteService.salva(admin);
	             System.out.println("Utente admin creato");
	         } else {
	             System.out.println("Utente admin già esistente");
	         }
	     }
	}
