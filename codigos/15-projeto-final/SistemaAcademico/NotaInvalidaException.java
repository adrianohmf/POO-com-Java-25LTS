// Arquivo: NotaInvalidaException.java

class NotaInvalidaException extends IllegalArgumentException {
    NotaInvalidaException(double nota) {
        super("Nota invalida: " + nota + " (deve ser entre 0 e 10)");
    }
}
