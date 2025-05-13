package com.azienda.esercizioSpringWebData.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.azienda.esercizioSpringWebData.model.Prodotto;

public interface ProdottoRepository extends JpaRepository<Prodotto,Integer> {
	@Query("SELECT p FROM Prodotto p WHERE LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Prodotto> findAllByNome(String nome);

    // Ricerca per nome e prezzo
    @Query("SELECT p FROM Prodotto p WHERE LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%')) AND p.prezzo = :prezzo")
    List<Prodotto> findByNomeAndPrezzo(String nome, Float prezzo);
}

