package br.com.aforca.admin.api.exception;

public class NomeCategoriaJaRegistradoException extends RuntimeException {
  public NomeCategoriaJaRegistradoException(String message) {
    super(message);
  }
}
