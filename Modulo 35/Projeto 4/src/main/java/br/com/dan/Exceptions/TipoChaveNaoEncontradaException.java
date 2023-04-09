package br.com.dan.Exceptions;

public class TipoChaveNaoEncontradaException extends Exception {

	private static final long serialVersionUID = 0;

	public TipoChaveNaoEncontradaException(String msg) {
        this(msg, null);
    }

    public TipoChaveNaoEncontradaException(String msg, Throwable e) {
        super(msg, e);
    }
}
