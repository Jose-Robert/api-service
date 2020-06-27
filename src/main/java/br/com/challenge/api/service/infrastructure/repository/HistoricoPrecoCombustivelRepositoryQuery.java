package br.com.challenge.api.service.infrastructure.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.challenge.api.service.domain.model.HistoricoPrecoCombustivel;
import br.com.challenge.api.service.infrastructure.filter.HistoricoPrecoCombustivelFilter;

public interface HistoricoPrecoCombustivelRepositoryQuery {
	
	public Page<HistoricoPrecoCombustivel> filtrar(HistoricoPrecoCombustivelFilter filter, Pageable pageable);

}
