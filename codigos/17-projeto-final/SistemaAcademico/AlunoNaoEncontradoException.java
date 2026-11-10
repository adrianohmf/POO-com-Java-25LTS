// Arquivo: AlunoNaoEncontradoException.java

// EXCEÇÕES
class AlunoNaoEncontradoException extends Exception {
    AlunoNaoEncontradoException(String mat) {
        super("Aluno não encontrado: matrícula " + mat);
    }
}
