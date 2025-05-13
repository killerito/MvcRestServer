package com.azienda.esercizioSpringWebData.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.azienda.esercizioSpringWebData.exception.ProdottoGiaEsistenteException;
import com.azienda.esercizioSpringWebData.exception.ProdottoNonTrovatoException;
import com.azienda.esercizioSpringWebData.model.Prodotto;
import com.azienda.esercizioSpringWebData.repository.ProdottoRepository;

@Service
public class ProdottoService {

    @Autowired
    private ProdottoRepository repo;

    public Prodotto salva(Prodotto prodotto) {
        // Controlla se un prodotto con lo stesso nome esiste già
        if (repo.findAllByNome(prodotto.getNome()).size() > 0) {
            throw new ProdottoGiaEsistenteException("Prodotto con nome '" + prodotto.getNome() + "' già esistente.");
        }
        return repo.save(prodotto);
    }

    public List<Prodotto> trovaPerNome(String nome) {
        return repo.findAllByNome(nome);
    }

    public List<Prodotto> trovaPerNomeEPrezzo(String nome, Float prezzo) {
        return repo.findByNomeAndPrezzo(nome, prezzo);
    }

    public List<Prodotto> trovaTutti() {
        return repo.findAll();
    }
    public void elimina(Integer id) {
        if (!repo.existsById(id)) {
            throw new ProdottoNonTrovatoException("Prodotto con ID " + id + " non trovato.");
        }
        repo.deleteById(id);
    }
}