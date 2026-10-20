// Arquivo: AlunoNaoEncontradoException.java

// EXCEÇÕES
class AlunoNaoEncontradoException extends Exception {
    AlunoNaoEncontradoException(String mat) {
        super("Aluno nao encontrado: matricula " + mat);
    }
}
