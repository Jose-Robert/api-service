package br.com.challenge.api.service.domain.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "T_Historico_Preco_Combustivel")
public class HistoricoPrecoCombustivel extends BaseEntity<Long> {

	private static final long serialVersionUID = 4077061097827678931L;

	@Column(name = "Regiao")
	private String regiao;

	@Column(name = "SiglaEstado ")
	private String siglaEstado;

	@Column(name = "SiglaMunicipio")
	private String municipio;

	@Column(name = "RevendaInstalacao")
	private String revendaInstalacao;

	@Column(name = "Codigo")
	private String codigo;

	@Column(name = "Produto")
	private String produto;

	@Column(name = "DataColeta")
	@JsonFormat(pattern = "dd-MM-yyyy")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dataColeta;

	@Column(name = "ValorCompra")
	private Double valorCompra;

	@Column(name = "ValorVenda")
	private Double valorVenda;

	@Column(name = "UnidadeMedida")
	private String unidadeMedida;

	@Column(name = "Bandeira")
	private String bandeira;

}
