package br.com.challenge.api.service.infrastructure.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.challenge.api.service.domain.model.HistoricoPrecoCombustivel;
import br.com.challenge.api.service.presentation.dto.ValorMedioVendaECompra;

@Repository
public interface HistoricoPrecoCombustivelRepository extends JpaRepository<HistoricoPrecoCombustivel, Long>, HistoricoPrecoCombustivelRepositoryQuery {
	
	@Query(value = "SELECT avg(ValorVenda) FROM HistoricoPrecoCombustivel WHERE SiglaMunicipio = ?1", nativeQuery = true)
	Double buscarPorValorVendaMunicipio(@Param("municipio") String municipio);

	@Query(value = "SELECT avg(ValorCompra) FROM HistoricoPrecoCombustivel WHERE SiglaMunicipio = ?1", nativeQuery = true)
	Double buscarPorValorCompraMunicipio(@Param("municipio") String municipio);
	
    @Query(value = "SELECT hpc FROM HistoricoPrecoCombustivel hpc GROUP BY DataColeta")
    List<HistoricoPrecoCombustivel> findAllGroupByDataColeta();
	
    @Query(value = "SELECT ValorMedioVendaECompra(avg(ValorCompra) as valorMedioCompra, avg(ValorVenda) as valorMedioVenda)"
    		+ " FROM HistoricoPrecoCombustivel WHERE Bandeira = ?1", nativeQuery = true)
    ValorMedioVendaECompra buscarPorValorBandeira(String bandeira);
	
	@Query(value = "select hpc from HistoricoPrecoCombustivel hpc " + "where hpc.dataColeta = :data ", nativeQuery = true)
	Collection<HistoricoPrecoCombustivel> recuperarHistoricoPorData(@Param("data") LocalDate data);

	@Query(value = "SELECT trunc(SUM(hpc.ValorCompra)/ COUNT(hpc.*),2) " + "FROM HistoricoPrecoCombustivel hpc "
			+ "where  Bandeira = :bandeira;", nativeQuery = true)
	Double recuperarValorMedioCompraPorBandeira(@Param("bandeira") String bandeira);

	@Query(value = "SELECT trunc(SUM(hpc.ValorVenda)/ COUNT(hpc.*),2) " + "FROM HistoricoPrecoCombustivel hpc "
			+ "where  Bandeira = :bandeira;", nativeQuery = true)
	BigDecimal buscarPorValorVendaECompraBandeira(@Param("bandeira") String bandeira);


}
