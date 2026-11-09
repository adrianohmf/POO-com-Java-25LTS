// Arquivo: AlunoNota.java

record AlunoNota(String nome, double media) {

    boolean aprovado() {
        return media >= 7.0;
    }
}
