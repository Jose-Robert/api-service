package br.com.challenge.api.service.infrastructure.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import br.com.challenge.api.service.domain.model.HistoricoPrecoCombustivel;
import br.com.challenge.api.service.domain.model.HistoricoPrecoCombustivel_;
import br.com.challenge.api.service.infrastructure.filter.HistoricoPrecoCombustivelFilter;

public class HistoricoPrecoCombustivelRepositoryImpl implements HistoricoPrecoCombustivelRepositoryQuery {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<HistoricoPrecoCombustivel> filtrar(HistoricoPrecoCombustivelFilter historicoPrecoCombustivelFilter, Pageable pageable) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<HistoricoPrecoCombustivel> criteria = builder.createQuery(HistoricoPrecoCombustivel.class);
		Root<HistoricoPrecoCombustivel> root = criteria.from(HistoricoPrecoCombustivel.class);

		Predicate[] predicates = criarRestricoes(historicoPrecoCombustivelFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<HistoricoPrecoCombustivel> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(historicoPrecoCombustivelFilter));
	}

	private Predicate[] criarRestricoes(HistoricoPrecoCombustivelFilter historicoPrecoCombustivelFilter, CriteriaBuilder builder,
			Root<HistoricoPrecoCombustivel> root) {
		List<Predicate> predicates = new ArrayList<>();

		// Filtro por Municipio: Listando por Municipio
		if (!StringUtils.isEmpty(historicoPrecoCombustivelFilter.getMunicipio())) {
			predicates.add(builder.like(builder.lower(root.get(HistoricoPrecoCombustivel_.MUNICIPIO)),
					"%" + historicoPrecoCombustivelFilter.getMunicipio().toLowerCase() + "%"));
		}

		// Filtro por Sigla - Estado: Listando por sigla de Estado

		if (!StringUtils.isEmpty(historicoPrecoCombustivelFilter.getSiglaEstado())) {
			predicates.add(builder.like(builder.lower(root.get(HistoricoPrecoCombustivel_.SIGLA_ESTADO)),
					"%" + historicoPrecoCombustivelFilter.getSiglaEstado().toLowerCase() + "%"));
		}

		// Filtro por Região: Listando por região brasileira
		if (!StringUtils.isEmpty(historicoPrecoCombustivelFilter.getRegiao())) {
			predicates.add(builder.like(builder.lower(root.get(HistoricoPrecoCombustivel_.REGIAO)),
					"%" + historicoPrecoCombustivelFilter.getRegiao().toLowerCase() + "%"));
		}

		// Filtro por Bandeira: Listando por Bandeira da Empresa Distribuidora
		if (!StringUtils.isEmpty(historicoPrecoCombustivelFilter.getBandeira())) {
			predicates.add(builder.like(builder.lower(root.get(HistoricoPrecoCombustivel_.BANDEIRA)),
					"%" + historicoPrecoCombustivelFilter.getBandeira().toLowerCase() + "%"));
		}

		// Filtro por Empresa: Listando por Empresa
		if (!StringUtils.isEmpty(historicoPrecoCombustivelFilter.getRevendaInstalacao())) {
			predicates.add(builder.like(builder.lower(root.get(HistoricoPrecoCombustivel_.REVENDA_INSTALACAO)),
					"%" + historicoPrecoCombustivelFilter.getRevendaInstalacao().toLowerCase() + "%"));
		}

		// Filtro por Tipo de Combustivel: Listando por Tipo de Combustivel
		if (!StringUtils.isEmpty(historicoPrecoCombustivelFilter.getProduto())) {
			predicates.add(builder.like(builder.lower(root.get(HistoricoPrecoCombustivel_.PRODUTO)),
					"%" + historicoPrecoCombustivelFilter.getProduto().toLowerCase() + "%"));
		}

		// Filtro por data de Coleta_De: Listando por Data de Coleta de determinado
		// Inicio
		if (historicoPrecoCombustivelFilter.getDataColetaDe() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(HistoricoPrecoCombustivel_.DATA_COLETA),
					historicoPrecoCombustivelFilter.getDataColetaDe()));
		}
		// Filtro por data de Coleta_Ate: Listando por Data de Coleta de ate determinado
		// dia
		if (historicoPrecoCombustivelFilter.getDataColetaAte() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get(HistoricoPrecoCombustivel_.DATA_COLETA),
					historicoPrecoCombustivelFilter.getDataColetaAte()));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}

	private Long total(HistoricoPrecoCombustivelFilter historicoPrecoCombustivelFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<HistoricoPrecoCombustivel> root = criteria.from(HistoricoPrecoCombustivel.class);

		Predicate[] predicates = criarRestricoes(historicoPrecoCombustivelFilter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
