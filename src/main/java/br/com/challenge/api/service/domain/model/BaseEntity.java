package br.com.challenge.api.service.domain.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public abstract class BaseEntity< T extends Serializable > implements Serializable{
	
	private static final long serialVersionUID = -917842785809543543L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

}
