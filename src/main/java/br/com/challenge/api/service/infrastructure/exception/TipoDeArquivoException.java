package br.com.challenge.api.service.infrastructure.exception;

public class TipoDeArquivoException extends RuntimeException {

	private static final long serialVersionUID = -6404873973159463448L;

	public TipoDeArquivoException() {
		super();
	}
	
	public TipoDeArquivoException(String message) {
		super(message);
	}

	public TipoDeArquivoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
