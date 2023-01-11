package domain.exceptions;

public class TipoChaveNaoEncontradaException extends Exception {

    private static final long serialVersionUID = -1231283792173243220L;

    public TipoChaveNaoEncontradaException(String message) {
        super(message);
    }

    public TipoChaveNaoEncontradaException(String message, Throwable ex) {
        super(message, ex);
    }
}
