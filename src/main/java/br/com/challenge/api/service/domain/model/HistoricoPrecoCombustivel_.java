package br.com.challenge.api.service.domain.model;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HistoricoPrecoCombustivel.class)
public abstract class HistoricoPrecoCombustivel_ extends br.com.challenge.api.service.domain.model.BaseEntity_ {

	public static volatile SingularAttribute<HistoricoPrecoCombustivel, String> codigo;
	public static volatile SingularAttribute<HistoricoPrecoCombustivel, String> siglaEstado;
	public static volatile SingularAttribute<HistoricoPrecoCombustivel, String> produto;
	public static volatile SingularAttribute<HistoricoPrecoCombustivel, String> municipio;
	public static volatile SingularAttribute<HistoricoPrecoCombustivel, Double> valorVenda;
	public static volatile SingularAttribute<HistoricoPrecoCombustivel, Double> valorCompra;
	public static volatile SingularAttribute<HistoricoPrecoCombustivel, String> unidadeMedida;
	public static volatile SingularAttribute<HistoricoPrecoCombustivel, LocalDate> dataColeta;
	public static volatile SingularAttribute<HistoricoPrecoCombustivel, String> regiao;
	public static volatile SingularAttribute<HistoricoPrecoCombustivel, String> revendaInstalacao;
	public static volatile SingularAttribute<HistoricoPrecoCombustivel, String> bandeira;

	public static final String CODIGO = "codigo";
	public static final String SIGLA_ESTADO = "siglaEstado";
	public static final String PRODUTO = "produto";
	public static final String MUNICIPIO = "municipio";
	public static final String VALOR_VENDA = "valorVenda";
	public static final String VALOR_COMPRA = "valorCompra";
	public static final String UNIDADE_MEDIDA = "unidadeMedida";
	public static final String DATA_COLETA = "dataColeta";
	public static final String REGIAO = "regiao";
	public static final String REVENDA_INSTALACAO = "revendaInstalacao";
	public static final String BANDEIRA = "bandeira";

}

