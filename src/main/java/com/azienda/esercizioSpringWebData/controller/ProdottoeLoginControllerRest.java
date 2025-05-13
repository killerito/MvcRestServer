package com.azienda.esercizioSpringWebData.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.azienda.esercizioSpringWebData.exception.ProdottoNonTrovatoException;
import com.azienda.esercizioSpringWebData.model.Prodotto;
import com.azienda.esercizioSpringWebData.model.Utente;
import com.azienda.esercizioSpringWebData.service.ProdottoService;
import com.azienda.esercizioSpringWebData.service.UtenteService;

@RestController
@RequestMapping(path ="/home/prodotti" , produces = "application/json")
@CrossOrigin (origins = "*")
public class ProdottoeLoginControllerRest {
	@Autowired
	private ProdottoService prodottoService;
	@Autowired
	private UtenteService utenteService;

	@GetMapping("/login") // questo verifica le credenziali e il login dell'admin
			public ResponseEntity<?> verificaCredenziali(String username, String password, boolean richiedeAdmin) {
	    if (username == null || password == null) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Credenziali mancanti.");
	    }

	    Optional<Utente> utenteOpt = utenteService.login(username, password);
	    if (utenteOpt.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utente non trovato o credenziali errate.");
	    }

	    Utente utente = utenteOpt.get();
	    if (richiedeAdmin && !"administrator".equals(utente.getRuolo().getNome())) {
	        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Accesso non autorizzato per l’operazione.");
	    }

