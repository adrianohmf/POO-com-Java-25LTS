// Arquivo: src/br/edu/ifpe/biblioteca/servico/Catalogo.java

package br.edu.ifpe.biblioteca.servico;

import br.edu.ifpe.biblioteca.modelo.Livro;
import java.util.ArrayList;

public class Catalogo {

    private final ArrayList<Livro> livros = new ArrayList<>();

    public void adicionar(Livro livro) {
        livros.add(livro);
    }

    public ArrayList<Livro> buscarPorAutor(String autor) {
        var encontrados = new ArrayList<Livro>();
        for (Livro livro : livros) {
            if (livro.autor().equalsIgnoreCase(autor)) {
                encontrados.add(livro);
            }
        }
        return encontrados;
    }

    public int total() {
        return livros.size();
    }

    // Sem modificador: visível apenas para classes do pacote "servico"
    void limpar() {
        livros.clear();
    }
}
