// Arquivo: src/br/edu/ifpe/biblioteca/app/App.java

package br.edu.ifpe.biblioteca.app;

import br.edu.ifpe.biblioteca.modelo.Livro;
import br.edu.ifpe.biblioteca.servico.Catalogo;

public class App {

    public static void main(String[] args) {
        Catalogo catalogo = new Catalogo();
        catalogo.adicionar(new Livro("Dom Casmurro", "Machado de Assis", 1899));
        catalogo.adicionar(new Livro("Quincas Borba", "Machado de Assis", 1891));
        catalogo.adicionar(new Livro("Vidas Secas", "Graciliano Ramos", 1938));

        System.out.println("Total no catálogo: " + catalogo.total());

        for (Livro livro : catalogo.buscarPorAutor("machado de assis")) {
            System.out.println(livro.descricao());
        }
    }
}
