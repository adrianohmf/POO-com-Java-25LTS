// Arquivo: AgendaDeLivros.java

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

void main() throws IOException {
    var repositorio = new RepositorioLivros(Path.of("dados", "livros.csv"));

    List<Livro> livros = repositorio.carregar();
    IO.println("Livros carregados do arquivo: " + livros.size());

    // A cada execução acrescenta um livro novo, e o arquivo guarda o histórico
    livros.add(new Livro("Livro número " + (livros.size() + 1), "Autor", 2000 + livros.size()));
    repositorio.salvar(livros);

    for (Livro livro : livros) {
        IO.println("  - " + livro.titulo() + " (" + livro.ano() + ")");
    }
    IO.println("Total salvo: " + livros.size());
}
