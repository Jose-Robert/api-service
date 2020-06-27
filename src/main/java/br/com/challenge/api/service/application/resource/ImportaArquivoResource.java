package br.com.challenge.api.service.application.resource;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.challenge.api.service.domain.service.StorageFileService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/importar")
public class ImportaArquivoResource {
	
	@Autowired
	private StorageFileService storeFileService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/csv")
	public void carregaCsv(@RequestParam(name = "file") MultipartFile file) {
		try {
			storeFileService.subirArquivoCsv(file);
		} catch (IOException e) {
			log.info("Falha ao fazer upload do arquivo.");
			e.printStackTrace();
		}
	}
}
