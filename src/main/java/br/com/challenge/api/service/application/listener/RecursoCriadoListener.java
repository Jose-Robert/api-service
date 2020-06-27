package br.com.challenge.api.service.application.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.challenge.api.service.application.event.RecursoCriadorEvent;

public class RecursoCriadoListener implements ApplicationListener<RecursoCriadorEvent> {

	// EVENTO QUE ESCUTA E EXECUTA O EVENTO/CONTEXTO...

	@Override
	public void onApplicationEvent(RecursoCriadorEvent recursoCriadoEvent) {
		HttpServletResponse response = recursoCriadoEvent.getResponse();
		Long codigo = recursoCriadoEvent.getCodigo();

		adicionarHeaderLocation(response, codigo);
	}

	// ESTE METODO ADICIONA NA HEADER HTTP O ID DO NOVO ITEM CRIADO.....

	private void adicionarHeaderLocation(HttpServletResponse response, Long id) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(id).toUri();
		response.setHeader("Location", uri.toASCIIString());
	}
}