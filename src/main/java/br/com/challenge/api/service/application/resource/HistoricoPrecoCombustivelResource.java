package br.com.challenge.api.service.application.resource;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.challenge.api.service.application.event.RecursoCriadorEvent;
import br.com.challenge.api.service.domain.model.HistoricoPrecoCombustivel;
import br.com.challenge.api.service.domain.service.HistoricoPrecoCombustivelService;
import br.com.challenge.api.service.infrastructure.filter.HistoricoPrecoCombustivelFilter;
import br.com.challenge.api.service.infrastructure.persistence.HistoricoPrecoCombustivelRepository;
import br.com.challenge.api.service.presentation.dto.ValorMedioVendaECompra;

@RestController
@RequestMapping("/2018-1_CA")
public class HistoricoPrecoCombustivelResource {
	
	@Autowired
	private HistoricoPrecoCombustivelRepository repository;
	
	@Autowired
	private HistoricoPrecoCombustivelService service;
	
	@Autowired
	private ApplicationEventPublisher publisherEvent;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Page<HistoricoPrecoCombustivel> pesquisar(HistoricoPrecoCombustivelFilter filter, Pageable page) {
		return repository.filtrar(filter, page);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<HistoricoPrecoCombustivel> criar(@Valid @RequestBody HistoricoPrecoCombustivel historicoPrecoCombustivel, HttpServletResponse response) {
		HistoricoPrecoCombustivel historicoPrecoCombustivelSalvo = service.salvar(historicoPrecoCombustivel);
		publisherEvent.publishEvent(new RecursoCriadorEvent(this, response, historicoPrecoCombustivelSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(historicoPrecoCombustivelSalvo);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/id/{id}")
	public ResponseEntity<HistoricoPrecoCombustivel> buscarPorId(@PathVariable(required = true) Long id){
		HistoricoPrecoCombustivel hpc = service.buscarPeloIdHistoricoPrecoCombustivel(id).get();
		return hpc != null ? ResponseEntity.ok(hpc) : ResponseEntity.notFound().build();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/find/media/valor/compra/municipio", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public BigDecimal buscarPorValorCompra(@RequestParam(name = "municipio", required = true) String municipio) {
		return repository.buscarPorValorCompraMunicipio(municipio);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/find/media/valor/venda/municipio", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public BigDecimal buscarPorValorVenda(@RequestParam(name = "municipio", required = true) String municipio) {
		return repository.buscarPorValorVendaMunicipio(municipio);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/find/media/valor/compraevenda/bandeira", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ValorMedioVendaECompra valorMedioVendaECompra(
			@RequestParam(name = "bandeira", required = true) String bandeira) {
		return repository.buscarPorValorBandeira(bandeira);
	}

	@GetMapping(value = "/find/all/groupdatacoleta", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<HistoricoPrecoCombustivel> findAllGroupByDataColeta() {
		return repository.findAllGroupByDataColeta();
	}
	
	

}
