package br.com.dan.Exceptions;

public class TipoElementoNaoConhecidoException extends Exception {
	
	private static final long serialVersionUID = 0;

	public TipoElementoNaoConhecidoException(String msg) {
        this(msg, null);
    }

    public TipoElementoNaoConhecidoException(String msg, Throwable e) {
        super(msg, e);
    }

}
