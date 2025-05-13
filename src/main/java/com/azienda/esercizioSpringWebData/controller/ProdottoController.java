package com.azienda.esercizioSpringWebData.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.azienda.esercizioSpringWebData.exception.ProdottoGiaEsistenteException;
import com.azienda.esercizioSpringWebData.model.Prodotto;
import com.azienda.esercizioSpringWebData.model.Utente;
import com.azienda.esercizioSpringWebData.service.ProdottoService;

import jakarta.servlet.http.HttpSession;
@Controller
public class ProdottoController {
	@Autowired
	private ProdottoService prodottoService;
	
    @GetMapping("/ricercaProdotti")
    public String mostraRicercaProdotti() {
        return "ricercaProdotti"; 
    }
	// Mostra la pagina di creazione prodotto
	@GetMapping("/creaProdotto")
	public String mostraCreaProdotto(HttpSession session, Model model) {
		try {
			Utente utente = (Utente) session.getAttribute("utente");
			if (utente != null && utente.getRuolo().getNome().equals("administrator")) {
				return "creaProdotto"; // Solo gli amministratori possono creare prodotti
			} else {
				model.addAttribute("errore", "Accesso non autorizzato.");
				return "homeInterna"; // Reindirizza alla home se non è un amministratore
			}
		} catch (Exception e) {
			model.addAttribute("errore", "Errore durante il caricamento della pagina.");
			return "homeInterna"; // Errore durante il caricamento
		}
	}

	// Crea un prodotto
	@PostMapping("/creaProdotto")
    public String creaProdotto(Prodotto prodotto, Model model) {
        try {
            prodottoService.salva(prodotto);
            model.addAttribute("messaggio", "Prodotto creato con successo.");
            return "esito"; // Mostra la pagina di esito
        } catch (ProdottoGiaEsistenteException e) {
            model.addAttribute("errore", e.getMessage());
            return "esito";  // Mostra l'errore sulla pagina di esito
        } catch (Exception e) {
            model.addAttribute("errore", "Errore durante la creazione del prodotto: " + e.getMessage());
            return "esito";  // Mostra l'errore sulla pagina di esito
        }
    }
	@GetMapping("/visualizzaProdotti")
	public String visualizzaProdotti(Model model) {
		try {
			List<Prodotto> prodotti = prodottoService.trovaTutti();
			model.addAttribute("prodotti", prodotti);
			return "visualizzaProdotti"; // Mostra la lista dei prodotti
		} catch (Exception e) {
			model.addAttribute("errore", "Errore durante il caricamento dei prodotti: " + e.getMessage());
			return "esito";  // Errore durante il caricamento
		}
	}

	// Ricerca dei prodotti
	@PostMapping("/ricercaProdotti")
	public String ricercaProdotti(@RequestParam(required = false) String nome, 
			@RequestParam(required = false) Float prezzo, 
			Model model) {
		try {
			List<Prodotto> prodotti;

			// Se entrambi i parametri sono forniti
			if (nome != null && !nome.isEmpty() && prezzo != null) {
				prodotti = prodottoService.trovaPerNomeEPrezzo(nome, prezzo);
			} 
			// Se solo il nome è fornito
			else if (nome != null && !nome.isEmpty()) {
				prodotti = prodottoService.trovaPerNome(nome);
			} 
			// Se nessun parametro è fornito
			else {
				prodotti = prodottoService.trovaTutti();
			}

			// Se non ci sono risultati, aggiungi un messaggio di errore
			if (prodotti.isEmpty()) {
				model.addAttribute("errore", "Nessun prodotto trovato.");
				return "esito"; // Puoi sostituirlo con la vista appropriata
			}

			// Aggiungi i prodotti trovati al modello
			model.addAttribute("prodotti", prodotti);
			return "risultatiRicercaProdotti"; // Mostra i risultati della ricerca
		} catch (Exception e) {
			// Gestisci eventuali errori e mostra un messaggio di errore
			model.addAttribute("errore", "Errore durante la ricerca: " + e.getMessage());
			return "esito";  // Errore durante la ricerca
		}
	}
}