	    return null; 
	}
	 @GetMapping("/loginEffettivo")
	    public ResponseEntity<?> loginEffettivo(@RequestParam String username, @RequestParam String password) {
	        Optional<Utente> utenteOpt = utenteService.login(username, password);
	        if (utenteOpt.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utente non trovato o credenziali errate.");
	        }

	        return ResponseEntity.ok("Login effettuato con successo.");
	    }
	@GetMapping("/getAllprodotti")
	public ResponseEntity<?> getAll(@RequestHeader String username, @RequestHeader String password) {
		ResponseEntity<?> check = verificaCredenziali(username, password, false);
		if (check != null) return check;

		try {
			return ResponseEntity.ok(prodottoService.trovaTutti());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Errore nel recupero dei prodotti.");
		}
	}
	@GetMapping("/prodotto/{id}")
	public ResponseEntity<?> getById(@PathVariable Integer id, @RequestHeader String username, @RequestHeader String password) {
	    ResponseEntity<?> check = verificaCredenziali(username, password, false);
	    if (check != null) return check;

	    try {
	        // Ottieni tutti i prodotti
	        List<Prodotto> prodotti = prodottoService.trovaTutti();

	        // Cerca il prodotto con l'ID specificato
	        for (Prodotto prodotto : prodotti) {
	            if (prodotto.getId().equals(id)) {
	                return ResponseEntity.ok(prodotto);
	            }
	        }

	        // Se non trovi il prodotto con quell'ID, lancia un'eccezione
	        throw new ProdottoNonTrovatoException("Prodotto con ID " + id + " non trovato.");
	    } catch (ProdottoNonTrovatoException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    } catch (Exception e) {
	        return ResponseEntity.internalServerError().body("Errore durante la ricerca del prodotto.");
	    }
	}
	@GetMapping("/nome/{nome}")
	public ResponseEntity<?> getByNome(@PathVariable String nome, @RequestHeader String username, @RequestHeader String password) {
		ResponseEntity<?> check = verificaCredenziali(username, password, false);
		if (check != null) return check;

		try {
			return ResponseEntity.ok(prodottoService.trovaPerNome(nome));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Errore durante la ricerca per nome.");
		}
	}
	@GetMapping("/prezzo-minore-di/{prezzo}")
	public ResponseEntity<?> getByPrezzo(@PathVariable Float prezzo, @RequestHeader String username, @RequestHeader String password) {
	    ResponseEntity<?> check = verificaCredenziali(username, password, false);
	    if (check != null) return check;

	    try {
	        // Crea una lista per memorizzare i prodotti con prezzo inferiore
	        List<Prodotto> prodottiFiltrati = new ArrayList<>();

	        // Itera sui prodotti e aggiungi quelli che soddisfano la condizione
	        for (Prodotto prodotto : prodottoService.trovaTutti()) {
	            if (prodotto.getPrezzo() < prezzo) {
	                prodottiFiltrati.add(prodotto);
	            }
	        }

	        // Restituisci la lista filtrata
	        return ResponseEntity.ok(prodottiFiltrati);
	    } catch (Exception e) {
	        return ResponseEntity.internalServerError().body("Errore durante il filtro per prezzo.");
	    }
	}
	@GetMapping("/visualizzaAvanzata")
	public ResponseEntity<?> filtra(@RequestParam String nome, @RequestParam Float prezzo,
			@RequestHeader String username, @RequestHeader String password) {
		ResponseEntity<?> check = verificaCredenziali(username, password, false);
		if (check != null) return check;

		try {
			List<Prodotto> prodottiFiltrati = new ArrayList<>();

			for (Prodotto prodotto : prodottoService.trovaTutti()) {
				if (prodotto.getNome().toLowerCase().contains(nome.toLowerCase()) && prodotto.getPrezzo() < prezzo) {
					prodottiFiltrati.add(prodotto);
				}
			}

			return ResponseEntity.ok(prodottiFiltrati);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Errore durante il filtro combinato.");
		}
	}
	@PostMapping("/crea")
    public ResponseEntity<?> crea(@RequestBody Prodotto prodotto,
                                  @RequestHeader String username, @RequestHeader String password) {
        ResponseEntity<?> check = verificaCredenziali(username, password, true);
        if (check != null) return check;

        return ResponseEntity.status(HttpStatus.CREATED).body(prodottoService.salva(prodotto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> aggiorna(@PathVariable Integer id, @RequestBody Prodotto nuovoProdotto,
                                      @RequestHeader String username, @RequestHeader String password) {
        ResponseEntity<?> check = verificaCredenziali(username, password, true);
        if (check != null) return check;

        nuovoProdotto.setId(id);
        return ResponseEntity.ok(prodottoService.salva(nuovoProdotto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> aggiornaParziale(@PathVariable Integer id, @RequestBody Prodotto patch,
                                              @RequestHeader String username, @RequestHeader String password) {
        ResponseEntity<?> check = verificaCredenziali(username, password, true);
        if (check != null) return check;

        // Trova il prodotto con ID corrispondente
        Prodotto prodottoDaAggiornare = null;
        for (Prodotto prodotto : prodottoService.trovaTutti()) {
            if (prodotto.getId().equals(id)) {
                prodottoDaAggiornare = prodotto;
                break;
            }
        }

        // Se non è stato trovato il prodotto
        if (prodottoDaAggiornare == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Prodotto non trovato.");
        }

        // Applica le modifiche parziali
        if (patch.getNome() != null) prodottoDaAggiornare.setNome(patch.getNome());
        if (patch.getPrezzo() != null) prodottoDaAggiornare.setPrezzo(patch.getPrezzo());

        // Salva il prodotto aggiornato
        return ResponseEntity.ok(prodottoService.salva(prodottoDaAggiornare));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> elimina(@PathVariable Integer id,
                                     @RequestHeader String username, @RequestHeader String password) {
        ResponseEntity<?> check = verificaCredenziali(username, password, true);
        if (check != null) return check;

        try {
            prodottoService.elimina(id); // Chiama il servizio per eliminare il prodotto
            return ResponseEntity.noContent().build(); // Restituisce 204 No Content
        } catch (ProdottoNonTrovatoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); // Gestisce l'errore se il prodotto non viene trovato
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore durante l'eliminazione del prodotto.");
        }
    }

    @DeleteMapping("/intervallo") // Elimina prodotti in intervallo di prezzo
    public ResponseEntity<?> eliminaIntervallo(@RequestParam Float min, @RequestParam Float max,
                                               @RequestHeader String username, @RequestHeader String password) {
        ResponseEntity<?> check = verificaCredenziali(username, password, true);
        if (check != null) return check;

        // Crea una lista per i prodotti da eliminare
        List<Prodotto> daEliminare = new ArrayList<>();

        // Itera su tutti i prodotti e seleziona quelli che rientrano nell'intervallo di prezzo
        for (Prodotto prodotto : prodottoService.trovaTutti()) {
            if (prodotto.getPrezzo() >= min && prodotto.getPrezzo() <= max) {
                daEliminare.add(prodotto);
            }
        }

        // Elimina i prodotti trovati
        for (Prodotto prodotto : daEliminare) {
            prodottoService.elimina(prodotto.getId());
        }

        // Restituisce risposta con stato 204 No Content
        return ResponseEntity.noContent().build();
    }
}
