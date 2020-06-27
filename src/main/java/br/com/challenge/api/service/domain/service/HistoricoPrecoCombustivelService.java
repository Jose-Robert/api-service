package br.com.challenge.api.service.domain.service;

import java.util.Optional;

import br.com.challenge.api.service.domain.model.HistoricoPrecoCombustivel;

public interface HistoricoPrecoCombustivelService {
	
	HistoricoPrecoCombustivel salvar (HistoricoPrecoCombustivel historicoPrecoCombustivel);
	
	Optional<HistoricoPrecoCombustivel> buscarPeloIdHistoricoPrecoCombustivel(Long id);

}
