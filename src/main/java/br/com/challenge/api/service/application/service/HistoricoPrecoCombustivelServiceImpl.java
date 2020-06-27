package br.com.challenge.api.service.application.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.challenge.api.service.domain.model.HistoricoPrecoCombustivel;
import br.com.challenge.api.service.domain.service.HistoricoPrecoCombustivelService;
import br.com.challenge.api.service.infrastructure.persistence.HistoricoPrecoCombustivelRepository;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class HistoricoPrecoCombustivelServiceImpl implements HistoricoPrecoCombustivelService{
	
	@Autowired
	private HistoricoPrecoCombustivelRepository repository;

	@Override
	public HistoricoPrecoCombustivel salvar(HistoricoPrecoCombustivel historicoPrecoCombustivel) {
		log.info("Cadastrando um Novo Lançamento de Preços de Combustives : " + historicoPrecoCombustivel.toString());
		return repository.save(historicoPrecoCombustivel);
	}

	@Override
	public Optional<HistoricoPrecoCombustivel> buscarPeloIdHistoricoPrecoCombustivel(Long id) {
		log.info("Buscar Historico Preço Combustivel pelo id: " + id);
		Optional<HistoricoPrecoCombustivel> HistoricoPrecoCombustivelSalvo = repository.findById(id);
		if (!HistoricoPrecoCombustivelSalvo.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return HistoricoPrecoCombustivelSalvo;
	}
	
	
		
}
