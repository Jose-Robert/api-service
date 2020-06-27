package br.com.challenge.api.service.application.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import br.com.challenge.api.service.domain.model.HistoricoPrecoCombustivel;
import br.com.challenge.api.service.domain.service.StorageFileService;
import br.com.challenge.api.service.infrastructure.exception.TipoDeArquivoException;
import br.com.challenge.api.service.infrastructure.repository.HistoricoPrecoCombustivelRepository;
import br.com.challenge.api.service.infrastructure.utils.TipoArquivoUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StorageFileServiceImpl implements StorageFileService {

	@Autowired
	private HistoricoPrecoCombustivelRepository repository;

	@Value("${caminho.arquivo.local}")
	private String caminho;
	
	private String path;
	private String nome;
	private String tipo;
	private Long tamanho;

	@Override
	public void subirArquivoCsv(MultipartFile file) throws IOException {

		path = "";
		nome = file.getOriginalFilename();
		tipo = file.getContentType();
		tamanho = file.getSize();

		/*
		 * VALIDANDO TIPO DE ARQUIVO SELECIONADO
		 */
		isValidoTipoArquivo(tipo);

		byte[] bytes = file.getBytes();

		/*
		 *  Cria diretorio para armazenamento do arquivo.
		 */
		path = criarPath();

		File diretorio = new File(path);

		if (!diretorio.exists()) {
			diretorio.mkdirs();
		}

		/*
		 * Cria o arquivo na maquina local.
		 */
		File arquivoLocal = new File(diretorio.getAbsolutePath() + "/" + nome);

		if (!arquivoLocal.exists()) {

			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(arquivoLocal));

			stream.write(bytes);

			stream.close();
			System.out.println(path);
			
			try {
				LerArquivoCsvPersist(arquivoLocal.getAbsolutePath());
				
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	@Override
	public void LerArquivoCsvPersist(String path) throws IOException {
		
		log.info("Carregando arquivo para persistir no banco de dados.");
		
		final Double valorZerado = 0.0;

        Reader ler = Files.newBufferedReader(Paths.get(path));
        log.info(path);
        log.info("Leitura feita com sucesso.");
        
        CSVReader lerCsv = new CSVReaderBuilder(ler).withSkipLines(1).build();

        List<String[]> hpcList = lerCsv.readAll();

        for (String[] hpcArray : hpcList) {

            LocalDate data = LocalDate.parse(hpcArray[6], DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            double valorVenda = valorZerado;
            double valorCompra = valorZerado;

            if (!hpcArray[8].isEmpty()) {
                valorVenda = Double.parseDouble(hpcArray[8]);
            }
            if (!hpcArray[7].isEmpty()) {
                valorCompra = Double.parseDouble(hpcArray[7]);
            }

            HistoricoPrecoCombustivel toHpc = new HistoricoPrecoCombustivel(hpcArray[0], hpcArray[1], 
            		hpcArray[2],hpcArray[3], hpcArray[4], hpcArray[5], data, valorCompra,
            		valorVenda, hpcArray[9],hpcArray[10]);

            repository.save(toHpc);
            log.info("Arquivo persistido no Banco de dados");
            log.info("Caminho do Arquivo: "+path);

        }

	}

	private void isValidoTipoArquivo(String tipoAqruivo) {

		if (!tipoAqruivo.equalsIgnoreCase(TipoArquivoUtils.MIME_TEXT_X_CSV)) {
			throw new TipoDeArquivoException(
					"Tipo de arquivo invalido, deve possuir Extenção: " + TipoArquivoUtils.MIME_TEXT_X_CSV);

		}

	}
	
	private String criarPath() {

        String data = String.valueOf(LocalDate.now());
        String hora = String.valueOf(LocalTime.now());

        return caminho + "/" + data + "/" + hora.replaceAll("[:.]", "");
    }

}
