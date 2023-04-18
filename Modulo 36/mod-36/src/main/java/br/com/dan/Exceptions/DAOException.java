package br.com.dan.Exceptions;

public class DAOException extends Exception {

	private static final long serialVersionUID = 0;

	public DAOException(String msg, Exception ex) {
		super(msg, ex);
    }
}