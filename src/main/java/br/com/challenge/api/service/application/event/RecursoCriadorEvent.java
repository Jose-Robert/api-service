package br.com.challenge.api.service.application.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecursoCriadorEvent extends ApplicationEvent {
	
	
	private static final long serialVersionUID = -7563951052265925174L;
	
	private HttpServletResponse response;
	private Long codigo;

	public RecursoCriadorEvent(Object source, HttpServletResponse response, Long codigo) {
		super(source);
		this.response = response;
		this.codigo = codigo;
	}

}