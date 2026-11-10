// Arquivo: src/br/edu/ifpe/biblioteca/modelo/Livro.java

package br.edu.ifpe.biblioteca.modelo;

public record Livro(String titulo, String autor, int ano) {

    public String descricao() {
        return titulo + " (" + autor + ", " + ano + ")";
    }
}
