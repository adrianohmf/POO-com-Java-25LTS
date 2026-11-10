// Arquivo: EscreverArquivo.java

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

void main() throws IOException {
    Path pasta = Path.of("saida");
    Path arquivo = pasta.resolve("notas.txt");        // saida/notas.txt

    Files.createDirectories(pasta);                   // cria a pasta se ela não existir

    // writeString: grava um texto inteiro (substitui o conteúdo anterior)
    Files.writeString(arquivo, "Maria;8.5\n");

    // write com lista: uma linha por elemento
    Files.write(arquivo, List.of("João;7.0", "Ana;9.5"), StandardOpenOption.APPEND);

    // APPEND acrescenta ao final, sem apagar o que já existe
    Files.writeString(arquivo, "Pedro;6.0\n", StandardOpenOption.APPEND);

    IO.println("Arquivo criado em: " + arquivo.toAbsolutePath().getFileName());
    IO.println("Existe? " + Files.exists(arquivo));
    IO.println("Tamanho: " + Files.size(arquivo) + " bytes");
}
