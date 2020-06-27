package br.com.challenge.api.service.infrastructure.filter;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class HistoricoPrecoCombustivelFilter {
	
	private String regiao;

	private String siglaEstado;

	private String municipio;

	private String revendaInstalacao;

	private String codigo;

	private String produto;

	@JsonFormat(pattern = "dd-MM-yyyy")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dataColeta;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dataColetaDe;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dataColetaAte;
	
	private BigDecimal valorCompra;
	
	private BigDecimal valorVenda;

	private String unidadeMedida;

	private String bandeira;

}
