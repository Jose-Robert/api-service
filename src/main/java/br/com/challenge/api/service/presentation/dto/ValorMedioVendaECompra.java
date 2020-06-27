package br.com.challenge.api.service.presentation.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class ValorMedioVendaECompra {

	  private BigDecimal valorMedioCompra;	
	  private BigDecimal valorMedioVenda;	
	 
	   
}