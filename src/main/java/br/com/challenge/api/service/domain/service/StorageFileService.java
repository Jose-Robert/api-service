package br.com.challenge.api.service.domain.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface StorageFileService {
	
	void subirArquivoCsv(MultipartFile file) throws IOException;
	void LerArquivoCsvPersist(String path) throws IOException;
	

}
