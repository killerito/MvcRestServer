package com.azienda.esercizioSpringWebData.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.azienda.esercizioSpringWebData.exception.UtenteNonTrovatoException;
import com.azienda.esercizioSpringWebData.model.Ruolo;
import com.azienda.esercizioSpringWebData.model.Utente;
import com.azienda.esercizioSpringWebData.service.RuoloService;
import com.azienda.esercizioSpringWebData.service.UtenteService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private RuoloService ruoloService;
    
    @GetMapping("/index")
    public String mostraIndex() {
        return "index";
    }

    @GetMapping("/menu")
    public String mostraMenu(HttpSession session) {
        Utente utente = (Utente) session.getAttribute("utente");

        if (utente == null) {
            return "redirect:/login";
        }

        if ("administrator".equals(utente.getRuolo().getNome())) {
            return "menuAdmin"; 
        } else {
            return "menuUtente"; 
        }
    }
    // Mostra la pagina di login
    @GetMapping("/login")
    public String mostraLogin() {
        return "paginaLogin";
    }

    // Gestisce il login
    @PostMapping("/login")
    public String login(String username, String password, HttpSession session, Model model) {
        try {
            Optional<Utente> utenteOpt = utenteService.login(username, password);

            if (utenteOpt.isPresent()) {
                Utente utente = utenteOpt.get();
                utente.setLogged(true);  // Imposta il flag logged su true
                session.setAttribute("utente", utente);  // Salva l'utente nella sessione

                // Puoi reindirizzare in base al ruolo dell'utente
                if ("administrator".equals(utente.getRuolo().getNome())) {
                    return "menuAdmin"; // Successo per admin
                } else {
                    return "menuUtente"; // Successo per utente normale
                }
            } else {
                throw new UtenteNonTrovatoException("Utente non trovato o credenziali errate.");
            }
        } catch (UtenteNonTrovatoException e) {
            model.addAttribute("errore", e.getMessage());
            return "paginaLogin"; // Login fallito
        } catch (Exception e) {
            model.addAttribute("errore", "Errore durante il login. Riprova più tardi.");
            return "paginaLogin"; // Errore durante il login
        }
    }

    // Logout dell'utente
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();  // Invalida la sessione dell'utente
        return "index";  // Reindirizza alla pagina della home
    }

    // Mostra la pagina di registrazione
    @GetMapping("/registrazione")
    public String mostraRegistrazione() {
        return "paginaRegistrazione";
    }

    // Gestisce la registrazione dell'utente
    @PostMapping("/registrazione")
    public String registra(Utente utente, Model model) {
        try {
            // Controlla se l'username esiste già
            if (utenteService.usernameEsiste(utente.getUsername())) {
                model.addAttribute("errore", "Username già esistente.");
                return "paginaRegistrazione"; // Se l'username esiste, torna alla pagina di registrazione
            }

            // Verifica se il ruolo "user" esiste già nel database
            Ruolo ruolo = ruoloService.trovaPerNome("user").orElse(null);
            if (ruolo == null) {
                // Se il ruolo "user" non esiste, crealo
                ruolo = new Ruolo("user");
                ruoloService.salva(ruolo); // Salva il ruolo nel database
            }

            // Assegna il ruolo "user" all'utente
            utente.setRuolo(ruolo);

            // Salva l'utente nel database
            utenteService.salva(utente);
            model.addAttribute("messaggio", "Registrazione avvenuta con successo.");
            return "paginaLogin";  // Reindirizza alla pagina di login
        } catch (Exception e) {
            model.addAttribute("errore", "Errore durante la registrazione. Riprova più tardi.");
            return "paginaRegistrazione";  // Errore durante la registrazione
        }
    }
}

